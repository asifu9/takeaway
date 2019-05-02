package com.game.server.service;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * Util class for game applicaton
 *
 */
@Component
public class GameUtil {

	/**
	 * Util class to convert data from entity to DTO and vise versa
	 * 
	 * @param object
	 * @param classType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T convert(Object object, @SuppressWarnings("rawtypes") Class classType) {
		return (T) new Gson().fromJson(new Gson().toJson(object), classType);
	}

}
