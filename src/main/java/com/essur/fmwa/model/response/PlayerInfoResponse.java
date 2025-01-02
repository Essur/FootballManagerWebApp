package com.essur.fmwa.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerInfoResponse {
    private Long playerId;
    private String playerName;
    private Integer experience;
    private Integer age;
    private String team;
}
