package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service




public class SchedulerService {
    private final SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }

    @Transactional
    public ScheduleResponseDto create(ScheduleRequestDto requestDto) {
        Scheduler scheduler = new Scheduler(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getAuthor(),
                requestDto.getPassword()
        );


        Scheduler savedScheduler = schedulerRepository.save(scheduler);

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


    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {

        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<>();

        List<Scheduler> schedulerList = schedulerRepository.findAll();
        for (Scheduler scheduler : schedulerList) {
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                    scheduler.getId(),
                    scheduler.getTitle(),
                    scheduler.getContent(),
                    scheduler.getAuthor(),
                    scheduler.getCreatedAt(),
                    scheduler.getModifiedAt()
            );

            scheduleResponseDtoList.add(scheduleResponseDto);
        }

        return scheduleResponseDtoList;
    }


    @Transactional(readOnly = true)
    public ScheduleResponseDto findOne(Long id) {
        Scheduler scheduler = getOrThrow(id);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                scheduler.getId(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getAuthor(),
                scheduler.getCreatedAt(),
                scheduler.getModifiedAt()
        );

        return scheduleResponseDto;
    }


    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) {
        Scheduler scheduler = getOrThrow(id);
        if (!scheduler.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }

        scheduler.updateSchedule(
                requestDto.getTitle(),
                requestDto.getAuthor()
        );

        Scheduler updatedScheduler = schedulerRepository.save(scheduler);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(

                updatedScheduler.getId(),
                updatedScheduler.getTitle(),
                updatedScheduler.getContent(),
                updatedScheduler.getAuthor(),
                updatedScheduler.getCreatedAt(),
                updatedScheduler.getModifiedAt()
        );


        return scheduleResponseDto;
    }


    @Transactional
    public void delete(Long id, ScheduleRequestDto requestDto) {
        Scheduler scheduler = getOrThrow(id);
        if (!scheduler.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }
        schedulerRepository.delete(scheduler);
    }

    private Scheduler getOrThrow(Long id) {
        return schedulerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "일정을 찾을 수 없어요: " + id));
    }
}
