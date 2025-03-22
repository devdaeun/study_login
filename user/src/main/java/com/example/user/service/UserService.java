package com.example.user.service;

import com.example.user.UserRepository;
import com.example.user.config.AuthConfig;
import com.example.user.dto.UserSigninReqeustDto;
import com.example.user.dto.UserSigninResponseDto;
import com.example.user.dto.UserSignupRequestDto;
import com.example.user.entity.User;
import com.example.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthConfig authConfig;

    public ResponseEntity<?> signUp(UserSignupRequestDto requestDto) {
        //중복 확인
        Optional<User> duplicate = userRepository.findDulicate(requestDto.getUsername(),
                requestDto.getEmail(), requestDto.getSlackName());
        if(duplicate.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Email or SlackName is already taken.");
        }
        //권한 설정
        UserRoleEnum role = checkUserRole(requestDto.getTokenValue());

        User user = userRepository.save(
                User.builder()
                        .username(requestDto.getUsername())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .email(requestDto.getEmail())
                        .slackName(requestDto.getSlackName())
                        .role(role.toString())
                        .build()
        );
        return ResponseEntity.ok("sign-up success");
    }
    //공통오류 처리진행
    public UserSigninResponseDto signIn(@Valid UserSigninReqeustDto reqeustDto) throws AuthenticationException {
        //아이디 비밀번호 일치여부 확인.
        Optional<User> userinfo = userRepository.findByUsername(reqeustDto.getUsername());

        User user = userinfo.orElseThrow(()-> new AuthenticationException("아이디가 존재하지않습니다."));
        boolean pwcheck =  passwordEncoder.matches(reqeustDto.getPassword(), user.getPassword()); //비밀번호 일치여부 판단

        if(!pwcheck){
            throw new AuthenticationException("아이디나 비밀번호가 일치하지않습니다.");// 일치하지않는 부분 특정방지
        }

        //비밀번호 일치 (토큰 생성을 위한 dto 전달.)
        UserSigninResponseDto responseDto = new UserSigninResponseDto();
        responseDto.setId(user.getId());
        responseDto.setRole(user.getRole());
        responseDto.setSlackName(user.getSlackName());

        return responseDto;
    }

    private UserRoleEnum checkUserRole(String tokenValue) {
        if (authConfig.getMasterKey().equals(tokenValue)) {
            return UserRoleEnum.MASTER;
        } else if (authConfig.getHubKey().equals(tokenValue)) {
            return UserRoleEnum.HUB;
        } else if (authConfig.getShippingKey().equals(tokenValue)) {
            return UserRoleEnum.SHIPPING;
        } else {
            return UserRoleEnum.COMPANY;
        }
    }

    //리턴에 대한 형식
    //공통 ResponseEntity 설정? 을 해야할까에대한 고민중

}
