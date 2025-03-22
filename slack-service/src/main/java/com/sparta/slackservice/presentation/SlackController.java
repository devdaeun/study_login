package com.sparta.slackservice.presentation;

import com.sparta.slackservice.application.dto.SlackRequestDto;
import com.sparta.slackservice.application.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/slacks")
@RequiredArgsConstructor
public class SlackController {
//    @GetMapping("/test")
//    public String getSlack() {
//        return "Hello World";
//    }

    private final SlackService slackService;

    @GetMapping("")
    public ResponseEntity<?> createSlack(@RequestBody SlackRequestDto requestDto) {

        slackService.createSlack(requestDto);

        return null;
    }
}
