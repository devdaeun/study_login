package com.example.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "p_slackuser")
@Entity
public class SlackUser {
    // 사용자 테이블에서
    // 슬랙이름에 대한 정보를 따로 저장하는 엔티티
    // 관리자 계층 등의 role값은 슬랙을 사용하지않기때문에 특정 권한에서만 생성되도록 엔티티 분리

    // 회원 이름(user_id), 슬랙이름(slackName) 나머지 공통 Base entity로 구성
    // 슬랙관련된 내용만 따로 저장하기위한것이어서 많은컬럼은 없다.
    // 어 근데 그러면 이거 추가할때는 어떻게 추가하게해야하지 엄... response dto 따로 만드니까 거기에다ㅏㄱ 값을 떼어다가 넣어주면
    // 되긴할거같은데 흠... 다시 고민을 해봐야겠다.

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "slack_user_id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    private Long user_id;

    private String slackName;

}
