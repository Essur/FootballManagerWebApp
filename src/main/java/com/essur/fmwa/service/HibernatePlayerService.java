package com.essur.fmwa.service;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.model.response.PlayerInfoResponse;
import com.essur.fmwa.utils.PlayerInfoResponseMapper;
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
        Player pEntity = new Player();
        pEntity.setFirstName(playerDTO.getFirstName());
        pEntity.setLastName(playerDTO.getLastName());
        pEntity.setMiddleName(playerDTO.getMiddleName());
        pEntity.setAge(playerDTO.getAge());
        pEntity.setExperience(playerDTO.getExperience());

        Team team = entityManager.find(Team.class, playerDTO.getTeamId());

        if (team == null) {
            return new ResponseEntity<>("Team with id " + playerDTO.getTeamId() + " not found", HttpStatus.NOT_FOUND);
        }
        pEntity.setTeam(team);

        entityManager.persist(pEntity);
        return new ResponseEntity<>(pEntity.getId(), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> deletePlayerById(Long playerId) {
        Player player = entityManager.find(Player.class, playerId);
        if (player == null) {
            return new ResponseEntity<>("Player with id " + playerId + " was not found", HttpStatus.NOT_FOUND);
        }
        entityManager.remove(player);
        return new ResponseEntity<>("Player was successfully deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> getPlayerById(Long playerId) {
        Player playerById = entityManager.find(Player.class, playerId);
        if (playerById == null) {
            return new ResponseEntity<>("Player was not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                new PlayerInfoResponse(
                        playerById.getId(),
                        playerById.getFirstName() + " " + playerById.getLastName() + " " + playerById.getMiddleName(),
                        playerById.getExperience(),
                        playerById.getAge(),
                        playerById.getTeam().getName()
                ), HttpStatus.FOUND);
    }

    @Transactional
    public ResponseEntity<?> updatePlayerById(Long playerId, UpdatePlayerRequest playerDTO) {
        Player pEntity = entityManager.find(Player.class, playerId);
        if (pEntity == null) {
            return new ResponseEntity<>("Player was not found", HttpStatus.NOT_FOUND);
        }
        pEntity.setFirstName(playerDTO.getFirstName());
        pEntity.setLastName(playerDTO.getLastName());
        pEntity.setMiddleName(playerDTO.getMiddleName());
        pEntity.setAge(playerDTO.getAge());
        pEntity.setExperience(playerDTO.getExperience());
        entityManager.merge(pEntity);
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


}