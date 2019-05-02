package com.game.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.server.entity.NumberGameAudit;

/**
 * The Interface GameInstancerRepository.
 */
@Repository
public interface NumberGameAuditRepository extends JpaRepository<NumberGameAudit, Integer> {

	List<NumberGameAudit> findByGameInstanceId(int gameInstanceId);
}