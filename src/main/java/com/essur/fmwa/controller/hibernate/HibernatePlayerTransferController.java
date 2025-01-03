package com.essur.fmwa.controller.hibernate;

import com.essur.fmwa.Routes;
import com.essur.fmwa.service.hibernate.HibernatePlayerTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HibernatePlayerTransferController {
    private final HibernatePlayerTransferService hibernatePlayerTransferService;

    @PostMapping(value = Routes.H_PLAYER_TRANSFER)
    public ResponseEntity<?> playerTransfer(@RequestParam("playerId") Long playerId,
                                            @RequestParam("buyerTeamId") Long buyerTeamId) {
        return hibernatePlayerTransferService.playerTransfer(playerId, buyerTeamId);
    }
}