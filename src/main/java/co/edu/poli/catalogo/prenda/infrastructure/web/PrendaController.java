package co.edu.poli.catalogo.prenda.infrastructure.web;

import co.edu.poli.catalogo.prenda.application.port.in.ConsultarPrendasUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {

    private final ConsultarPrendasUseCase consultarPrendas;

    public PrendaController(ConsultarPrendasUseCase consultarPrendas) {
        this.consultarPrendas = consultarPrendas;
    }

    @GetMapping
    public List<PrendaResponse> listar() {
        return consultarPrendas.listar().stream().map(PrendaResponse::desde).toList();
    }

    @GetMapping("/{id}")
    public PrendaResponse detalle(@PathVariable Long id) {
        return PrendaResponse.desde(consultarPrendas.obtenerPorId(id));
    }
}
