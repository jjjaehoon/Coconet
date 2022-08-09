package com.coconet.server.repository;

import com.coconet.server.entity.TodoData;
import com.coconet.server.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByNum(int num);
    Users findByEmail(String email);
    Users findByName(String name);
    Users findByNameAndPhone(String name, String phone);
    Users findByDepartmentAndPositionAndName(String department, String position, String name);
    List<Users> findByDepartmentAndPosition(String department, String position);

    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByEmail(String email);

    @Query("select u.num from Users u "
            + "where u.email = :email")
    int findNumByEmail(@Param("email") String email);
}