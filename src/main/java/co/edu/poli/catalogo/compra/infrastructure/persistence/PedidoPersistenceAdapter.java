package co.edu.poli.catalogo.compra.infrastructure.persistence;

import co.edu.poli.catalogo.compra.application.port.out.PedidoRepositoryPort;
import co.edu.poli.catalogo.compra.domain.*;
import co.edu.poli.catalogo.prenda.infrastructure.persistence.PrendaJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Repository
public class PedidoPersistenceAdapter implements PedidoRepositoryPort {
    private final PedidoJpaRepository pedidos;
    private final PrendaJpaRepository prendas;
    public PedidoPersistenceAdapter(PedidoJpaRepository pedidos, PrendaJpaRepository prendas) { this.pedidos=pedidos; this.prendas=prendas; }
    @Transactional public Pedido guardar(Pedido pedido) {
        PedidoEntity e=new PedidoEntity(); e.usuarioId=pedido.usuarioId(); e.fecha=pedido.fecha(); e.total=pedido.total(); e.estado=pedido.estado();
        for(DetallePedido d:pedido.detalles()) {
            PedidoDetalleEntity item=new PedidoDetalleEntity(); item.pedido=e;
            item.prenda=prendas.getReferenceById(d.prendaId()); item.talla=d.talla(); item.cantidad=d.cantidad(); item.precioUnitario=d.precioUnitario();
            e.detalles.add(item);
        }
        PedidoEntity saved=pedidos.save(e);
        return new Pedido(saved.id,saved.usuarioId,saved.fecha,saved.total,saved.estado,
                new ArrayList<>(pedido.detalles()));
    }
}
