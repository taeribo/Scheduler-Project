package com.example.scheduler.dto;



import lombok.Getter;


@Getter
public class ScheduleRequestDto {
    private final String title;
    private final String content;
    private final String author;
    private final String password;




    public ScheduleRequestDto(String title, String content, String author, String password){
       this.title = title;
       this.content = content;
       this.author = author;
       this.password = password;
    }
    }
