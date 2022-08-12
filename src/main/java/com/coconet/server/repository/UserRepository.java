package com.coconet.server.repository;

import com.coconet.server.entity.TodoData;
import com.coconet.server.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByNum(int num);
    Users findByEmail(String email);
    Users findByNameAndPhone(String name, String phone);
    Users findByDepartmentAndPositionAndName(String department, String position, String name);
    List<Users> findByDepartmentAndPosition(String department, String position);

    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByEmail(String email);

    @Query("select u.num from Users u "
            + "where u.email = :email")
    int findNumByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("update Users u set u.name = :modify_name where u.num = :num")
    void updateNameByNum(@Param("modify_name") String modify_name, @Param("num") int num);

    @Transactional
    @Modifying
    @Query("update Users u set u.phone = :modify_phone where u.num = :num")
    void updatePhoneByNum(@Param("modify_phone") String modify_phone, @Param("num") int num);
}