package com.essur.fmwa.controller.jdbc;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.service.jdbc.JdbcTemplatePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JdbcTemplatePlayerController {
    private final JdbcTemplatePlayerService jdbcTemplatePlayerService;

    @PostMapping(value = Routes.JDBC_CREATE_PLAYER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDTO playerDTO) {
        return jdbcTemplatePlayerService.createPlayer(playerDTO);
    }

    @GetMapping(value = Routes.JDBC_GET_PLAYER_BY_ID)
    public ResponseEntity<?> getPlayerById(@RequestParam Long playerId){
        return jdbcTemplatePlayerService.getPlayerById(playerId);
    }

    @GetMapping(value = Routes.JDBC_GET_ALL_PLAYERS)
    public ResponseEntity<?> getAllPlayers() {
        return jdbcTemplatePlayerService.getAllPlayers();
    }

    @PutMapping(value = Routes.JDBC_UPDATE_PLAYER_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePlayer(@RequestParam Long playerId, @RequestBody UpdatePlayerRequest updatePlayerRequest) {
        return jdbcTemplatePlayerService.updatePlayer(playerId, updatePlayerRequest);
    }

    @DeleteMapping(value = Routes.JDBC_DELETE_PLAYER)
    public ResponseEntity<?> deletePlayer(@RequestParam Long playerId) {
        return jdbcTemplatePlayerService.deletePlayer(playerId);
    }
}
