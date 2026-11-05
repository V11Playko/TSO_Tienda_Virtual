package co.edu.poli.catalogo.comun.web;

import java.time.LocalDateTime;

/** Formato JSON simple para todas las respuestas de error de la API. */
public record ErrorResponse(LocalDateTime fecha, int estado, String error, String mensaje, String ruta) {
}
