package com.essur.fmwa.controller.jdbc;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.model.response.PlayerInfoResponse;
import com.essur.fmwa.service.jdbc.JdbcTemplatePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JdbcTemplatePlayerController {
    private final JdbcTemplatePlayerService jdbcTemplatePlayerService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = Routes.JDBC_CREATE_PLAYER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createPlayer(@RequestBody PlayerDTO playerDTO) {
        return jdbcTemplatePlayerService.createPlayer(playerDTO);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.JDBC_GET_PLAYER_BY_ID)
    public PlayerInfoResponse getPlayerById(@RequestParam Long playerId){
        return jdbcTemplatePlayerService.getPlayerById(playerId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.JDBC_GET_ALL_PLAYERS)
    public List<PlayerInfoResponse> getAllPlayers() {
        return jdbcTemplatePlayerService.getAllPlayers();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = Routes.JDBC_UPDATE_PLAYER_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlayerInfoResponse updatePlayer(@RequestParam Long playerId,
                                           @RequestBody UpdatePlayerRequest updatePlayerRequest) {
        return jdbcTemplatePlayerService.updatePlayer(playerId, updatePlayerRequest);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = Routes.JDBC_DELETE_PLAYER)
    public void deletePlayer(@RequestParam Long playerId) {
        jdbcTemplatePlayerService.deletePlayer(playerId);
    }
}
