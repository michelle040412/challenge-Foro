package com.forohub.Topic;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Estado status;

    @Column(name = "autor", nullable = false, length = 255)
    private String autor;

    @Column(name = "curso", nullable = false, length = 255)
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = datosRegistroTopico.fecha();
        this.status = datosRegistroTopico.estado();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }
}
