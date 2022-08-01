package com.coconet.server.repository;

import com.coconet.server.entity.TodoData;
import com.coconet.server.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);
    Users findByName(String name);
    Users findByNameAndPhone(String name, String phone);

    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByEmail(String email);
}