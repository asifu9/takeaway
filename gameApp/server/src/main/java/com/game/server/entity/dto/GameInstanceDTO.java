package com.game.server.entity.dto;

import com.game.server.enums.GameStatus;

/**
 * DTO class for Game Instance
 *
 */
public class GameInstanceDTO {

	private int id;

	private GameDTO game;

	private GameStatus status;

	private boolean allPlayersAvailable = false;

	private int wonByPlayerId;

	/**
	 * Instantiates a new game instance.
	 */
	public GameInstanceDTO() {
	}

	public GameInstanceDTO(GameDTO game, GameStatus status) {
		this.game = game;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// public List<GamePlayerDTO> getGamePlayers() {
	// return gamePlayers;
	// }
	//
	// public void setGamePlayers(List<GamePlayerDTO> gamePlayers) {
	// this.gamePlayers = gamePlayers;
	// }

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	// public void addPlayer(GamePlayerDTO gamePlayer){
	// if(!gamePlayers.contains(gamePlayer)){
	// gamePlayers.add(gamePlayer);
	// }
	// }

	public GameDTO getGame() {
		return game;
	}

	public void setGame(GameDTO game) {
		this.game = game;
	}

	public boolean isAllPlayersAvailable() {
		return allPlayersAvailable;
	}

	public void setAllPlayersAvailable(boolean allPlayersAvailable) {
		this.allPlayersAvailable = allPlayersAvailable;
	}

	private String ipAddress;
	private int port;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getWonByPlayerId() {
		return wonByPlayerId;
	}

	public void setWonByPlayerId(int wonByPlayerId) {
		this.wonByPlayerId = wonByPlayerId;
	}

}
