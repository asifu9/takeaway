package com.client.game.number;

import org.springframework.stereotype.Component;

import com.client.game.ClientApplication;
import com.client.game.core.IGameLogic;
import com.client.game.dto.AuditDTO;
import com.client.game.enums.GameStatus;

@Component
public class NumberGameLogic implements IGameLogic {

	AuditDTO inputDTO;

	int startNumber;

	@Override
	public AuditDTO process() throws Exception {
		System.out.println("in process of game logic");
		if (inputDTO.getGameStatus() == GameStatus.NEW) {
			// its a new game, get a random number between 10 to 100
			if (startNumber < 3) {
				throw new Exception("Start number cannot be less than 3");
			}
			inputDTO.setGameStatus(GameStatus.RUNNING);
			if (ClientApplication.currentUser != null) {
				inputDTO.setCurrentPlayerId(ClientApplication.currentUser.getId());
			}

			inputDTO.setNumber(startNumber);

			int number = startNumber;
			// process the number for first time
			if (number % 3 == 0) {
				inputDTO.setCalculatedNumber(0);
				inputDTO.setResult(number / 3);
				// that means we are adding a zero here
			} else if ((number - 1) % 3 == 0) {
				inputDTO.setCalculatedNumber(-1);
				inputDTO.setResult((number - 1) / 3);
			} else {
				inputDTO.setCalculatedNumber(1);
				inputDTO.setResult((number + 1) / 3);
			}
		} else {
			// get the result from UI and do the calculation
			inputDTO.setResult((inputDTO.getNumber() + inputDTO.getCalculatedNumber()) / 3);

		}

		if (inputDTO.getResult() == 1) {
			// this means game is over
			inputDTO.setGameStatus(GameStatus.END);
		}

		return inputDTO;
	}

	public AuditDTO getInputDTO() {
		return inputDTO;
	}

	public void setInputDTO(AuditDTO inputDTO) {
		this.inputDTO = inputDTO;
	}

	public NumberGameLogic() {
		super();
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

}
