package co.edu.uceva.cursoservice.model.entities;

import jakarta.persistence.*;

import lombok.*;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre_curso;
    private String descripcion_curso;
    private String nombre_docente;

}