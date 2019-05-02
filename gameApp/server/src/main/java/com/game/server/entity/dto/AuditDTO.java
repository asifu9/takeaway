package com.game.server.entity.dto;

import com.game.server.enums.GameStatus;
/**
 * DTO class for Auditing game information
 *
 */
public class AuditDTO {

	private int number;
	private int calculatedNumber;
	private int result;
	private String calcuationString;
	private GameStatus gameStatus;
	private int currentPlayerId;
	private int oppositePlayerId;
	private int gameInstanceId;
	private String ipAddress;
	private int port;

	public AuditDTO() {
	}

	public AuditDTO(int number, int calculatedNumber, int result, String calcuationString, GameStatus gameStatus,
			int currentPlayerId, int oppositePlayerId, int gameInstanceId, String ipAddress, int port) {
		this.number = number;
		this.calculatedNumber = calculatedNumber;
		this.result = result;
		this.calcuationString = calcuationString;
		this.gameStatus = gameStatus;
		this.currentPlayerId = currentPlayerId;
		this.oppositePlayerId = oppositePlayerId;
		this.gameInstanceId = gameInstanceId;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCalculatedNumber() {
		return calculatedNumber;
	}

	public void setCalculatedNumber(int calculatedNumber) {
		this.calculatedNumber = calculatedNumber;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getCalcuationString() {
		return calcuationString;
	}

	public void setCalcuationString(String calcuationString) {
		this.calcuationString = calcuationString;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}

	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}

	public int getOppositePlayerId() {
		return oppositePlayerId;
	}

	public void setOppositePlayerId(int oppositePlayerId) {
		this.oppositePlayerId = oppositePlayerId;
	}

	@Override
	public String toString() {
		return "InputDTO [number=" + number + ", calculatedNumber=" + calculatedNumber + ", result=" + result
				+ ", calcuationString=" + calcuationString + ", gameStatus=" + gameStatus + ", currentPlayerId="
				+ currentPlayerId + ", oppositePlayerId=" + oppositePlayerId + ", gameInstanceId=" + gameInstanceId
				+ "]";
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

	public int getGameInstanceId() {
		return gameInstanceId;
	}

	public void setGameInstanceId(int gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

}
