package com.coconet.server.repository;

import com.coconet.server.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

    UserStatus findByNum(int num);
    long countByStatus(int status);

    @Query("select u.status from UserStatus u "
            + "where u.num = :num")
    String findStatusByNum(@Param("num") int num);

    @Transactional
    @Modifying
    @Query("update UserStatus u set u.status = :modify_status where u.num = :num")
    void updateStatus(@Param("modify_status") String status, @Param("num") String num);
}