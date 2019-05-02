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

import com.game.server.enums.GameStatus;

/**
 * Entity Class to represent Game table.
 */
@Entity
@Table(name = "NUMBER_GAME_STEP")
public class NumberGameAudit implements Serializable {

	private static final long serialVersionUID = -5353654512648431306L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(nullable = false, name = "GAME_INSTANCE_ID")
	private int gameInstanceId;

	@Enumerated(EnumType.STRING)
	@Column(name = "GAME_STATUS")
	private GameStatus gameStatus;

	@Column(nullable = false, name = "SEND_BY_PLAYER_ID")
	private int sendByPlayerId;

	@Column(nullable = false, name = "NUMBER")
	private int number;
	@Column(nullable = false, name = "USED_OPTION_NUMBER")
	private int usedOptionNumber;
	@Column(nullable = false, name = "RESULT_NUMBER")
	private int resultNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameInstanceId() {
		return gameInstanceId;
	}

	public void setGameInstanceId(int gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getSendByPlayerId() {
		return sendByPlayerId;
	}

	public void setSendByPlayerId(int sendByPlayerId) {
		this.sendByPlayerId = sendByPlayerId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getUsedOptionNumber() {
		return usedOptionNumber;
	}

	public void setUsedOptionNumber(int usedOptionNumber) {
		this.usedOptionNumber = usedOptionNumber;
	}

	public int getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(int resultNumber) {
		this.resultNumber = resultNumber;
	}

}
