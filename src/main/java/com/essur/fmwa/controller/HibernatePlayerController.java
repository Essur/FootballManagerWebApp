package com.essur.fmwa.controller;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.service.HibernatePlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HibernatePlayerController {
    private final HibernatePlayerService hibernatePlayerService;

    @GetMapping(value = Routes.H_GET_ALL_PLAYERS)
    public ResponseEntity<?> getAllPlayers() {
        return hibernatePlayerService.getAllPlayers();
    }

    @PostMapping(value = Routes.H_CREATE_PLAYER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDTO playerDTO) {
        log.info("Creating player: {}", playerDTO);
        return hibernatePlayerService.createPlayer(playerDTO);
    }

    @PutMapping(value = Routes.H_UPDATE_PLAYER_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePlayer(@RequestParam("playerId") Long playerId, @RequestBody UpdatePlayerRequest playerDTO) {
        log.info("Updating player: {}", playerDTO);
        return hibernatePlayerService.updatePlayerById(playerId, playerDTO);
    }

    @GetMapping(value = Routes.H_GET_PLAYER_BY_ID)
    public ResponseEntity<?> getPlayerById(@RequestParam Long playerId) {
        return hibernatePlayerService.getPlayerById(playerId);
    }

    @DeleteMapping(value = Routes.H_DELETE_PLAYER)
    public ResponseEntity<?> deletePlayer(@RequestParam Long playerId) {
        return hibernatePlayerService.deletePlayerById(playerId);
    }

}
