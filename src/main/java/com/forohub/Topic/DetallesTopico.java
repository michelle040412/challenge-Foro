package com.forohub.Topic;

import java.time.LocalDateTime;

public record DetallesTopico(
        long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        String autor,
        String curso
) {
}
