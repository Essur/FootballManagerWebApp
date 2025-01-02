package com.essur.fmwa.model.response;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.model.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamInfoResponse {
    private String teamName;
    private String teamId;
    private List<PlayerDTO> players;
}
