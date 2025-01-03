package com.essur.fmwa.service;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.utils.calculator.TotalPaymentCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public abstract class AbstractPlayerTransferService {
    public ResponseEntity<?> playerTransfer(Long playerId, Long buyerTeamId) {
        Player player = getPlayerById(playerId);
        Team buyerTeam = getTeamById(buyerTeamId);

        if (player == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NO_CONTENT);
        } else if (buyerTeam == null) {
            return new ResponseEntity<>("Team not found", HttpStatus.NO_CONTENT);
        } else if (Objects.equals(player.getTeam().getId(), buyerTeam.getId())) {
            return new ResponseEntity<>("Could not transfer player from the same team", HttpStatus.CONFLICT);
        }

        Team sellerTeam = player.getTeam();

        double totalPayment = TotalPaymentCalculator.calculateTotalPayment(player, sellerTeam);

        if (buyerTeam.getBalance() < totalPayment) {
            return new ResponseEntity<>("Buyer team does not have enough funds.", HttpStatus.BAD_REQUEST);
        }

        totalPayment = Math.round(totalPayment);

        buyerTeam.setBalance(buyerTeam.getBalance() - (int) totalPayment);
        sellerTeam.setBalance(sellerTeam.getBalance() + (int) totalPayment);
        player.setTeam(buyerTeam);

        updateTeamData(buyerTeam);
        updateTeamData(sellerTeam);
        updatePlayerData(player);

        return new ResponseEntity<>("Player " + player.getLastName() + " " + player.getFirstName() +
                                    " was moved from " + sellerTeam.getName() + " to " + buyerTeam.getName(), HttpStatus.OK);
    }

    protected abstract Player getPlayerById(Long playerId);

    protected abstract Team getTeamById(Long teamId);

    protected abstract void updateTeamData(Team team);

    protected abstract void updatePlayerData(Player player);
}
