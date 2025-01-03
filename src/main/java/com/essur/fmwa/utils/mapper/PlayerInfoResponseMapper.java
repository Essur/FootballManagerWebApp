package com.essur.fmwa.utils.mapper;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.response.PlayerInfoResponse;

import java.util.List;

public interface PlayerInfoResponseMapper {
    static List<PlayerInfoResponse> getPlayerInfoResponse(List<Player> players) {
        return players.stream().map(player -> new PlayerInfoResponse(
                player.getId(),
                player.getFirstName() + " " + player.getLastName() + " " + player.getMiddleName(),
                player.getExperience(),
                player.getAge(),
                player.getTeam().getName())).toList();
    }
    static PlayerInfoResponse getPlayerInfoResponse(Player player) {
        return new PlayerInfoResponse(
                player.getId(),
                player.getFirstName() + " " + player.getLastName() + " " + player.getMiddleName(),
                player.getExperience(),
                player.getAge(),
                player.getTeam().getName());
    }

    static PlayerDTO getPlayerDTO(Player player, Long teamId) {
        return new PlayerDTO(
                player.getFirstName(),
                player.getLastName(),
                player.getMiddleName(),
                player.getExperience(),
                player.getAge(),
                teamId);
    }
}