package com.coconet.server.controller;

import com.coconet.server.define.NoticeType;
import com.coconet.server.dto.*;
import com.coconet.server.entity.*;
import com.coconet.server.jwt.JwtFilter;
import com.coconet.server.jwt.JwtTokenProvider;
import com.coconet.server.repository.*;
import com.coconet.server.service.AuthService;
import com.coconet.server.service.LogService;
import com.coconet.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    private final BoardRepository boardRepository;
    private final LogService logService;
    private final NoticeType noticeDefine;
    private final ApprovalRepository approvalRepository;
    private final ChartRepository chartRepository;
    private final LogRepository logRepository;
    private final TodoRepository todoRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


    /**
     ??????
     */
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN ????????? ?????? ??????
    public List<Users> retrieveAllUsers() {
        return userRepository.findAll();
    }   // ?????? ????????? ??????

    @GetMapping("/log")
    public boolean logTest() {

        return logService.noticeLog(noticeDefine.WORK_START, "????????? ????????? ??????", "2022-07-18");

    }   // ?????? ????????? ??????

    @GetMapping("/users/{num}")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN ????????? ?????? ??????
    public Users retrieveUser(@PathVariable int num)
    {
        Optional<Users> users = userRepository.findById(num);

        if (users.isEmpty())
        {
            throw new UserNotFoundException(String.format("ID[%s] not found", num));
        }   // ???????????? ?????????????????????

        return users.get();
    }   // ????????? 1??? ??????


    /**
     ??????
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }


    /**
     ?????????
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto)
    {
        Users user = userRepository.findByEmail(loginDto.getEmail());
        Optional<Users> loginUser = Optional.ofNullable(user);

        // ???????????? ?????? header ??????
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Credentials", "true");

        // JWT ?????? ??????
        TokenDto tokenDto = authService.createToken(loginDto);

        // Refresh Token DB??? ??????
        Token tokenData = new Token(user.getEmail(), tokenDto.getRefreshToken());
        refreshTokenRepository.save(tokenData);

        /**
         * DB?????? ?????? ??????
         */

        if (loginUser.isEmpty()) {
            throw new UserNotFoundException(String.format("???????????? ?????? ??? ????????????."));
        }
        else if ((user.getEmail().equals(loginDto.getEmail()))
                && (bcrypt.matches(loginDto.getPassword(), user.getPassword())))
        // ??? ?????? ??????(plain)??? ??? ?????? ??????(encrypt)??? ?????? ???????????? ??????
        {
            String name = user.getName();
            AuthDto authDto = new AuthDto(name, "true");

            responseHeaders.add("Jwt_Access_Token", tokenDto.getAccessToken());
            responseHeaders.add("Jwt_Refresh_Token", tokenDto.getRefreshToken());

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(authDto);
        }
        else {
            throw new UserNotFoundException(String.format("????????? ????????? ???????????????."));
        }
    }

    @GetMapping("/board/notice")//???????????? ?????? ??????
    public List<Notice> noticeAll() {
        return boardRepository.findAll();
    }

    @GetMapping("/board/approval")//approvalData
    public List<ApprovalData> approvalAll() {
        return approvalRepository.findAll();
    }

    @GetMapping("/board/chart")//chartData
    public List<ChartData> chartAll() {
        return chartRepository.findAll();
    }

    @GetMapping("/board/log")//logData
    public List<LogData> logAll() {
        return logRepository.findAll();
    }

    @PostMapping("/board/todo")//todoData
    public List<TodoData> todoAll(@RequestBody TodoData todoData) {

        return todoRepository.findAll();
    }

    @DeleteMapping("/users/{num}")
    public void deleteUser(@PathVariable int num) {
        userRepository.deleteById(num);
    }   //????????? ??????

    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@Validated @RequestBody Users users) {
        Users savedUser = userRepository.save(users);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{num}")
                .buildAndExpand(savedUser.getNum())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
