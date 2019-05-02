package com.game.server.entity.dto;

/**
 * DTO class for Player
 *
 */
public class PlayerDTO {

	public PlayerDTO(int id, String firstName, String lastName, long dateOfBirth, String userName,
			String profileImagePath, String ipAddress, int port) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.userName = userName;
		this.profileImagePath = profileImagePath;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public PlayerDTO(String firstName, String lastName, long dateOfBirth, String userName, String profileImagePath,
			String ipAddress, int port) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.userName = userName;
		this.profileImagePath = profileImagePath;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public PlayerDTO() {
	}

	private int id;

	private String firstName;

	private String lastName;

	private long dateOfBirth;

	private String userName;

	private String profileImagePath;
	
	private String ipAddress;
	
	private int port;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
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
