package com.essur.fmwa.controller;

import com.essur.fmwa.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
}
