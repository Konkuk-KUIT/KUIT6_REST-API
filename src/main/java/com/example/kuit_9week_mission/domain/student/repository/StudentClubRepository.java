package com.example.kuit_9week_mission.domain.student.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentClubRepository {

    private final JdbcTemplate jdbc;

    // TODO: TODO 6을 구현하기 위해선 StudentClubs 와 Clubs 테이블간의 JOIN 을 적절히 활용해야한다!


}
