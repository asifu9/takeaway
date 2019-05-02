package com.game.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.game.server.enums.GameType;

/**
 * Entity Class to represent Game table.
 */
@Entity
@Table(name = "GAME")
public class Game implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5353654512648431306L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(nullable = false, name = "NAME")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "GAME_TYPE")
	private GameType gameType;

	public Game() {

	}

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

}
