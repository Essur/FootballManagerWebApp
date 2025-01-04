package com.essur.fmwa.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamRequest {
    @NotNull
    private String name;
    @NotNull
    private Integer teamCommission;
    @NotNull
    private Integer balanceUSD;
}
