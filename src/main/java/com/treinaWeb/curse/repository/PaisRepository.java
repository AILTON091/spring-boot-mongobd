package com.treinaWeb.curse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.treinaWeb.curse.domain.Pais;
import com.treinaWeb.curse.domain.Post;
import com.treinaWeb.curse.domain.User;

// camada final camada de acesso a dados (repository )-
// responsavel pela comunicao com o banco

@Repository
public interface PaisRepository extends MongoRepository<Pais, String> {

}
