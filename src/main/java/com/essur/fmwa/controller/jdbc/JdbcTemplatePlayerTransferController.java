package com.essur.fmwa.controller.jdbc;

import com.essur.fmwa.Routes;
import com.essur.fmwa.service.jdbc.JdbcTemplatePlayerTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JdbcTemplatePlayerTransferController {
    private final JdbcTemplatePlayerTransferService jdbcTemplatePlayerTransferService;

    @PostMapping(value = Routes.JDBC_PLAYER_TRANSFER)
    public ResponseEntity<?> playerTransfer(@RequestParam("playerId") Long playerId,
                                            @RequestParam("buyerTeamId") Long buyerTeamId){
        return jdbcTemplatePlayerTransferService.playerTransfer(playerId, buyerTeamId);
    }
}
