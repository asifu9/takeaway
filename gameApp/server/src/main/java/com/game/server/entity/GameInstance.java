package com.game.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.game.server.enums.GameStatus;

@Entity
@Table(name = "GAME_INSTANCE")
public class GameInstance implements Serializable {

	private static final long serialVersionUID = -7711991132251014108L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@OneToOne
	@JoinColumn(name = "GAME_ID")
	private Game game;

	@Column(nullable = false, name = "GAME_STATUS")
	@Enumerated(EnumType.STRING)
	private GameStatus status;

	@Column(nullable = false, name = "ALL_PLAYERS_AVAILABLE")
	private boolean allPlayersAvailable = false;

	@Column(nullable = false, name = "WON_BY_PLAYER_ID")
	private int wonByPlayerId;

	public GameInstance() {
		super();
	}

	public GameInstance(Game game, GameStatus status) {
		this.game = game;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isAllPlayersAvailable() {
		return allPlayersAvailable;
	}

	public void setAllPlayersAvailable(boolean allPlayersAvailable) {
		this.allPlayersAvailable = allPlayersAvailable;
	}

	public int getWonByPlayerId() {
		return wonByPlayerId;
	}

	public void setWonByPlayerId(int wonByPlayerId) {
		this.wonByPlayerId = wonByPlayerId;
	}

}
