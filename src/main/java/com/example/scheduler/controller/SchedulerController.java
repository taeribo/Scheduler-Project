package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduler")

public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto>
    create(@RequestBody  ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto saved = schedulerService.create(scheduleRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> responseDtoList = schedulerService.findAll();
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findOne(@PathVariable Long id) {
        ScheduleResponseDto responseDto = schedulerService.findOne(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleResponseDto scheduleResponseDto = schedulerService.update(id, scheduleRequestDto);
        return ResponseEntity.ok(scheduleResponseDto);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){

        schedulerService.delete(id,requestDto);
        return ResponseEntity.noContent().build();
    }


}
