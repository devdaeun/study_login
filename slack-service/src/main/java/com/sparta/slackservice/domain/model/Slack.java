package com.sparta.slackservice.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Slack{

    @Id
    @Column(name = "slack_name", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String slackName;

    @Column(nullable = false)
    private String message;

    @Column(name = "receiver_id",nullable = false)
    private UUID receiverId;

    @Column(name = "sended_at")
    private LocalDateTime sendedAt;

    @Column(name = "sending_status")
    private boolean sendingStatus; //기본 true? false?

}
