package com.nagarro.test.repo;

import com.nagarro.test.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);


    @Modifying
    @Query("update UserEntity usr set usr.isLoggedIn =:status where usr.id=:usr_id")
    void changeUserLoginStatus(@Param("usr_id") Long id, @Param("status") Boolean status);
}
