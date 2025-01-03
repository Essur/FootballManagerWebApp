package com.essur.fmwa.service.hibernate;

import com.essur.fmwa.entity.Team;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.model.response.TeamInfoResponse;
import com.essur.fmwa.utils.mapper.TeamInfoResponseMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HibernateTeamService {
    private final EntityManager entityManager;

    @Transactional
    public ResponseEntity<?> createTeam(TeamDTO teamDTO) {
        if (teamDTO.getTeamCommission() > 10) {
            return new ResponseEntity<>("Commission must be 10 or less",HttpStatus.BAD_REQUEST);
        }
        Team teamEntity = new Team();

        teamEntity.setName(teamDTO.getName());
        teamEntity.setBalance(teamDTO.getBalanceUSD());
        teamEntity.setTeamCommission(teamDTO.getTeamCommission());
        entityManager.persist(teamEntity);

        return new ResponseEntity<>(teamEntity.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getTeamById(Long id) {
        Team team = entityManager.find(Team.class, id);
        if (team == null) {
            return new ResponseEntity<>("Team not found", HttpStatus.NO_CONTENT);
        }
        TeamInfoResponse teamInfoResponse = TeamInfoResponseMapper.getTeamInfoResponse(team);
        return new ResponseEntity<>(teamInfoResponse, HttpStatus.FOUND);
    }

    @Transactional
    public ResponseEntity<?> updateTeam(Long teamId, UpdateTeamRequest updateTeamRequest) {
        if (updateTeamRequest.getTeamCommission() > 10) {
            return new ResponseEntity<>("Commission must be 10 or less",HttpStatus.BAD_REQUEST);
        }
        Team teamEntity = entityManager.find(Team.class, teamId);
        if (teamEntity == null) {
            return new ResponseEntity<>("Team not found", HttpStatus.NO_CONTENT);
        }
        teamEntity.setName(updateTeamRequest.getName());
        teamEntity.setBalance(updateTeamRequest.getBalanceUSD());
        teamEntity.setTeamCommission(updateTeamRequest.getTeamCommission());
        entityManager.merge(teamEntity);
        return new ResponseEntity<>("Team was successfully updated", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteTeam(Long teamId) {
        Team team = entityManager.find(Team.class, teamId);
        if (team == null) {
            return new ResponseEntity<>("Team not found", HttpStatus.NO_CONTENT);
        }
        entityManager.remove(team);
        return new ResponseEntity<>("Player was successfully deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> getAllTeams() {
        List<Team> teams = entityManager
                .createQuery("SELECT t FROM Team t", Team.class)
                .getResultList();
        if (teams.isEmpty()) {
            return new ResponseEntity<>("List of teams are empty",HttpStatus.NO_CONTENT);
        }
        List<TeamInfoResponse> teamInfoResponse = TeamInfoResponseMapper.getTeamInfoResponse(teams);
        return new ResponseEntity<>(teamInfoResponse, HttpStatus.OK);
    }

    public Team getTeamEntityById(Long teamId) {
        return entityManager.find(Team.class, teamId);
    }
}
