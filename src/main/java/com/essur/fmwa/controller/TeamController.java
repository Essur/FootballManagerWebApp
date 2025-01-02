package com.essur.fmwa.controller;

import com.essur.fmwa.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

}
