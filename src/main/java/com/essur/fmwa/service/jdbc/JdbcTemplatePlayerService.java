package com.essur.fmwa.service.jdbc;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.exception.custom.DataNotFoundException;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.model.response.PlayerInfoResponse;
import com.essur.fmwa.utils.mapper.PlayerInfoResponseMapper;
import lombok.RequiredArgsConstructor;
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
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JdbcTemplatePlayerService {
    private final JdbcTemplate jdbcTemplate;

    public Long createPlayer(PlayerDTO player) {
        String sql = """
                INSERT INTO players (first_name, last_name, middle_name, experience_in_months, age, team_id) 
                VALUES (?, ?, ?, ?, ?, ?)
                """;


        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getMiddleName());
            ps.setInt(4, player.getExperience());
            ps.setInt(5, player.getAge());
            ps.setLong(6, player.getTeamId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public PlayerInfoResponse getPlayerById(Long playerId) {
        String sql = """
                SELECT p.*, t.*
                FROM players p
                LEFT JOIN teams t ON p.team_id = t.id
                WHERE p.id = ?
                """;

        List<Player> player = jdbcTemplate.query(sql, new PlayerRowMapper(), playerId);

        if (player.isEmpty()) {
            throw new DataNotFoundException("PLayer with id " + playerId + " was not found");
        }

        return PlayerInfoResponseMapper.getPlayerInfoResponse(player.get(0));
    }

    public PlayerInfoResponse updatePlayer(Long playerId, UpdatePlayerRequest updatePlayerRequest) {
        checkPlayerInDB(playerId);

        String sql = """
                UPDATE players
                SET first_name = ?, last_name = ?, middle_name = ?, experience_in_months = ?, age = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                updatePlayerRequest.getFirstName(),
                updatePlayerRequest.getLastName(),
                updatePlayerRequest.getMiddleName(),
                updatePlayerRequest.getExperience(),
                updatePlayerRequest.getAge(),
                playerId);

        return getPlayerById(playerId);
    }

    public void deletePlayer(Long playerId) {
        checkPlayerInDB(playerId);

        String sql = """
                DELETE FROM players WHERE id = ?
                """;

        jdbcTemplate.update(sql, playerId);
    }

    public List<PlayerInfoResponse> getAllPlayers() {
        String sql = """
                SELECT p.*, t.*
                FROM players p
                LEFT JOIN teams t ON p.team_id = t.id
                """;

        List<Player> players = jdbcTemplate.query(sql, new PlayerRowMapper());
        if (players.isEmpty()) {
            throw new DataNotFoundException("Players list is empty");
        }
        return PlayerInfoResponseMapper.getPlayerInfoResponse(players);
    }

    private void checkPlayerInDB(Long playerId) {
        String checkPlayerSql = "SELECT COUNT(*) FROM players WHERE id = ?";
        int count = Optional.ofNullable(jdbcTemplate.queryForObject(checkPlayerSql, Integer.class, playerId)).orElse(0);
        if (count == 0) {
            throw new DataNotFoundException("Player with ID " + playerId + " not found.");
        }
    }

    public Player getPlayerEntityById(Long playerId) {
        String sql = """
                SELECT p.*, t.*
                FROM players p
                LEFT JOIN teams t ON p.team_id = t.id
                WHERE p.id = ?
                """;

        List<Player> player = jdbcTemplate.query(sql, new PlayerRowMapper(), playerId);
        if (player.isEmpty()) {
            return null;
        }
        return player.get(0);
    }

    public void updatePlayerData(Player player) {
        String sql = """
                UPDATE players
                SET team_id = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                player.getTeam().getId(),
                player.getId());
    }

    private static class PlayerRowMapper implements RowMapper<Player>, Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getLong("id"));
            player.setFirstName(rs.getString("first_name"));
            player.setLastName(rs.getString("last_name"));
            player.setMiddleName(rs.getString("middle_name"));
            player.setExperience(rs.getInt("experience_in_months"));
            player.setAge(rs.getInt("age"));

            Team team = new Team();
            team.setId(rs.getLong("team_id"));
            team.setName(rs.getString("team_name"));
            team.setTeamCommission(rs.getInt("commission"));
            team.setBalance(rs.getInt("balance_usd"));

            player.setTeam(team);

            return player;
        }
    }
}


