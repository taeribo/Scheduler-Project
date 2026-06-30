package com.example.scheduler.repository;

import com.example.scheduler.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    List<Scheduler> findALlByAuthorOrderByModifiedAtDesc(String author);

    List<Scheduler> findAllByOrderByModifiedAtDesc();
}

// ai로 구현한 메서드 규칙 작성자명 기준으로 일정 조회
// findAllBY 전체 찾기
// Author author 필드를 기준으로
// OrderByModifiedAtDesc modifiedAt 기준으로 내림차순
