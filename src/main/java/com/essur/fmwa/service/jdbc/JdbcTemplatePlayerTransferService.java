package com.essur.fmwa.service.jdbc;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.service.AbstractPlayerTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcTemplatePlayerTransferService extends AbstractPlayerTransferService {
    private final JdbcTemplateTeamService teamService;
    private final JdbcTemplatePlayerService playerService;

    @Override
    protected Player getPlayerById(Long playerId) {
        return playerService.getPlayerEntityById(playerId);
    }

    @Override
    protected Team getTeamById(Long teamId) {
        return teamService.getTeamEntityById(teamId);
    }

    @Override
    protected void updateTeamData(Team team) {
        teamService.updateTeamData(team);
    }

    @Override
    protected void updatePlayerData(Player player) {
        playerService.updatePlayerData(player);
    }
}
