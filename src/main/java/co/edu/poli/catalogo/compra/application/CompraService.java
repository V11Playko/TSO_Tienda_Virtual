package co.edu.poli.catalogo.compra.application;

import co.edu.poli.catalogo.comun.FuncionalidadNoImplementadaException;
import co.edu.poli.catalogo.compra.application.port.in.ComprarPrendaUseCase;
import co.edu.poli.catalogo.compra.domain.Pedido;
import org.springframework.stereotype.Service;

/**
 * ESQUELETO de la compra por artículo.
 * TODO (Heinner):
 *  - Verificar que el usuario exista (debe estar registrado para comprar).
 *  - Consultar la prenda, validar que haya stock suficiente y descontarlo.
 *  - Guardar el pedido y su detalle con PedidoRepositoryPort (tablas pedido y pedido_detalle).
 */
@Service
public class CompraService implements ComprarPrendaUseCase {

    @Override
    public Pedido comprar(Long usuarioId, Long prendaId, String talla, int cantidad) {
        throw new FuncionalidadNoImplementadaException("compra por artículo");
    }
}
