package com.essur.fmwa.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfoResponse {
    private Long playerId;
    private String playerName;
    private Integer experience;
    private Integer age;
    private String team;
}
