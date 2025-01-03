package com.essur.fmwa.utils.calculator;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.entity.Team;

public interface TotalPaymentCalculator {
    static double calculateTotalPayment(Player player, Team sellerTeam){
        Integer coefficientOfTransfer = 100000;

        double transferPrice = (double) (player.getExperience() * coefficientOfTransfer) / player.getAge();
        double commission = transferPrice * ((double) sellerTeam.getTeamCommission() / 100);
        return transferPrice + commission;
    }
}
