package com.forohub.repositorio;

import com.forohub.Topic.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByTituloOrMensaje(String titulo, String mensaje);
}
