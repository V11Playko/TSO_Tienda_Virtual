package co.edu.poli.catalogo.compra.infrastructure.web;

import co.edu.poli.catalogo.compra.application.port.in.ComprarPrendaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Compra de una prenda por el usuario autenticado. */
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final ComprarPrendaUseCase comprarPrenda;

    public CompraController(ComprarPrendaUseCase comprarPrenda) {
        this.comprarPrenda = comprarPrenda;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse comprar(@RequestAttribute("usuarioId") Long usuarioId,
                          @Valid @RequestBody CompraRequest req) {
        return PedidoResponse.desde(comprarPrenda.comprar(usuarioId, req.prendaId(), req.talla(), req.cantidad()));
    }
}
