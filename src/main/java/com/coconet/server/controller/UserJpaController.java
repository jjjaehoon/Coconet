package com.coconet.server.controller;

import com.coconet.server.define.LogTag;
import com.coconet.server.define.Status;
import com.coconet.server.dto.*;
import com.coconet.server.entity.*;
import com.coconet.server.exception.CustomException;
import com.coconet.server.exception.UserNotFoundException;
import com.coconet.server.jwt.JwtTokenProvider;
import com.coconet.server.repository.*;
import com.coconet.server.service.AuthService;
import com.coconet.server.service.CustomUserDetailsService;
import com.coconet.server.service.LogService;
import com.coconet.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class UserJpaController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final LogService logService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserStatusRepository userStatusRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LogTag logTag;
    private final Status status;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    /**
     조회
     */
    @GetMapping("/users") // 전체 사용자 조회
    //@PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<Users> retrieveAllUsers() {
        return userRepository.findAll();
    }   // 전체 사용자 조회

    @GetMapping("/users/{num}") // 사용자 1명 조회
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public UserCheckDto retrieveUser(@PathVariable int num)
    {
        Users userOne = userRepository.findByNum(num);
        UserCheckDto userCheckDto = new UserCheckDto(
                userOne.getEmail(),
                userOne.getPhone(),
                userOne.getDepartment(),
                userOne.getPosition(),
                userOne.getBirthDate()
        );

        return userCheckDto;
    }

    @GetMapping("/users/userid") // 사용자 num(고유번호) 조회
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public int retrieveUserNum(@RequestParam("email") String email)
    {
        return userRepository.findNumByEmail(email);
    }

    /**
     가입
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto,
            @RequestHeader("Jwt_Access_Token") String accessToken)
    {
        // 관리자 이메일(AdminEmail)은 @Nullable임
        Users user = userRepository.findByEmail(jwtTokenProvider.getUserEmail(accessToken));
        logService.buildLog(
                customUserDetailsService.loadAuthoritiesByUser(user)
                , logTag.TAG_SIGNUP
                , "신규 회원 등록"
                , user.getName()
                , user.getEmail()
                , user.getDepartment());
        return ResponseEntity.ok(userService.signup(userDto));
    }


    /**
     로그인
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto)
    {
        Users user = userRepository.findByEmail(loginDto.getEmail());
        Optional<Users> loginUser = Optional.ofNullable(user);

        // header 세팅
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Credentials", "true");

        // JWT 토큰 생성
        TokenDto tokenDto = authService.createToken(loginDto);
        // Refresh Token DB에 저장
        Token tokenData = new Token(user.getEmail(), tokenDto.getRefreshToken());
        refreshTokenRepository.save(tokenData);

        if (loginUser.isEmpty()) {
            // String tag, boolean status, String title, String email, String type
            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_LOGIN
                    , "로그인 실패"
                    , user.getName()
                    , user.getEmail()
                    , user.getDepartment());

            throw new UserNotFoundException(String.format("사용자를 찾을 수 없습니다."));
        }
        else if ((user.getEmail().equals(loginDto.getEmail()))
                && (bcrypt.matches(loginDto.getPassword(), user.getPassword())))
        // 첫 번째 인자(plain)와 두 번째 인자(encrypt)의 값이 동일한지 확인
        {
            String name = user.getName();
            int num = userRepository.findNumByEmail(loginDto.getEmail());

            responseHeaders.add("Jwt_Access_Token", tokenDto.getAccessToken());
            responseHeaders.add("Jwt_Refresh_Token", tokenDto.getRefreshToken());

            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_LOGIN
                    , "로그인 성공"
                    , user.getName()
                    , user.getEmail()
                    , user.getDepartment());

            UserStatus findUserStatus = userStatusRepository.findByNum(user.getNum()); // Users 테이블의 num을 가져와 UserStatus 테이블에서 PK가 일치하는 컬럼을 찾음
            if (findUserStatus.getStatus() == status.WORK_OUTWORK) // 외근 중인 상태에서 로그인
            {
                findUserStatus.setStatus(status.WORK_OUTWORK_START);
            }
            else if (findUserStatus.getStatus() == status.WORK_SITETRIP) // 출장 중인 상태에서 로그인
            {
                findUserStatus.setStatus(status.WORK_SITETRIP_START);
            }
            else
            {
                findUserStatus.setStatus(status.WORK_START); // 현재 유저 상태를 '업무중'으로 변경
            }
            userStatusRepository.save(findUserStatus); // db에 업데이트

            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_USER_STATUS
                    , "출근"
                    , user.getName()
                    , user.getEmail()
                    , user.getDepartment());

            AuthDto authDto = new AuthDto(name, "true"
                    , userStatusRepository.findStatusByNum(num)
                    , num);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(authDto);
        }
        else {
            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_LOGIN
                    , "로그인 실패"
                    , user.getName()
                    , user.getEmail()
                    , user.getDepartment());

            throw new UserNotFoundException(String.format("입력된 정보가 틀렸습니다."));
        }
    }

    /**
     로그아웃
     */
    @PostMapping("/logout")
    public void logout(@RequestHeader("Jwt_Access_Token") String accessToken, @RequestBody LogoutDto logoutDto)
    {
        UserStatus findUserStatus = userStatusRepository.findByNum(logoutDto.getNum()); // Users 테이블의 num을 가져와 UserStatus 테이블에서 PK가 일치하는 컬럼을 찾음

        if (findUserStatus.getStatus() == status.WORK_OUTWORK) // 외근 중인 상태에서 로그인
        {
            findUserStatus.setStatus(status.WORK_OUTWORK_NOTHING);
        }
        else if (findUserStatus.getStatus() == status.WORK_SITETRIP) // 출장 중인 상태에서 로그인
        {
            findUserStatus.setStatus(status.WORK_SITETRIP_NOTHING);
        }
        else
        {
            findUserStatus.setStatus(status.WORK_NOTHING); // 현재 유저 상태를 '출근전'으로 변경
        }
        userStatusRepository.save(findUserStatus); // db에 업데이트

        Users user = userRepository.findByEmail(jwtTokenProvider.getUserEmail(accessToken));
        logService.buildLog(
                customUserDetailsService.loadAuthoritiesByUser(user)
                , logTag.TAG_LOGOUT
                , "로그아웃"
                , user.getName()
                , user.getEmail()
                , user.getDepartment());
        logService.buildLog(
                customUserDetailsService.loadAuthoritiesByUser(user)
                , logTag.TAG_USER_STATUS
                , "퇴근"
                , user.getName()
                , user.getEmail()
                , user.getDepartment());
    }

    /**
     현재 비밀번호 체크
     */
    @PostMapping("/password/check")
    public boolean passwordCheck(@RequestBody PasswordCheckDto passwordCheckDto)
    {
        Users user = userRepository.findByNameAndPhone(passwordCheckDto.getName(), passwordCheckDto.getPhone());
        Optional<Users> loginUser = Optional.ofNullable(user);

        // db에 있는 비밀번호와 다르면
        if (!(bcrypt.matches(passwordCheckDto.getPassword(), user.getPassword())))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     @로그인 - 비밀번호 찾기
     @유저정보수정 - 비밀번호 변경
     */
    @PostMapping("/password/change")
    public ResponseEntity passwordChange(@RequestBody PasswordFindDto passwordDto)
    {
        Users user = userRepository.findByNameAndPhone(passwordDto.getName(), passwordDto.getPhone());
        Optional<Users> loginUser = Optional.ofNullable(user);

        if (passwordDto.getNewPassword().length() < 5)
        {
            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_PASSWORD_CHANGE
                    , "비밀번호 변경 실패"
                    , user.getName()
                    , user.getEmail()
                    , user.getDepartment());
            throw new CustomException(String.format("비밀번호는 5자 이상으로 설정해주세요."));
        }
        else
        {
            user = userService.passwordChange(user, passwordDto.getNewPassword());
            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_PASSWORD_CHANGE
                    , "비밀번호 변경 성공"
                    , user.getName()
                    , user.getEmail()
                    , user.getDepartment());
        }
        return ResponseEntity.ok(logTag.TAG_PASSWORD_CHANGE);
    }

    /**
     이름 변경
     */
    @PostMapping("/name/change")
    public AuthDto nameChange(@RequestBody Users users) {
        userRepository.updateNameByNum(users.getName(), users.getNum());

        Users modyUser = userRepository.findByNum(users.getNum());

        AuthDto authDto = new AuthDto(modyUser.getName(), "true"
                , userStatusRepository.findStatusByNum(modyUser.getNum())
                , modyUser.getNum());

        logService.buildLog(
                customUserDetailsService.loadAuthoritiesByUser(modyUser)
                , logTag.TAG_NAME_CHANGE
                , "이름 변경 성공"
                , modyUser.getName()
                , modyUser.getEmail()
                , modyUser.getDepartment());

        return authDto;
    }

    /**
     전화번호 변경
     */
    @PostMapping("/phone/change")
    public AuthDto phoneChange(@RequestBody Users users) {
        userRepository.updatePhoneByNum(users.getPhone(), users.getNum());

        Users modyUser = userRepository.findByNum(users.getNum());

        AuthDto authDto = new AuthDto(modyUser.getName(), "true"
                , userStatusRepository.findStatusByNum(modyUser.getNum())
                , modyUser.getNum());

        logService.buildLog(
                customUserDetailsService.loadAuthoritiesByUser(modyUser)
                , logTag.TAG_PHONE_CHANGE
                , "전화번호 변경 성공"
                , modyUser.getName()
                , modyUser.getEmail()
                , modyUser.getDepartment());

        return authDto;
    }


    @DeleteMapping("/users/{num}")
    public void deleteUser(@PathVariable int num) {
        userRepository.deleteById(num);
    }   //사용자 삭제
}
