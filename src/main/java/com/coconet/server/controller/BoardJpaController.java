package com.coconet.server.controller;

import com.coconet.server.dto.TodoResultDto;
import com.coconet.server.entity.*;
import com.coconet.server.exception.UserNotFoundException;
import com.coconet.server.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class BoardJpaController {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ApprovalRepository approvalRepository;
    private final ChartRepository chartRepository;
    private final LogRepository logRepository;
    private final TodoRepository todoRepository;

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
    @PostMapping("/board/todo/add")
    public void todoAdd(@RequestBody TodoData todoData) {

        Users findUser = userRepository.findByName(todoData.getUserName());

        TodoData todoAddData = new TodoData();
        todoAddData.setUserNum(findUser.getNum());
        todoAddData.setUserName(todoData.getUserName());
        todoAddData.setTodo(todoData.getTodo());

        TodoData todoResultData = todoRepository.save(todoAddData);
    }

    /**
     * todolist 삭제
     */
    @DeleteMapping("/board/todo/delete")
    public void todoDelete(@RequestBody TodoData todoData) {
        todoRepository.delete(todoRepository.findByTodo(todoData.getTodo()));
    }
}
