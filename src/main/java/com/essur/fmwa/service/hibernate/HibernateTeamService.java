package com.essur.fmwa.service.hibernate;

import com.essur.fmwa.entity.Team;
import com.essur.fmwa.exception.BadRequestException;
import com.essur.fmwa.exception.DataNotFoundException;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.model.response.TeamInfoResponse;
import com.essur.fmwa.utils.mapper.TeamInfoResponseMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateTeamService {
    private final EntityManager entityManager;

    @Transactional
    public Long createTeam(TeamDTO teamDTO) {
        if (teamDTO.getTeamCommission() > 10) {
            throw new BadRequestException("Commission must be 10 or less");
        }
        Team teamEntity = new Team();

        teamEntity.setName(teamDTO.getName());
        teamEntity.setBalance(teamDTO.getBalanceUSD());
        teamEntity.setTeamCommission(teamDTO.getTeamCommission());
        entityManager.persist(teamEntity);

        return teamEntity.getId();
    }

    public TeamInfoResponse getTeamById(Long teamId) {
        Team team = entityManager.find(Team.class, teamId);
        if (team == null) {
            throw new DataNotFoundException("Team with id " + teamId + " not found");
        }
        return TeamInfoResponseMapper.getTeamInfoResponse(team);
    }

    @Transactional
    public TeamInfoResponse updateTeam(Long teamId, UpdateTeamRequest updateTeamRequest) {
        if (updateTeamRequest.getTeamCommission() > 10) {
            throw new BadRequestException("Commission must be 10 or less");
        }
        Team teamEntity = entityManager.find(Team.class, teamId);
        if (teamEntity == null) {
            throw new DataNotFoundException("Team with id " + teamId + " not found");
        }
        teamEntity.setName(updateTeamRequest.getName());
        teamEntity.setBalance(updateTeamRequest.getBalanceUSD());
        teamEntity.setTeamCommission(updateTeamRequest.getTeamCommission());
        entityManager.merge(teamEntity);
        return TeamInfoResponseMapper.getTeamInfoResponse(teamEntity);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        Team team = entityManager.find(Team.class, teamId);
        if (team == null) {
            throw new DataNotFoundException("Team with id " + teamId + " not found");
        }
        entityManager.remove(team);
    }

    public List<TeamInfoResponse> getAllTeams() {
        List<Team> teams = entityManager
                .createQuery("SELECT t FROM Team t", Team.class)
                .getResultList();
        if (teams.isEmpty()) {
            throw new DataNotFoundException("List of teams is empty");
        }
        return TeamInfoResponseMapper.getTeamInfoResponse(teams);
    }

    public Team getTeamEntityById(Long teamId) {
        return entityManager.find(Team.class, teamId);
    }
}
