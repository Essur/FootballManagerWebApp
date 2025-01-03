package com.essur.fmwa.service.jdbc;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.model.response.TeamInfoResponse;
import com.essur.fmwa.utils.mapper.TeamInfoResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcTemplateTeamService {
    private final JdbcTemplate jdbcTemplate;

    public ResponseEntity<?> createTeam(TeamDTO teamDTO) {
        if (teamDTO.getTeamCommission() > 10) {
            return new ResponseEntity<>("Commission must be 10 or less", HttpStatus.BAD_REQUEST);
        }
        String sql = """
                INSERT INTO teams (team_name, commission, balance_usd)
                VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, teamDTO.getName());
            ps.setInt(2, teamDTO.getTeamCommission());
            ps.setInt(3, teamDTO.getBalanceUSD());
            return ps;
        }, keyHolder);

        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return new ResponseEntity<>(generatedId, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getTeamById(Long teamId) {
        String sql = """
                SELECT p.id AS player_id, p.first_name, p.last_name, p.middle_name, p.experience_in_months, p.age,
                        t.id AS team_id, t.team_name, t.commission, t.balance_usd
                FROM players p
                RIGHT JOIN teams t ON p.team_id = t.id
                WHERE t.id = ?
                """;

        List<Team> team = jdbcTemplate.query(sql, new TeamRowMapper(), teamId);

        if (team.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TeamInfoResponse response = TeamInfoResponseMapper.getTeamInfoResponse(team.get(0));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> updateTeam(Long teamId, UpdateTeamRequest updateTeamRequest) {
        if (updateTeamRequest.getTeamCommission() > 10) {
            return new ResponseEntity<>("Commission must be 10 or less", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<String> NOT_FOUND = checkTeamInDB(teamId);
        if (NOT_FOUND != null) return NOT_FOUND;

        String sql = """
                UPDATE teams
                SET team_name = ?, commission = ?, balance_usd = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                updateTeamRequest.getName(),
                updateTeamRequest.getTeamCommission(),
                updateTeamRequest.getBalanceUSD(),
                teamId);

        return new ResponseEntity<>("Team with id " + teamId + " was updated", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTeam(Long teamId) {
        ResponseEntity<String> NOT_FOUND = checkTeamInDB(teamId);
        if (NOT_FOUND != null) return NOT_FOUND;

        String sql = """
                DELETE FROM teams WHERE id = ?
                """;
        jdbcTemplate.update(sql, teamId);
        return new ResponseEntity<>("Team with id " + teamId + " was deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> getAllPlayers() {
        String sql = """
                SELECT t.id AS team_id, t.team_name, t.commission, t.balance_usd,
                       p.id AS player_id, p.first_name, p.last_name, p.middle_name, p.experience_in_months, p.age
                FROM teams t
                LEFT JOIN players p ON p.team_id = t.id
                ORDER BY t.id;
                """;
        List<Team> teams = jdbcTemplate.query(sql, new TeamRowMapper());
        teams.forEach(t -> log.info(t.toString()));
        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TeamInfoResponse> response = TeamInfoResponseMapper.getTeamInfoResponse(teams);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<String> checkTeamInDB(Long teamId) {
        String checkTeamSql = """
                SELECT COUNT(*) FROM teams WHERE id = ?
                """;
        int count = Optional.ofNullable(jdbcTemplate.queryForObject(checkTeamSql, Integer.class, teamId)).orElse(0);
        if (count == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return null;
    }

    public Team getTeamEntityById(Long buyerTeamId) {
        String sql = """
                SELECT p.id AS player_id, p.first_name, p.last_name, p.middle_name, p.experience_in_months, p.age,
                        t.id AS team_id, t.team_name, t.commission, t.balance_usd
                FROM players p
                RIGHT JOIN teams t ON p.team_id = t.id
                WHERE t.id = ?
                """;

        List<Team> team = jdbcTemplate.query(sql, new TeamRowMapper(), buyerTeamId);

        if (team.isEmpty()) {
            return null;
        }
        return team.get(0);
    }

    public void updateTeamData(Team team) {
        String sql = """
                UPDATE teams
                SET team_name = ?, commission = ?, balance_usd = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                team.getName(),
                team.getTeamCommission(),
                team.getBalance(),
                team.getId());
    }

    private static class TeamRowMapper implements RowMapper<Team>, Serializable {

        @Serial
        private static final long serialVersionUID = 2L;

        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            Team team = new Team();
            team.setId(rs.getLong("team_id"));
            team.setName(rs.getString("team_name"));
            team.setTeamCommission(rs.getInt("commission"));
            team.setBalance(rs.getInt("balance_usd"));

            List<Player> players = new ArrayList<>();
            do {
                Long playerId = rs.getLong("player_id");
                if (rs.wasNull()) break;

                Player player = new Player();
                player.setId(playerId);
                player.setFirstName(rs.getString("first_name"));
                player.setLastName(rs.getString("last_name"));
                player.setMiddleName(rs.getString("middle_name"));
                player.setExperience(rs.getInt("experience_in_months"));
                player.setAge(rs.getInt("age"));

                players.add(player);
            } while (rs.next() && rs.getLong("team_id") == team.getId());
            team.setPlayers(players);
            return team;
        }
    }
}
