package co.edu.poli.catalogo.carrito.infrastructure.web;

import jakarta.validation.constraints.Min;

public record CantidadRequest(@Min(1) int cantidad) {
}
