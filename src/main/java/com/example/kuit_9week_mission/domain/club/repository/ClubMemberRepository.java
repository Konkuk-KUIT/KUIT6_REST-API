package com.example.kuit_9week_mission.domain.club.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class ClubMemberRepository {

    private final JdbcTemplate jdbc;

    // TODO: TODO 6을 구현하기 위해선 Club_Members 와 Clubs 테이블간의 JOIN 을 적절히 활용해야한다!
    public void save(Long studentId, Long clubId, LocalDate joinDate) {
        String sql = "INSERT INTO Club_Members(student_id, club_id, join_date) VALUES (?, ?, ?)";
        jdbc.update(sql, studentId, clubId, joinDate);
    }

    public boolean existsByStudentIdAndClubId(Long studentId, Long clubId) {
        String sql = """
            SELECT COUNT(*)
              FROM Club_Members
             WHERE student_id = ? AND club_id = ?
            """;

        Integer cnt = jdbc.queryForObject(sql, Integer.class, studentId, clubId);

        return cnt != null && cnt > 0;
    }


}
