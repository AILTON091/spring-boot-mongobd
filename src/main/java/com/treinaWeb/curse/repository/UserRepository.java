package com.treinaWeb.curse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.treinaWeb.curse.domain.Post;
import com.treinaWeb.curse.domain.User;

// camada final camada de acesso a dados (repository )-
// responsavel pela comunicao com o banco

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{  $and: [ {'email' : { $eq: ?0 }}, { 'password' : { $eq: ?1 }} ] }")
	List<User> searchEmail(String text, String password);

	@Query("{ 'email': { $eq: ?0 } }")
	List<User> validarEmail(String text);
	
	@Query("{ 'id': { $eq: ?0 } }")
	List<User> findAllById(String text);
	
}
