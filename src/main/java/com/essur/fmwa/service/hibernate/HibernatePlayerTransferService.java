package com.essur.fmwa.service.hibernate;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.service.AbstractPlayerTransferService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HibernatePlayerTransferService extends AbstractPlayerTransferService {
    private final EntityManager entityManager;
    private final HibernateTeamService teamService;
    private final HibernatePlayerService playerService;

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
        entityManager.merge(team);
    }

    @Override
    protected void updatePlayerData(Player player) {
        entityManager.merge(player);
    }
}
