package com.treinaWeb.curse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.treinaWeb.curse.domain.Telefone;

// camada final camada de acesso a dados (repository )-
// responsavel pela comunicao com o banco

@Repository
public interface TelefoneRepository extends MongoRepository<Telefone, String> {
	
}
