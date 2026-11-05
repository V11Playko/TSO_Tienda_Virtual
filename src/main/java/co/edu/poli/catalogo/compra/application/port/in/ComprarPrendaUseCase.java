package co.edu.poli.catalogo.compra.application.port.in;

import co.edu.poli.catalogo.compra.domain.Pedido;

public interface ComprarPrendaUseCase {

    /** Compra por artículo: el usuario elige prenda, talla y cantidad. Se valida el stock. */
    Pedido comprar(Long usuarioId, Long prendaId, String talla, int cantidad);
}
