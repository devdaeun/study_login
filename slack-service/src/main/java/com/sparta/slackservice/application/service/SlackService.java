package com.sparta.slackservice.application.service;

import com.sparta.slackservice.application.dto.SlackRequestDto;
import com.sparta.slackservice.domain.model.Slack;
import com.sparta.slackservice.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlackService {

    private final SlackRepository slackRepository;

    public String createSlack(SlackRequestDto requestDto) {
        Slack slack = new Slack();
//        slackRepository.save(slack);
        return "hi";
    }

}
