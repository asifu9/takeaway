package com.game.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class to represent an player info
 *
 */
@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {

	private static final long serialVersionUID = -4061333313445012410L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(nullable = false, name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DATE_OF_BIRTH")
	private long dateOfBirth;

	@Column(nullable = false, name = "USER_NAME")
	private String userName;

	@Column(name = "PROFILE_IMAGE_PATH")
	private String profileImagePath;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "PORT")
	private int port;

	public Player(String firstName, String lastName, String userName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}

	/**
	 * Instantiates a new player.
	 */
	public Player() {

	}

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