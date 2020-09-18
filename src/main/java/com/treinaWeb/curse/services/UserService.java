package com.treinaWeb.curse.services;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treinaWeb.curse.domain.User;
import com.treinaWeb.curse.dto.UserDTO;
import com.treinaWeb.curse.repository.UserRepository;
import com.treinaWeb.curse.services.exception.ObjectNotFoundException;

// CAMADA SERVICO - onde sao realizadas as querys 
//busca ,insersao, delecao no banco 

//e está chama a camada mais abaixo o UserRepository
//UserRepository- responsavel pela comunicacao com o banco 


@Service
public class UserService{

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<User> findByEmailPassword(String text, String password){
		return repo.searchEmail(text, password);
	}

	public List<User> findAllById(String id){
		return repo.findAllById(id);
	}
	
	public List<User> findByEmail(String text){
		return repo.validarEmail(text);
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	// converte DTO em Usuario
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getFirstName(), objDto.getSecondName(), objDto.getEmail(), objDto.getPassword(), objDto.getPais(), objDto.getTelefone(), objDto.getFoto());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	

	public boolean validarPassword(User obj) {
		User newObj = findById(obj.getId());
		if  (newObj.getPassword() == obj.getPassword()){
			return true;
		}
		else return false;
	}
	private void updateData(User newObj, User obj) {
		newObj.setFirstName(obj.getFirstName());
		newObj.setSecondName(obj.getSecondName());
		newObj.setPais(obj.getPais());
		newObj.setTelefone(obj.getTelefone());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
		newObj.setFoto(obj.getFoto());		
	}
	
	
	public boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
