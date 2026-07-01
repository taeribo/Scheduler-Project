package com.example.scheduler.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SchedulerViewController {

    @GetMapping("/")
    public String home(){return "index";}
}
