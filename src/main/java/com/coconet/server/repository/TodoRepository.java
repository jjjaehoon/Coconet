package com.coconet.server.repository;

import com.coconet.server.entity.ApprovalData;
import com.coconet.server.entity.TodoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoData, Integer> {
    TodoData findByName(String name);
    TodoData findByTodo(String todo);
}
