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
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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
import java.util.ArrayList;
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
     조회
     */
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<Users> retrieveAllUsers() {
        return userRepository.findAll();
    }   // 전체 사용자 조회

    @GetMapping("/log")
    public boolean logTest() {

        return logService.noticeLog(noticeDefine.WORK_START, "개발팀 김사원 출근", "2022-07-18");

    }   // 전체 사용자 조회

    @GetMapping("/users/{num}")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public Users retrieveUser(@PathVariable int num)
    {
        Optional<Users> users = userRepository.findById(num);

        if (users.isEmpty())
        {
            throw new UserNotFoundException(String.format("ID[%s] not found", num));
        }   // 데이터가 존재하지않으면

        return users.get();
    }   // 사용자 1명 조회


    /**
     가입
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
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

        // 김현빈을 위한 header 세팅
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Credentials", "true");

        // JWT 토큰 생성
        TokenDto tokenDto = authService.createToken(loginDto);

        // Refresh Token DB에 저장
        Token tokenData = new Token(user.getEmail(), tokenDto.getRefreshToken());
        refreshTokenRepository.save(tokenData);

        /**
         * DB에서 유저 조회
         */

        if (loginUser.isEmpty()) {
            throw new UserNotFoundException(String.format("사용자를 찾을 수 없습니다."));
        }
        else if ((user.getEmail().equals(loginDto.getEmail()))
                && (bcrypt.matches(loginDto.getPassword(), user.getPassword())))
        // 첫 번째 인자(plain)와 두 번째 인자(encrypt)의 값이 동일한지 확인
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
            throw new UserNotFoundException(String.format("입력된 정보가 틀렸습니다."));
        }
    }

    /**
     * 공지사항 조회
     */
    @GetMapping("/board/notice")//공지사항 전체 조회
    public List<Notice> noticeAll() {
        return boardRepository.findAll();
    }

    /**
     * 결재 조회
     */
    @GetMapping("/board/approval")//approvalData
    public List<ApprovalData> approvalAll() {
        return approvalRepository.findAll();
    }

    /**
     * 차트 조회
     */
    @GetMapping("/board/chart")//chartData
    public List<ChartData> chartAll() {
        return chartRepository.findAll();
    }

    /**
     * 실시간 기록 조회
     */
    @GetMapping("/board/log")//logData
    public List<LogData> logAll() {
        return logRepository.findAll();
    }


    /**
     * todolist 조회
     */
    @GetMapping("/board/todo")//todoData
    public List<TodoResultDto> todoAll(@RequestParam("username") String name) {

        int size = todoRepository.findByuserName(name).size();
        List<TodoData> listTodo = todoRepository.findByuserName(name);
        List<TodoResultDto> todoResultDto = new ArrayList<>(size);

        if (!listTodo.isEmpty()) {
            for (int i=0; i<size; i++) {
                TodoResultDto resultDto = new TodoResultDto(listTodo.get(i).getTodo());
                todoResultDto.add(i, resultDto);
            }

            return todoResultDto;
        }
        else {
            throw new UserNotFoundException(String.format("조회할 리스트가 없습니다."));
        }
    }

    /**
     * todolist 추가
     */
    @PostMapping("board/todo/add")
    public TodoData todoAdd(@RequestBody TodoData todoData) {



        return todoData;
    }

    @DeleteMapping("/users/{num}")
    public void deleteUser(@PathVariable int num) {
        userRepository.deleteById(num);
    }   //사용자 삭제

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
