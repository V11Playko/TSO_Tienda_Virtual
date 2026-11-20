package co.edu.poli.catalogo.carrito.application;

import co.edu.poli.catalogo.carrito.application.port.in.GestionarCarritoUseCase;
import co.edu.poli.catalogo.carrito.application.port.out.CarritoRepositoryPort;
import co.edu.poli.catalogo.carrito.domain.Carrito;
import co.edu.poli.catalogo.comun.ReglaNegocioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService implements GestionarCarritoUseCase {
    private final CarritoRepositoryPort carritos;
    public CarritoService(CarritoRepositoryPort carritos) { this.carritos=carritos; }
    @Override @Transactional(readOnly=true) public Carrito verCarrito(Long userId) { return carritos.obtener(userId); }
    @Override @Transactional public Carrito agregarItem(Long userId, Long productId, String talla, int cantidad) {
        validar(cantidad); if(talla==null||talla.isBlank()) throw new ReglaNegocioException("La talla es obligatoria");
        return carritos.agregar(userId,productId,talla,cantidad);
    }
    @Override @Transactional public Carrito actualizarCantidad(Long userId, Long itemId, int cantidad) {
        validar(cantidad); return carritos.actualizar(userId,itemId,cantidad);
    }
    @Override @Transactional public Carrito eliminarItem(Long userId, Long itemId) { return carritos.eliminar(userId,itemId); }
    private void validar(int cantidad) { if(cantidad<=0) throw new ReglaNegocioException("La cantidad debe ser mayor que cero"); }
}
