package com.treinaWeb.curse.dto;

import java.io.Serializable;

import com.treinaWeb.curse.domain.Pais;
import com.treinaWeb.curse.domain.Telefone;
import com.treinaWeb.curse.domain.User;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName; 
	private String secondName; 
	private String email;
	private String password;
	private Pais pais;
	private Telefone telefone;
	private String foto;
	
	

	public UserDTO() {	
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		firstName = obj.getFirstName();
		secondName = obj.getSecondName();
		email = obj.getEmail();
		password = obj.getPassword();
		pais = obj.getPais();
		telefone = obj.getTelefone();
		foto = obj.getFoto();
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

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
