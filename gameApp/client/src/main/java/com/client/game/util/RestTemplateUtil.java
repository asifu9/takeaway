package com.client.game.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateUtil {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	Environment environment;

	public int getPort() {
		return Integer.parseInt(environment.getProperty("local.server.port"));
	}

	public String getIpAddress() {
		// return InetAddress.getLocalHost().getHostName();
		return "localhost";
	}

	public <T> T fetchData(String url, Class<T> classType) throws Exception {
		return restTemplate.getForEntity(url, classType).getBody();
	}

	public <T> Object postData(String url, Object object, Class<T> classType) throws Exception {
		return restTemplate.postForEntity(url, object, classType);
	}

	public <T> List<T> fetch(String url, Class<T> type) throws Exception {
		ResponseEntity<List<T>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<T>>() {
				});
		return response.getBody();
	}

}
