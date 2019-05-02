package com.client.game.number;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.client.game.ClientApplication;
import com.client.game.core.IGame;
import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameInstanceDTO;
import com.client.game.enums.GameStatus;
import com.client.game.util.RestTemplateUtil;

@Component
public class NumberGame implements IGame {

	private NumberGameSession numberGameSession;

	@Autowired
	NumberGameLogic gameLogic;

	@Autowired
	private RestTemplateUtil restTemplate;

	@Value("${core.server.url}")
	private String coreServerUrl;

	@Value("${game.upperLimit}")
	int upperLimit;

	@Value("${game.lowerLimit}")
	int lowerLimit;

	@Autowired
	MessageController messageController;

	BlockingQueue<AuditDTO> queue = new ArrayBlockingQueue<>(10);

	public NumberGame() {

	}

	/**
	 * Method to start a new game
	 */
	@Override
	public void startNewGame(GameInstanceDTO gameInstanceDTO) throws Exception {

		this.numberGameSession = new NumberGameSession();
		numberGameSession.setGameInstance(gameInstanceDTO);

		// lets create an
		AuditDTO audit = new AuditDTO();
		audit.setCurrentPlayerId(ClientApplication.currentUser.getId());
		audit.setGameId(gameInstanceDTO.getGame().getId());
		audit.setGameStatus(GameStatus.NEW);
		audit.setCalculatedNumber(0);

		gameLogic.setInputDTO(audit);
		// get the initial starting number
		gameLogic.setStartNumber(((int) (Math.random() * (upperLimit - lowerLimit)) + upperLimit));
		audit = gameLogic.process();

		gameInstanceDTO.setIpAddress(restTemplate.getIpAddress());
		gameInstanceDTO.setPort(restTemplate.getPort());
		messageController.sendMessage(audit);
		restTemplate.postData(coreServerUrl + "/manage/game/" + ClientApplication.currentUser.getId(), gameInstanceDTO,
				GameInstanceDTO.class);

		numberGameSession.getPlayerMoves().add(audit);

	}

	/**
	 * Method to play the game but process the input and sending back the result
	 */
	@Override
	public void play(AuditDTO input) throws Exception {

		input.setCurrentPlayerId(ClientApplication.currentUser.getId());
		input.setGameId(numberGameSession.getGameInstance().getGame().getId());
		input.setGameStatus(GameStatus.RUNNING);
		gameLogic.setInputDTO(input);
		numberGameSession.getPlayerMoves().add(input);
		input = gameLogic.process();
		restTemplate.postData(coreServerUrl + "/game/number/input/", input, AuditDTO.class);
		messageController.sendMessage(input);
		String targetIpAddress = input.getIpAddress();
		int targetPort = input.getPort();
		String targetUrl = "http://" + targetIpAddress + ":" + targetPort + "/client/number/endpoint/0";
		// saveStep(input);
		restTemplate.postData(targetUrl, input, AuditDTO.class);
	}

	@Override
	public void stopGame(int gameId) throws Exception {
		throw new Exception("Not supported for this game");
	}

	@Override
	public void pauseGame(int gameId) throws Exception {
		throw new Exception("Not supported for this game");

	}

	public NumberGameSession getNumberGameSession() {
		return numberGameSession;
	}

	public void setNumberGameSession(NumberGameSession numberGameSession) {
		this.numberGameSession = numberGameSession;
	}

}
