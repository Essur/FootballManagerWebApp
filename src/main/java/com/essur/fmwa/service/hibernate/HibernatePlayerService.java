package com.essur.fmwa.service.hibernate;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.exception.DataNotFoundException;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.model.response.PlayerInfoResponse;
import com.essur.fmwa.utils.mapper.PlayerInfoResponseMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernatePlayerService {
    private final EntityManager entityManager;

    @Transactional
    public Long createPlayer(PlayerDTO playerDTO) {
        Player playerEntity = new Player();

        playerEntity.setFirstName(playerDTO.getFirstName());
        playerEntity.setLastName(playerDTO.getLastName());
        playerEntity.setMiddleName(playerDTO.getMiddleName());
        playerEntity.setAge(playerDTO.getAge());
        playerEntity.setExperience(playerDTO.getExperience());

        Team team = entityManager.find(Team.class, playerDTO.getTeamId());

        if (team == null) {
            throw new DataNotFoundException("Team with id " + playerDTO.getTeamId() + " not found");
        }
        playerEntity.setTeam(team);

        entityManager.persist(playerEntity);
        return playerEntity.getId();
    }

    @Transactional
    public void deletePlayerById(Long playerId) {
        Player player = entityManager.find(Player.class, playerId);
        if (player == null) {
            throw new DataNotFoundException("Player with id " + playerId + " was not found");
        }
        entityManager.remove(player);
    }

    public PlayerInfoResponse getPlayerById(Long playerId) {
        Player playerById = entityManager.find(Player.class, playerId);
        if (playerById == null) {
            throw new DataNotFoundException("Player with id " + playerId + " was not found");
        }
        PlayerInfoResponse response = new PlayerInfoResponse();

        response.setPlayerId(playerById.getId());
        response.setPlayerName(playerById.getFirstName() + " " + playerById.getLastName() + " " + playerById.getMiddleName());
        response.setExperience(playerById.getExperience());
        response.setAge(playerById.getAge());
        response.setTeam(playerById.getTeam().getName());

        return response;
    }

    @Transactional
    public PlayerInfoResponse updatePlayerById(Long playerId, UpdatePlayerRequest updatePlayerRequest) {
        Player playerEntity = entityManager.find(Player.class, playerId);
        if (playerEntity == null) {
            throw new DataNotFoundException("Player with id " + playerId + " was not found");
        }
        playerEntity.setFirstName(updatePlayerRequest.getFirstName());
        playerEntity.setLastName(updatePlayerRequest.getLastName());
        playerEntity.setMiddleName(updatePlayerRequest.getMiddleName());
        playerEntity.setAge(updatePlayerRequest.getAge());
        playerEntity.setExperience(updatePlayerRequest.getExperience());

        Team team = entityManager.find(Team.class, updatePlayerRequest.getTeamId());

        if (team == null) {
            throw new DataNotFoundException("Team with id " + updatePlayerRequest.getTeamId() + " not found");
        }
        playerEntity.setTeam(team);

        entityManager.merge(playerEntity);

        return PlayerInfoResponseMapper.getPlayerInfoResponse(playerEntity);
    }


    public List<PlayerInfoResponse> getAllPlayers() {
        List<Player> players = entityManager
                .createQuery("SELECT p FROM Player p", Player.class)
                .getResultList();

        List<PlayerInfoResponse> response = PlayerInfoResponseMapper.getPlayerInfoResponse(players);
        if (response.isEmpty()) {
            throw new DataNotFoundException("No players found");
        }
        return response;
    }

    public Player getPlayerEntityById(Long playerId) {
        return entityManager.find(Player.class, playerId);
    }
}