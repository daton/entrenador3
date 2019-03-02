package org.unitec.entrenador3;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by campitos on 7/04/17.
 */
public interface RepositorioAlumno extends MongoRepository<Alumno , String> {
    Alumno findByNombre(String nombre);
}
