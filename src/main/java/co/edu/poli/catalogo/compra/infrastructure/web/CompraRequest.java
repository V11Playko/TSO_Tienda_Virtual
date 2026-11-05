package co.edu.poli.catalogo.compra.infrastructure.web;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompraRequest(@NotNull Long prendaId, @NotBlank String talla, @Min(1) int cantidad) {
}
