package co.edu.uceva.cursoservice.model.service;

import co.edu.uceva.cursoservice.model.dao.CursoDao;
import co.edu.uceva.cursoservice.model.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoServiceImpl implements ICursoService{

    @Autowired
    CursoDao cursoDao;

    @Override
    public List<Curso> listar_curso() {
        return (List<Curso>) cursoDao.findAll();
    }

    @Override
    public Curso save(Curso curso) {
        return cursoDao.save(curso);
    }

    @Override
    public Curso findById(Long id) { return cursoDao.findById(id).orElse(null); }

    @Override
    public void delete(Curso curso) { cursoDao.delete(curso);}
}