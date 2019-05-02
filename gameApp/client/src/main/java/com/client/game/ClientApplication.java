package com.client.game;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.client.game.dto.PlayerDTO;
import com.client.game.service.IPlayers;
import com.client.game.util.RestTemplateUtil;

@SpringBootApplication
@ComponentScan("com.client")
public class ClientApplication {

	public static String currentUserName;
	public static PlayerDTO currentUser;

	@Autowired
	private IPlayers managePlayers;

	
	private static int port;

	public static void main(String[] args) {
		if (args.length == 2) {
			
			currentUserName = args[1];
			setPort(args[0]);
		} else {
			System.out.println("You need to pass port and current user name in command line");
			System.exit(-1);
		}
		SpringApplication.run(ClientApplication.class, args);

	}
	
	static void setPort(String str){
		String[] spl=str.split("=");
		if(spl.length==2){
			try{
				port= Integer.parseInt(spl[1]);
			}catch(Exception ex){
				System.out.println("Port is not provided");
				System.exit(1);
			}
		}
	}

	@PostConstruct
	public void initService() throws Exception {
		if (null != currentUserName) {
			currentUser = managePlayers.getByUserName(currentUserName);
			if(currentUser==null){
				System.out.println("No user found with the given user name " + currentUserName);
				System.exit(1);
			}else if(port!=currentUser.getPort()){
				System.out.println("The given port of application "+port+" not matching with the user port " + currentUser.getPort());
				System.exit(1);
			}
		}
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
