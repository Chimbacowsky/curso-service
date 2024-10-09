package co.edu.uceva.cursoservice.model.service;

import co.edu.uceva.cursoservice.model.entities.Curso;

import java.util.List;

public interface ICursoService {

    List<Curso> listar_curso();
    Curso save(Curso curso);
    Curso findById(Long id);
}