package com.essur.fmwa.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HibernateTeamService {
    private final EntityManager entityManager;


}
