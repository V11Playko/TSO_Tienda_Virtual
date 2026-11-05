package co.edu.poli.catalogo.busqueda.infrastructure.web;

import co.edu.poli.catalogo.busqueda.application.port.in.BuscarPrendasUseCase;
import co.edu.poli.catalogo.busqueda.domain.FiltroBusqueda;
import co.edu.poli.catalogo.prenda.infrastructure.web.PrendaResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/busqueda")
public class BusquedaController {

    private final BuscarPrendasUseCase buscarPrendas;

    public BusquedaController(BuscarPrendasUseCase buscarPrendas) {
        this.buscarPrendas = buscarPrendas;
    }

    /** Búsqueda básica: GET /api/busqueda?nombre=jean */
    @GetMapping
    public List<PrendaResponse> buscarPorNombre(@RequestParam String nombre) {
        return buscarPrendas.buscarPorNombre(nombre).stream().map(PrendaResponse::desde).toList();
    }

    /** Búsqueda avanzada: todos los filtros son opcionales. */
    @GetMapping("/avanzada")
    public List<PrendaResponse> buscarAvanzada(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String talla,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {
        FiltroBusqueda filtro = new FiltroBusqueda(categoria, genero, talla, color, marca, precioMin, precioMax);
        return buscarPrendas.buscarAvanzada(filtro).stream().map(PrendaResponse::desde).toList();
    }
}
