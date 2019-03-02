package org.unitec.entrenador3;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by campitos on 22/02/17.
 */
public interface RepositorioPregunta extends MongoRepository<Pregunta, String> {
    List<Pregunta> findByTema(String tema);
    List<Pregunta> findByArea(String area);
}
