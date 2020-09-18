package com.treinaWeb.curse.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.treinaWeb.curse.domain.User;
import com.treinaWeb.curse.dto.UserDTO;
import com.treinaWeb.curse.resources.util.Criptografar;
import com.treinaWeb.curse.resources.util.URL;
import com.treinaWeb.curse.services.UserService;

// CONTRALADOR REST - ENVIA O RECURSOS PARA A WEB
// camada mais proxima ao front-end - ENVIA O RECURSOS PARA A WEB
// esta comunica com a camada de serivco  - q executa as querys, ou de 
//busca ,insersao, delecao no banco 

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	// @GetMapping tambem pode usar GetMapping
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		// converte cada usuario aa lista de usuario User em UserDTO
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findByEmailpassword(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "password", defaultValue = "") String password) {

		text = URL.decodeParam(text);
		password = URL.decodeParam(password);
		List<User> list = service.findByEmailPassword(text, password);
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		if (list.size() > 0) {
			return ResponseEntity.ok().body(listDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findByEmail(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "password", defaultValue = "") String password) {

		text = URL.decodeParam(text);
		password = URL.decodeParam(password);
		password = Criptografar.encriptografar(password); // encripta para fazer a comparacao no banco 
		List<User> list = service.findByEmailPassword(text, password);
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		if (list.size() > 0) {
			return ResponseEntity.ok().body(listDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// @RequestBody --serve para aceitar a class UserDto pois é um DTO , com as
	// informações vindas da web
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> findById(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		
		boolean emailValido = service.isValidEmailAddressRegex(obj.getEmail());
		if (emailValido) {
			List<User> validarEmail = service.findByEmail(obj.getEmail());
			if (validarEmail.size() > 0) {
				return ResponseEntity.notFound().build();
			} else {
				
				obj.setPassword(Criptografar.encriptografar(obj.getPassword()));
				obj = service.insert(obj);
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId())
						.toUri();
				return ResponseEntity.created(uri).build();
			}
		}
		else
		{
			return ResponseEntity.notFound().build();
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updatePassword(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		if (obj.getPassword().length() < 20) {
			obj.setPassword(Criptografar.encriptografar(obj.getPassword()));
		}
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
