package co.edu.poli.catalogo.carrito.infrastructure.persistence;

import co.edu.poli.catalogo.carrito.application.port.out.CarritoRepositoryPort;
import co.edu.poli.catalogo.carrito.domain.*;
import co.edu.poli.catalogo.comun.RecursoNoEncontradoException;
import co.edu.poli.catalogo.comun.ReglaNegocioException;
import co.edu.poli.catalogo.prenda.infrastructure.persistence.PrendaJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class CarritoPersistenceAdapter implements CarritoRepositoryPort {
    private final CarritoJpaRepository carritos;
    private final PrendaJpaRepository prendas;
    public CarritoPersistenceAdapter(CarritoJpaRepository carritos, PrendaJpaRepository prendas) { this.carritos=carritos; this.prendas=prendas; }
    @Transactional public Carrito obtener(Long userId) {
        if(userId==null || userId<=0) throw new ReglaNegocioException("Sesión no válida");
        return carritos.porUsuario(userId).map(this::map).orElseGet(()->new Carrito(null,userId,java.util.List.of()));
    }
    @Transactional public Carrito agregar(Long userId, Long productId, String size, int quantity) {
        CarritoEntity c=lock(userId);
        var p=prendas.findByIdBloqueado(productId).orElseThrow(()->new RecursoNoEncontradoException("No existe la prenda " + productId));
        if(!co.edu.poli.catalogo.prenda.infrastructure.persistence.PrendaMapper.desde(p).admiteTalla(size))
            throw new ReglaNegocioException("La talla seleccionada no está disponible para esta prenda");
        if (p.stock<quantity) throw new ReglaNegocioException("Stock insuficiente");
        CarritoItemEntity i=c.items.stream().filter(x->x.prenda.id.equals(productId)&&x.talla.equalsIgnoreCase(size)).findFirst().orElse(null);
        int nueva=quantity+(i==null?0:i.cantidad);
        if(nueva>p.stock) throw new ReglaNegocioException("La cantidad supera el stock disponible");
        if(i==null) { i=new CarritoItemEntity(); i.carrito=c; i.prenda=p; i.talla=size.trim(); c.items.add(i); }
        i.cantidad=nueva; c.actualizadoEn=LocalDateTime.now();
        return map(carritos.save(c));
    }
    @Transactional public Carrito actualizar(Long userId, Long itemId, int quantity) {
        CarritoEntity c=lock(userId); CarritoItemEntity i=find(c,itemId);
        var producto=prendas.findByIdBloqueado(i.prenda.id).orElseThrow(()->new RecursoNoEncontradoException("No existe la prenda " + i.prenda.id));
        if(producto.stock<quantity) throw new ReglaNegocioException("Stock insuficiente");
        i.cantidad=quantity; c.actualizadoEn=LocalDateTime.now(); return map(carritos.save(c));
    }
    @Transactional public Carrito eliminar(Long userId, Long itemId) {
        CarritoEntity c=lock(userId); c.items.remove(find(c,itemId)); c.actualizadoEn=LocalDateTime.now(); return map(carritos.save(c));
    }
    private CarritoEntity lock(Long userId) {
        if(userId==null || userId<=0) throw new ReglaNegocioException("Sesión no válida");
        return carritos.porUsuarioBloqueado(userId).orElseGet(()->carritos.save(new CarritoEntity(userId)));
    }
    private CarritoItemEntity find(CarritoEntity c, Long id) { return c.items.stream().filter(i->i.id.equals(id)).findFirst().orElseThrow(()->new RecursoNoEncontradoException("No existe el item " + id + " en este carrito")); }
    private Carrito map(CarritoEntity c) { return new Carrito(c.id,c.usuarioId,c.items.stream().map(i->new ItemCarrito(i.id,i.prenda.id,i.prenda.nombre,i.talla,i.cantidad,i.prenda.precio)).toList()); }
}
