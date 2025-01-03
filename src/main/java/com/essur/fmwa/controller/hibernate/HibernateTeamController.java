package com.essur.fmwa.controller.hibernate;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.model.response.TeamInfoResponse;
import com.essur.fmwa.service.hibernate.HibernateTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HibernateTeamController {
    private final HibernateTeamService hibernateTeamService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.H_GET_ALL_TEAMS)
    public List<TeamInfoResponse> getAllTeams() {
        return hibernateTeamService.getAllTeams();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.H_GET_TEAM_BY_ID)
    public TeamInfoResponse getTeamById(@RequestParam("teamId") Long teamId) {
        return hibernateTeamService.getTeamById(teamId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = Routes.H_CREATE_TEAM, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createTeam(@RequestBody TeamDTO teamDTO) {
        return hibernateTeamService.createTeam(teamDTO);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = Routes.H_UPDATE_TEAM_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeamInfoResponse updateTeam(@RequestParam Long teamId, @RequestBody UpdateTeamRequest updateTeamRequest) {
        return hibernateTeamService.updateTeam(teamId, updateTeamRequest);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = Routes.H_DELETE_TEAM)
    public void deleteTeam(@RequestParam("teamId") Long teamId) {
        hibernateTeamService.deleteTeam(teamId);
    }
}