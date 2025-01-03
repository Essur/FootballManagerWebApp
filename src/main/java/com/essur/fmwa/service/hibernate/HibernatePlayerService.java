package com.essur.fmwa.service.hibernate;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.model.response.PlayerInfoResponse;
import com.essur.fmwa.utils.mapper.PlayerInfoResponseMapper;
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
public class HibernatePlayerService {
    private final EntityManager entityManager;

    @Transactional
    public ResponseEntity<?> createPlayer(PlayerDTO playerDTO) {
        Player playerEntity = new Player();

        playerEntity.setFirstName(playerDTO.getFirstName());
        playerEntity.setLastName(playerDTO.getLastName());
        playerEntity.setMiddleName(playerDTO.getMiddleName());
        playerEntity.setAge(playerDTO.getAge());
        playerEntity.setExperience(playerDTO.getExperience());

        Team team = entityManager.find(Team.class, playerDTO.getTeamId());

        if (team == null) {
            return new ResponseEntity<>("Team with id " + playerDTO.getTeamId() + " not found", HttpStatus.BAD_REQUEST);
        }
        playerEntity.setTeam(team);

        entityManager.persist(playerEntity);
        return new ResponseEntity<>(playerEntity.getId(), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> deletePlayerById(Long playerId) {
        Player player = entityManager.find(Player.class, playerId);
        if (player == null) {
            return new ResponseEntity<>("Player with id " + playerId + " was not found", HttpStatus.NO_CONTENT);
        }
        entityManager.remove(player);
        return new ResponseEntity<>("Player was successfully deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> getPlayerById(Long playerId) {
        Player playerById = entityManager.find(Player.class, playerId);
        if (playerById == null) {
            return new ResponseEntity<>("Player was not found", HttpStatus.NO_CONTENT);
        }
        PlayerInfoResponse response = new PlayerInfoResponse();

        response.setPlayerId(playerById.getId());
        response.setPlayerName(playerById.getFirstName() + " " + playerById.getLastName() + " " + playerById.getMiddleName());
        response.setExperience(playerById.getExperience());
        response.setAge(playerById.getAge());
        response.setTeam(playerById.getTeam().getName());

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @Transactional
    public ResponseEntity<?> updatePlayerById(Long playerId, UpdatePlayerRequest playerDTO) {
        Player playerEntity = entityManager.find(Player.class, playerId);
        if (playerEntity == null) {
            return new ResponseEntity<>("Player was not found", HttpStatus.NO_CONTENT);
        }
        playerEntity.setFirstName(playerDTO.getFirstName());
        playerEntity.setLastName(playerDTO.getLastName());
        playerEntity.setMiddleName(playerDTO.getMiddleName());
        playerEntity.setAge(playerDTO.getAge());
        playerEntity.setExperience(playerDTO.getExperience());
        entityManager.merge(playerEntity);
        return new ResponseEntity<>("Player was successfully updated", HttpStatus.OK);
    }


    public ResponseEntity<?> getAllPlayers() {
        List<Player> players = entityManager
                .createQuery("SELECT p FROM Player p", Player.class)
                .getResultList();

        List<PlayerInfoResponse> response = PlayerInfoResponseMapper.getPlayerInfoResponse(players);

        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Player getPlayerEntityById(Long playerId) {
        return entityManager.find(Player.class, playerId);
    }
}