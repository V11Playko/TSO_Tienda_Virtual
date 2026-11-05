package co.edu.poli.catalogo.carrito.application;

import co.edu.poli.catalogo.carrito.application.port.in.GestionarCarritoUseCase;
import co.edu.poli.catalogo.carrito.domain.Carrito;
import co.edu.poli.catalogo.comun.FuncionalidadNoImplementadaException;
import org.springframework.stereotype.Service;

/**
 * ESQUELETO del carrito de compras.
 * TODO (Isabel):
 *  - Implementar CarritoRepositoryPort con JPA (tablas carrito y carrito_item) e inyectarlo aquí.
 *  - Validar el stock de la prenda al agregar o cambiar cantidades.
 *  - Calcular la cantidad de prendas y el precio total a pagar.
 */
@Service
public class CarritoService implements GestionarCarritoUseCase {

    private static final String FUNCIONALIDAD = "carrito de compras";

    @Override
    public Carrito verCarrito(Long usuarioId) {
        throw new FuncionalidadNoImplementadaException(FUNCIONALIDAD);
    }

    @Override
    public Carrito agregarItem(Long usuarioId, Long prendaId, String talla, int cantidad) {
        throw new FuncionalidadNoImplementadaException(FUNCIONALIDAD);
    }

    @Override
    public Carrito actualizarCantidad(Long usuarioId, Long itemId, int cantidad) {
        throw new FuncionalidadNoImplementadaException(FUNCIONALIDAD);
    }

    @Override
    public Carrito eliminarItem(Long usuarioId, Long itemId) {
        throw new FuncionalidadNoImplementadaException(FUNCIONALIDAD);
    }
}
