package com.essur.fmwa.controller.jdbc;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.service.jdbc.JdbcTemplateTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JdbcTemplateTeamController {
    private final JdbcTemplateTeamService jdbcTemplateTeamService;

    @PostMapping(value = Routes.JDBC_CREATE_TEAM, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO){
        return jdbcTemplateTeamService.createTeam(teamDTO);
    }

    @GetMapping(value = Routes.JDBC_GET_TEAM_BY_ID)
    public ResponseEntity<?> getTeamById(@RequestParam("teamId") Long teamId) {
        return jdbcTemplateTeamService.getTeamById(teamId);
    }

    @GetMapping(value = Routes.JDBC_GET_ALL_TEAMS)
    public ResponseEntity<?> getAllTeams() {
        return jdbcTemplateTeamService.getAllPlayers();
    }

    @PutMapping(value = Routes.JDBC_UPDATE_TEAM_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTeam(@RequestParam("teamId") Long teamId, @RequestBody UpdateTeamRequest updateTeamRequest) {
        return jdbcTemplateTeamService.updateTeam(teamId, updateTeamRequest);
    }

    @DeleteMapping(value = Routes.JDBC_DELETE_TEAM)
    public ResponseEntity<?> deleteTeam(@RequestParam("teamId") Long teamId) {
        return jdbcTemplateTeamService.deleteTeam(teamId);
    }
}
