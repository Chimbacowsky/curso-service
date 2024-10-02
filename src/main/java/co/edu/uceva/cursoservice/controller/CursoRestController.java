package co.edu.uceva.cursoservice.controller;

import co.edu.uceva.cursoservice.model.entities.Curso;
import co.edu.uceva.cursoservice.model.service.CursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/curso-service")
public class CursoRestController {

    @Autowired
    private CursoServiceImpl celularservice;

    /*
    Metodo que lista los cursos.
     */
    @GetMapping("/cursos")
    public ResponseEntity listar_curso() {
        Map<String, Object> response = new HashMap<>();
        List<Curso> cursos = null;
        try {
            cursos = this.celularservice.listar_curso();
        } catch (Exception e) {
            response.put("mensaje", "Error al listar los cursos");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
        return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
    }

}