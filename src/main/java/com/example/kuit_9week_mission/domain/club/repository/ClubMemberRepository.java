package com.example.kuit_9week_mission.domain.club.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubMemberRepository {

    private final JdbcTemplate jdbc;

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

    public List<String> findClubNamesByStudentId(Long studentId) {
        String sql = """
                SELECT c.name
                FROM Club_Members cm
                JOIN Clubs c ON cm.club_id = c.club_id
                WHERE cm.student_id = ?
                """;

        return jdbc.queryForList(sql, String.class, studentId);
    }

}
