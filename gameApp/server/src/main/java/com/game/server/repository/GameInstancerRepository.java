package com.game.server.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.server.entity.GameInstance;



/**
 * The Interface GameInstancerRepository.
 */
@Repository
public interface GameInstancerRepository extends JpaRepository<GameInstance, Integer> {

}