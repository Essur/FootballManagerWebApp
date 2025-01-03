package com.essur.fmwa.utils.mapper;

import com.essur.fmwa.entity.Team;
import com.essur.fmwa.model.response.TeamInfoResponse;

import java.util.List;

public interface TeamInfoResponseMapper {
    static List<TeamInfoResponse> getTeamInfoResponse(List<Team> teams){
        return teams.stream().map(team -> new TeamInfoResponse(
                team.getId(),
                team.getName(),
                team.getTeamCommission(),
                team.getBalance(),
                team.getPlayers().stream().map(p -> PlayerInfoResponseMapper.getPlayerDTO(p, team.getId())).toList()
        )).toList();
    }

    static TeamInfoResponse getTeamInfoResponse(Team team){
        return new TeamInfoResponse(
                team.getId(),
                team.getName(),
                team.getTeamCommission(),
                team.getBalance(),
                team.getPlayers().stream().map(p -> PlayerInfoResponseMapper.getPlayerDTO(p, team.getId())).toList());
    }
}
