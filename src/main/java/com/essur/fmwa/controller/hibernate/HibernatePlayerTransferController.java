package com.essur.fmwa.controller.hibernate;

import com.essur.fmwa.Routes;
import com.essur.fmwa.service.hibernate.HibernatePlayerTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HibernatePlayerTransferController {
    private final HibernatePlayerTransferService hibernatePlayerTransferService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = Routes.H_PLAYER_TRANSFER)
    public String playerTransfer(@RequestParam("playerId") Long playerId,
                                 @RequestParam("buyerTeamId") Long buyerTeamId) {
        return hibernatePlayerTransferService.playerTransfer(playerId, buyerTeamId);
    }
}