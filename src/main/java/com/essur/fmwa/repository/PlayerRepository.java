package com.essur.fmwa.repository;

import com.essur.fmwa.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}