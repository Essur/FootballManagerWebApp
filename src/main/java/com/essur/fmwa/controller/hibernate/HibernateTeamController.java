package com.essur.fmwa.controller.hibernate;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.TeamDTO;
import com.essur.fmwa.model.request.UpdateTeamRequest;
import com.essur.fmwa.service.hibernate.HibernateTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HibernateTeamController {
    private final HibernateTeamService hibernateTeamService;

    @GetMapping(value = Routes.H_GET_ALL_TEAMS)
    public ResponseEntity<?> getAllTeams(){
        return hibernateTeamService.getAllTeams();
    }

    @GetMapping(value = Routes.H_GET_TEAM_BY_ID)
    public ResponseEntity<?> getTeamById(@RequestParam("teamId") Long teamId){
        return hibernateTeamService.getTeamById(teamId);
    }

    @PostMapping(value = Routes.H_CREATE_TEAM, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO){
        return hibernateTeamService.createTeam(teamDTO);
    }

    @PutMapping(value = Routes.H_UPDATE_TEAM_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTeam(@RequestParam Long teamId, @RequestBody UpdateTeamRequest updateTeamRequest){
        return hibernateTeamService.updateTeam(teamId, updateTeamRequest);
    }

    @DeleteMapping(value = Routes.H_DELETE_TEAM)
    public ResponseEntity<?> deleteTeam(@RequestParam("teamId") Long teamId){
        return hibernateTeamService.deleteTeam(teamId);
    }
}
