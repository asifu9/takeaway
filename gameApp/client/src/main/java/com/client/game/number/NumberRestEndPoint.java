package com.client.game.number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.game.ClientApplication;
import com.client.game.connect.EndPoint;
import com.client.game.dto.AuditDTO;
import com.client.game.dto.GamePlayerDTO;
import com.client.game.enums.GameStatus;
import com.client.game.util.RestTemplateUtil;
import com.google.gson.Gson;

/**
 * Rest end point for number game.
 *
 */
@RestController
@RequestMapping(value = "/number/endpoint/")
public class NumberRestEndPoint implements EndPoint {

	@Autowired
	NumberGame numberGame;

	@Autowired
	RestTemplateUtil restTemplateUtil;

	@Autowired
	SimpMessagingTemplate messageController;

	@Autowired
	NumberGameLogic gameLogic;

	@Value("${core.server.url}")
	private String coreServerUrl;

	@Override
	public boolean sendMessage() throws Exception {
		return false;
	}

	/**
	 * This API will be called from another player when he send updated number
	 */
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Override
	public void recieveMessage(@RequestBody AuditDTO input) throws Exception {
		numberGame.play(input);
	}

	/**
	 * This api will be called by core server to initiate the first step of the
	 * game.
	 * 
	 * @param player
	 * @throws Exception
	 */
	@PostMapping(value = "first/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void firstSend(@RequestBody GamePlayerDTO player) throws Exception {
		System.out.println("in firstSend of rest end point  class");
		System.out.println("got new data " + new Gson().toJson(player));
		System.out.println("now sending the data");

		AuditDTO input = new AuditDTO();
		input.setCurrentPlayerId(ClientApplication.currentUser.getId());
		input.setGameStatus(GameStatus.NEW);
		input.setCalculatedNumber(0);
		input.setCalcuationString("disable");
		input.setOppositePlayerId(player.getPlayer().getId());
		input.setGameInstanceId(player.getGameInstanceId().getId());
		int portToIdentify = restTemplateUtil.getPort();
		input.setIpAddress(restTemplateUtil.getIpAddress());
		input.setPort(portToIdentify);

		gameLogic.setInputDTO(input);
		input = gameLogic.process();

		saveStep(input);
		// send update to UI
		messageController.convertAndSend("/game.sendMessage", input);

		input.setCalcuationString("enable");
		restTemplateUtil.postData(
				"http://" + player.getIpAddress() + ":" + player.getPort() + "/client/number/endpoint/play", input,
				Object.class);
	}

	/**
	 * API which will be called by other player to send that data which will be
	 * pushed to UI
	 * 
	 * @param input
	 * @throws Exception
	 */
	@PostMapping(value = "play", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void playContinuos(@RequestBody AuditDTO input) throws Exception {
		messageController.convertAndSend("/game.sendMessage", input);

	}

	/**
	 * Api to consume the user action (input form user, either 1, 0 or -1) and
	 * will be processed accordingly and send update to up through web socket
	 * 
	 * @param input
	 * @throws Exception
	 */
	@PostMapping(value = "user/input", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void userAction(@RequestBody AuditDTO input) throws Exception {
		// input.setNumber(input.getResult());

		input.setCurrentPlayerId(ClientApplication.currentUser.getId());
		input.setGameInstanceId(input.getGameInstanceId());
		int targetPort = input.getPort();
		String targetIp = input.getIpAddress();

		int portToIdentify = restTemplateUtil.getPort();
		input.setIpAddress(restTemplateUtil.getIpAddress());
		input.setPort(portToIdentify);

		gameLogic.setInputDTO(input);
		input = gameLogic.process();
		if (input.getResult() == 1) {
			input.setGameStatus(GameStatus.END);
			saveStep(input);
			input.setCalcuationString("disable");
			messageController.convertAndSend("/game.sendMessage", input);
			restTemplateUtil.postData("http://" + targetIp + ":" + targetPort + "/client/number/endpoint/play", input,
					Object.class);
		} else {

			saveStep(input);
			input.setCalcuationString("disable");
			messageController.convertAndSend("/game.sendMessage", input);
			input.setCalcuationString("enable");
			restTemplateUtil.postData("http://" + targetIp + ":" + targetPort + "/client/number/endpoint/play", input,
					Object.class);
		}
	}

	/**
	 * Method to call the audit API to store data in core server
	 * 
	 * @param dto
	 */
	void saveStep(AuditDTO dto) {
		Runnable runnable2 = () -> {
			try {
				restTemplateUtil.postData(coreServerUrl + "/number/game/audit/", dto, Object.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		new Thread(runnable2).start();
		;
	}

}
