package com.game.server.queue;

import com.game.server.entity.dto.GameInstanceDTO;

/**
 * Domain class to represent QueueItem
 *
 */
public class QueueItem {

	GameInstanceDTO instance;
	int playerId;

	public QueueItem(GameInstanceDTO instance, int playerId) {
		super();
		this.instance = instance;
		this.playerId = playerId;
	}

	public GameInstanceDTO getInstance() {
		return instance;
	}

	public void setInstance(GameInstanceDTO instance) {
		this.instance = instance;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instance.getGame() == null) ? 0 : instance.getGame().getId());
		result = prime * result + playerId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueueItem other = (QueueItem) obj;
		if (instance == null) {
			if (other.instance != null)
				return false;
		} else if ((instance.getGame().getId() != other.instance.getGame().getId()))
			return false;
		if (playerId != other.playerId)
			return false;
		return true;
	}

}
