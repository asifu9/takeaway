package com.client.game.connect;

import com.client.game.dto.AuditDTO;

public interface EndPoint {

	public boolean sendMessage() throws Exception; 

	void recieveMessage(AuditDTO input) throws Exception;
}
