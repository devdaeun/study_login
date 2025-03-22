package com.example.user;

import com.example.user.dto.UserSignupRequestDto;
import com.example.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where (u.username = :username or u.email = :email or u.slackName = :slackName)")
    Optional<User> findDulicate(@Param("username") String username,
                                @Param("email") String email,
                                @Param("slackName") String slackName);

    Optional<User> findByUsername(@Param("username") String username);
}
