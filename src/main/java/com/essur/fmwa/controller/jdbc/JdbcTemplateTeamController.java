package com.essur.fmwa.controller.jdbc;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.model.response.TeamInfoResponse;
import com.essur.fmwa.service.jdbc.JdbcTemplateTeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JdbcTemplateTeamController {
    private final JdbcTemplateTeamService jdbcTemplateTeamService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = Routes.JDBC_CREATE_TEAM, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createTeam(@RequestBody @Valid TeamDTO teamDTO) {
        return jdbcTemplateTeamService.createTeam(teamDTO);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.JDBC_GET_TEAM_BY_ID)
    public TeamInfoResponse getTeamById(@RequestParam("teamId") Long teamId) {
        return jdbcTemplateTeamService.getTeamById(teamId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.JDBC_GET_ALL_TEAMS)
    public List<TeamInfoResponse> getAllTeams() {
        return jdbcTemplateTeamService.getAllTeams();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = Routes.JDBC_UPDATE_TEAM_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeamInfoResponse updateTeam(@RequestParam("teamId") Long teamId, @RequestBody @Valid UpdateTeamRequest updateTeamRequest) {
        return jdbcTemplateTeamService.updateTeam(teamId, updateTeamRequest);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = Routes.JDBC_DELETE_TEAM)
    public void deleteTeam(@RequestParam("teamId") Long teamId) {
        jdbcTemplateTeamService.deleteTeam(teamId);
    }
}
