package com.essur.fmwa.model.response;

import com.essur.fmwa.model.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamInfoResponse {
    private Long teamId;
    private String teamName;
    private Integer commission;
    private Integer balance;
    private List<PlayerDTO> players;
}
