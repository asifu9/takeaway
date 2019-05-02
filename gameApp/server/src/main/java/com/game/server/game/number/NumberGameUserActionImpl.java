package com.game.server.game.number;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.entity.dto.GamePlayerDTO;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.enums.GameStatus;
import com.game.server.queue.NumberGamePlayersQueue;
import com.game.server.queue.QueueItem;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GamePlayerService;
import com.game.server.service.GameUtil;
import com.game.server.service.PlayerService;
import com.game.server.util.RestTemplateUtil;

@Component
public class NumberGameUserActionImpl implements IGameUserAction {

	@Autowired
	NumberGamePlayersQueue queue;

	@Autowired
	GameInstanceService gameInstanceService;

	@Autowired
	GamePlayerService gamePlayerService;

	@Autowired
	PlayerService playerService;

	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@Autowired
	GameUtil gameUtil;

	@Override
	public synchronized void startGame(GameInstanceDTO instance, int currentUserId) throws Exception {
		// put the new entry into queue
		queue.put(new QueueItem(instance, currentUserId));
		process();
	}

	/**
	 * This method will check, if two users available in queue then it will take
	 * those two players and start a new game.
	 * 
	 * @throws Exception
	 */
	void process() throws Exception {

		// this dequeue will pull out 2 players info from queue
		List<QueueItem> players = queue.remove2Items();
		if (players != null) {

			// lets start a game between these two players
			GameInstanceDTO gameInstance = new GameInstanceDTO(players.get(0).getInstance().getGame(), GameStatus.NEW);
			gameInstance.setAllPlayersAvailable(true);
			// lets create new game instance object, later user can see the
			// details of the game using this.
			gameInstance = gameInstanceService.create(gameInstance);
			boolean isInitiator = false;
			GamePlayerDTO otherPlayer = null;

			for (QueueItem player : players) {

				PlayerDTO playerDTO = playerService.findById(player.getPlayerId());
				GamePlayerDTO dto = new GamePlayerDTO(isInitiator, Calendar.getInstance().getTimeInMillis(), 0, 0,
						GameStatus.NEW, gameInstance, playerDTO, player.getInstance().getIpAddress(),
						player.getInstance().getPort());

				// make any one as game initiator
				if (isInitiator) {
					dto.setGameInstanceId(gameInstance);
					restTemplateUtil.postData(
							"http://" + dto.getIpAddress() + ":" + dto.getPort() + "/client/number/endpoint/first/",
							otherPlayer, Object.class);
				} else {
					otherPlayer = dto;
				}
				isInitiator = true;
				// lets create an entry for both players in Game PLayer table.
				gamePlayerService.create(dto);

			}

		}
	}

	@Override
	public void pauseGame() throws Exception {
		throw new Exception("This function is not supported in Number Game");
	}

	@Override
	public void resumeGame() throws Exception {
		throw new Exception("This function is not supported in Number Game");
	}

	@Override
	public void stopGame() throws Exception {
		throw new Exception("This function is not supported in Number Game");
	}

	public RestTemplateUtil getRestTemplateUtil() {
		return restTemplateUtil;
	}

	public void setRestTemplateUtil(RestTemplateUtil restTemplateUtil) {
		this.restTemplateUtil = restTemplateUtil;
	}

}
