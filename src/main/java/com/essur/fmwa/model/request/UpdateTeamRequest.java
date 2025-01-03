package com.essur.fmwa.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamRequest {
    private String name;
    private Integer teamCommission;
    private Integer balanceUSD;
}
