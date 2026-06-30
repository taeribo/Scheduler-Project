package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service


public class SchedulerService {
    private final SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository schedulerRepository){
        this.schedulerRepository = schedulerRepository;
    }
    @Transactional
    public ScheduleResponseDto create(ScheduleRequestDto requestDto){
        Scheduler scheduler = new Scheduler(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );


        Scheduler savedScheduler =schedulerRepository.save(scheduler);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                savedScheduler.getId(),
                savedScheduler.getTitle(),
                savedScheduler.getContent(),
                savedScheduler.getAuthor(),
                savedScheduler.getCreatedAt(),
                savedScheduler.getModifiedAt()
        );

        return scheduleResponseDto;
    }
}
