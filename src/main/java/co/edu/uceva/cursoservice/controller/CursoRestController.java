package co.edu.uceva.cursoservice.controller;

import co.edu.uceva.cursoservice.model.entities.Curso;
import co.edu.uceva.cursoservice.model.service.CursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/curso-service")
public class CursoRestController {

    @Autowired
    private CursoServiceImpl cursoService;

    /*
    Metodo que lista los cursos.
     */
    @GetMapping("/cursos")
    public ResponseEntity listar_curso() {
        Map<String, Object> response = new HashMap<>();
        List<Curso> cursos = null;
        try {
            cursos = this.cursoService.listar_curso();
        } catch (Exception e) {
            response.put("mensaje", "Error al listar los cursos");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
        return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
    }

    /*
    Metodo que guarda curso.
     */
    @PostMapping("/curso")
    public Curso guardarCurso(@RequestBody Curso curso) {
        return this.cursoService.save(curso);
    }

    /*
    Metodo que busca curso por id.
    */
    @GetMapping("/cursos/{id}")
    public Curso buscarCurso(@PathVariable long id) {
        return this.cursoService.findById(id);
    }

    /*
    Metodo que elimina curso por id.
     */
    @DeleteMapping("/cursos/{id}")
    public void borrarCurso(@PathVariable("id") Long id) {
        Curso curso;
        curso = cursoService.findById(id);
        cursoService.delete(curso);
    }

}

