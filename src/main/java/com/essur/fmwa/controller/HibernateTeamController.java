package com.essur.fmwa.controller;

import com.essur.fmwa.service.HibernateTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HibernateTeamController {
    private final HibernateTeamService hibernateTeamService;

}
