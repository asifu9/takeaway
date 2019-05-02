package com.game.server.service;

import java.util.List;

import com.game.server.entity.NumberGameAudit;
import com.game.server.entity.dto.AuditDTO;

/**
 * This interface represents the operation in number game audit table
 */
public interface NumberGameAuditService {

	/**
	 * Method to create new audit
	 * 
	 * @param numberGameAudit
	 * @return
	 * @throws Exception
	 */
	public NumberGameAudit create(NumberGameAudit numberGameAudit) throws Exception;

	/**
	 * Method to create new audit using audit DTO
	 * 
	 * @param audit
	 * @return
	 * @throws Exception
	 */
	public AuditDTO create(AuditDTO audit) throws Exception;

	/**
	 * Method to find the audit details for given game instance id
	 * 
	 * @param gameInstanceId
	 * @return
	 * @throws Exception
	 */
	public List<NumberGameAudit> findByGameInstanceId(int gameInstanceId) throws Exception;
	
	/**
	 * Method to delete all game audits
	 */
	public void deleteAll();

}
