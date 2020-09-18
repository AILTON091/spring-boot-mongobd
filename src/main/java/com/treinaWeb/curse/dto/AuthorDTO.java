package com.treinaWeb.curse.dto;

import java.io.Serializable;

import com.treinaWeb.curse.domain.User;

public class AuthorDTO implements Serializable {
  
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName;
	private String secondMame;
	 
	public AuthorDTO() {
	}


	public AuthorDTO(User obj) {
		super();
		this.id = obj.getId();
		this.firstName = obj.getFirstName();
		this.secondMame = obj.getSecondName();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSecondMame() {
		return secondMame;
	}


	public void setSecondMame(String secondMame) {
		this.secondMame = secondMame;
	}


}

