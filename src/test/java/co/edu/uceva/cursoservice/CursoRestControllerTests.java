package co.edu.uceva.cursoservice;


import co.edu.uceva.cursoservice.model.entities.Curso;
import co.edu.uceva.cursoservice.model.service.ICursoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoRestControllerTests {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;     //Prueba controladores y endpoints sin necesidad de un navegador web

    @Autowired
    private ICursoService cursoService;

    /*
    Simula envíos de solicitudes HTTP en la prueba de la clase CursoRestController
     */
    @Before
    public void setUp() { this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();}

    //Prueba para comprobar que recibe un nombre correcto en la respuesta
    @Test
    public void testHolaMundo() throws Exception {
        String nombre = "Andres";
        this.mockMvc.perform(get("/api/curso-service/hola/{nombre}", nombre))
                .andExpect(status().isOk()).andExpect(content().string("Hola" + nombre));
    }

    //Método get para comprobar que recibe una lista de cursos, se lanza una excepcion si hay error
    @Test
    public void testListar() throws Exception {
        Curso curso1 = new Curso(null, "Analisis numerico", "Introduccion de metodos de aproximacion.", "Andres Gonzalez");
        Curso curso2 = new Curso(null, "Sistemas operativos", "Introduccion a creacion de maquinas virtuales.", "Fabian Perez");
        cursoService.save(curso1);
        cursoService.save(curso2);
        this.mockMvc.perform(get("/api/curso-service/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].curso", is(curso1.getNombre_curso())))
                .andExpect(jsonPath("$[0].curso", is(curso2.getNombre_curso())));
        cursoService.delete(curso1);
        cursoService.delete(curso2);
    }

    @Test
    public void testBuscarCurso() throws Exception {
        Curso curso = new Curso(null, "Analisis numerico");
        cursoService.save(curso);

        this.mockMvc.perform(get("/api/curso-service/cursos/{id}", curso.getNombre_curso()))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.curso", is(curso.getId())));

        cursoService.delete(curso);
    }

    @Test
    public void testCrearCurso() throws Exception {
        Curso curso = new Curso(null, "Matematicas discretas");
        cursoService.save(curso);

        this.mockMvc.perform(get("/api/curso-service/cursos/{nombre-curso}", curso.getId()))
    }
}
