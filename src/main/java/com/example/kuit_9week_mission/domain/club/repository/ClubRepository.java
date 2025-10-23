package com.example.kuit_9week_mission.domain.club.repository;

import com.example.kuit_9week_mission.domain.club.model.Club;
import com.example.kuit_9week_mission.domain.club.model.ClubStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClubRepository {

    private final JdbcTemplate jdbc;

    private static final RowMapper<Club> MAPPER = (ResultSet rs, int rowNum) -> new Club(
            rs.getLong("club_id"),
            rs.getString("name"),
            rs.getString("description"),
            ClubStatus.valueOf(rs.getString("status"))
    );

    public List<Club> findClubs(long lastId, int size) {
        String sql = """
            SELECT club_id, name, description
              FROM Clubs
             WHERE club_id < ?
             ORDER BY club_id DESC
             LIMIT ?
            """;

        return jdbc.query(sql, MAPPER, lastId, size + 1);
    }

    public Optional<Club> findById(Long clubId) {
        String sql = "SELECT club_id, name, description FROM Clubs WHERE club_id = ?";

        return jdbc.query(sql, MAPPER, clubId).stream().findFirst();
    }

    public void update(Long clubId, String name, String description) {
        jdbc.update("UPDATE Clubs SET name = ?, description = ? WHERE club_id = ?",
                name, description, clubId);
    }

}
