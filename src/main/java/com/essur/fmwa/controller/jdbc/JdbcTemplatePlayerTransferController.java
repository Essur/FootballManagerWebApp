package com.essur.fmwa.controller.jdbc;

import com.essur.fmwa.Routes;
import com.essur.fmwa.service.jdbc.JdbcTemplatePlayerTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JdbcTemplatePlayerTransferController {
    private final JdbcTemplatePlayerTransferService jdbcTemplatePlayerTransferService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = Routes.JDBC_PLAYER_TRANSFER)
    public String playerTransfer(@RequestParam("playerId") Long playerId,
                                 @RequestParam("buyerTeamId") Long buyerTeamId) {
        return jdbcTemplatePlayerTransferService.playerTransfer(playerId, buyerTeamId);
    }
}
