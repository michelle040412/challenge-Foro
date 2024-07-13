package com.forohub.Topic;

import java.time.LocalDateTime;

public record ListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Estado estado,
        String autor,
        String curso
) {
    public ListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
