package com.essur.fmwa.service;

import com.essur.fmwa.entity.Player;
import com.essur.fmwa.model.PlayerDTO;
import com.essur.fmwa.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public ResponseEntity<?> createPlayer(PlayerDTO player) {

        return null;
    }
}
