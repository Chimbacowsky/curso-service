package co.edu.uceva.cursoservice.model.dao;

import co.edu.uceva.cursoservice.model.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoDao extends CrudRepository<Curso, Long> {
}
