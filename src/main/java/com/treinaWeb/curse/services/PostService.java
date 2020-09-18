package com.treinaWeb.curse.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treinaWeb.curse.domain.Post;
import com.treinaWeb.curse.repository.PostRepository;
import com.treinaWeb.curse.services.exception.ObjectNotFoundException;

// CAMADA SERVICO - onde sao realizadas as querys 
//busca ,insersao, delecao no banco 

//e está chama a camada mais abaixo o UserRepository
//UserRepository- responsavel pela comunicacao com o banco 


@Service
public class PostService{

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTile(String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
