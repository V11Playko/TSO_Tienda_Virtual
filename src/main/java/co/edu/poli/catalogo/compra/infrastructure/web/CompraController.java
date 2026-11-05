package co.edu.poli.catalogo.compra.infrastructure.web;

import co.edu.poli.catalogo.compra.application.port.in.ComprarPrendaUseCase;
import co.edu.poli.catalogo.compra.domain.Pedido;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ESQUELETO: responde 501 hasta implementar CompraService.
 * Mientras no exista sesión, el usuario se identifica con el encabezado X-Usuario-Id.
 */
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final ComprarPrendaUseCase comprarPrenda;

    public CompraController(ComprarPrendaUseCase comprarPrenda) {
        this.comprarPrenda = comprarPrenda;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido comprar(@RequestHeader(value = "X-Usuario-Id", required = false) Long usuarioId,
                          @Valid @RequestBody CompraRequest req) {
        // TODO: devolver un DTO de respuesta en lugar del modelo de dominio
        return comprarPrenda.comprar(usuarioId, req.prendaId(), req.talla(), req.cantidad());
    }
}
