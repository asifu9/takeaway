package com.client.game.dto;

import com.client.game.enums.GameType;

/**
 * DTO class for Game entity
 * 
 *
 */
public class GameDTO {

	public GameDTO() {
	}

	private int id;

	private String name;

	private GameType gameType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public GameDTO(int id, String name, GameType gameType) {
		this.id = id;
		this.name = name;
		this.gameType = gameType;
	}

	public GameDTO(String name, GameType gameType) {
		this.name = name;
		this.gameType = gameType;
	}

}
