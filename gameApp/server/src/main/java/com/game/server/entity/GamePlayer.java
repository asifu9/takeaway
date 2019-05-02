
package com.game.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.game.server.enums.GameStatus;

/**
 * Entity Class to represent Game Player table.
 */
@Entity
@Table(name = "GAME_PLAYER")
public class GamePlayer implements Serializable {

	private static final long serialVersionUID = 6206012906416907040L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	@Column(name = "IS_INITIATOR")
	private boolean isInitiator;

	@Column(name = "CREATED_ON")
	private long createdOn;

	@Column(name = "SCORE")
	private float score;

	@Column(name = "SAVED_STATE")
	private int savedState;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private GameStatus status;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gameInstanceId")
	private GameInstance gameInstanceId;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "PORT")
	private int port;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GamePlayer() {
	}

	public GamePlayer(GameStatus status, GameInstance gameInstance) {
		this.status = status;
		this.gameInstanceId = gameInstance;
	}

	public boolean isInitiator() {
		return isInitiator;
	}

	public void setInitiator(boolean isInitiator) {
		this.isInitiator = isInitiator;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getSavedState() {
		return savedState;
	}

	public void setSavedState(int savedState) {
		this.savedState = savedState;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public GameInstance getGameInstanceId() {
		return gameInstanceId;
	}

	public void setGameInstanceId(GameInstance gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

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

}
