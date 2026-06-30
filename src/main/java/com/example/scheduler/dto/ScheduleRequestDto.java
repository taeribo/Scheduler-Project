package com.example.scheduler.dto;
//보내는 데이터

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ScheduleRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ScheduleRequestDro{
        private String title;
        private String content;
        private String author;
        private String password;
    }
}
