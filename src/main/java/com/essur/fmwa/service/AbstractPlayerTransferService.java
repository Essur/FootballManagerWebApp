package com.essur.fmwa.service;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;
import com.essur.fmwa.exception.BadRequestException;
import com.essur.fmwa.exception.TransferRequestException;
import com.essur.fmwa.utils.calculator.TotalPaymentCalculator;
import jakarta.transaction.Transactional;

import java.util.Objects;

public abstract class AbstractPlayerTransferService {
    @Transactional
    public String playerTransfer(Long playerId, Long buyerTeamId) {
        Player player = getPlayerById(playerId);
        Team buyerTeam = getTeamById(buyerTeamId);

        if (player == null) {
            throw new BadRequestException("Player with id " + playerId + " was not found");
        } else if (buyerTeam == null) {
            throw new BadRequestException("Buyer team with id " + buyerTeamId + " was not found");
        } else if (Objects.equals(player.getTeam().getId(), buyerTeam.getId())) {
            throw new BadRequestException("Player with id " + playerId + " is already transferred");
        }

        Team sellerTeam = player.getTeam();

        double totalPayment = TotalPaymentCalculator.calculateTotalPayment(player, sellerTeam);

        if (buyerTeam.getBalance() < totalPayment) {
            throw new TransferRequestException("Buyer team balance is less than the total payment sum");
        }

        totalPayment = Math.round(totalPayment);

        buyerTeam.setBalance(buyerTeam.getBalance() - (int) totalPayment);
        sellerTeam.setBalance(sellerTeam.getBalance() + (int) totalPayment);
        player.setTeam(buyerTeam);

        updateTeamData(buyerTeam);
        updateTeamData(sellerTeam);
        updatePlayerData(player);

        return "Player " + player.getLastName() + " " + player.getFirstName() +
               " was moved from " + sellerTeam.getName() + " to " + buyerTeam.getName();
    }

    protected abstract Player getPlayerById(Long playerId);

    protected abstract Team getTeamById(Long teamId);

    protected abstract void updateTeamData(Team team);

    protected abstract void updatePlayerData(Player player);
}
