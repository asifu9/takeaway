package com.game.server.queue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Component;

/**
 * Queue to hold all new players. once queue contains more than 2 players 
 * then it will dequeue 2 players at a time to start the game.
 *
 */
@Component
public class NumberGamePlayersQueue {

	private BlockingQueue<QueueItem> games = new ArrayBlockingQueue<>(20);
	Set<QueueItem> queueSet=new HashSet<>();
 
	NumberGamePlayersQueue() {

	}

	/**
	 * Method to add an entry to queue
	 * @param instance
	 * @throws InterruptedException
	 */
	public void put(QueueItem instance) throws InterruptedException {
		if(queueSet.add(instance)){
			games.put(instance);
		}
	}

	/**'
	 * Method to remove 2 items from queue
	 * @return
	 * @throws InterruptedException
	 */
	public List<QueueItem> remove2Items() throws InterruptedException {
		
		if (games.isEmpty()) {
			return null;
		}
		if(games.size()>=2){
			List<QueueItem> items=new ArrayList<>();
			QueueItem item1=games.take();
			queueSet.remove(item1);
			items.add(item1);
			QueueItem item2=games.take();
			queueSet.remove(item2);
			items.add(item2);
			return items;
		}
		return null;
	}

}
