package com.essur.fmwa.controller.hibernate;

import com.essur.fmwa.Routes;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.model.request.UpdatePlayerRequest;
import com.essur.fmwa.model.response.PlayerInfoResponse;
import com.essur.fmwa.service.hibernate.HibernatePlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HibernatePlayerController {
    private final HibernatePlayerService hibernatePlayerService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.H_GET_ALL_PLAYERS)
    public List<PlayerInfoResponse> getAllPlayers() {
        return hibernatePlayerService.getAllPlayers();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = Routes.H_CREATE_PLAYER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createPlayer(@RequestBody PlayerDTO playerDTO) {
        log.info("Creating player: {}", playerDTO);
        return hibernatePlayerService.createPlayer(playerDTO);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = Routes.H_UPDATE_PLAYER_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlayerInfoResponse updatePlayer(@RequestParam("playerId") Long playerId,
                                           @RequestBody UpdatePlayerRequest playerDTO) {
        return hibernatePlayerService.updatePlayerById(playerId, playerDTO);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = Routes.H_GET_PLAYER_BY_ID)
    public PlayerInfoResponse getPlayerById(@RequestParam Long playerId) {
        return hibernatePlayerService.getPlayerById(playerId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(value = Routes.H_DELETE_PLAYER)
    public void deletePlayer(@RequestParam Long playerId) {
        hibernatePlayerService.deletePlayerById(playerId);
    }

}
