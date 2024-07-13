package com.forohub.Topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ActualizacionTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        Estado estado,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
