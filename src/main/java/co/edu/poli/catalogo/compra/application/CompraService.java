package co.edu.poli.catalogo.compra.application;

import co.edu.poli.catalogo.comun.RecursoNoEncontradoException;
import co.edu.poli.catalogo.comun.ReglaNegocioException;
import co.edu.poli.catalogo.compra.application.port.in.ComprarPrendaUseCase;
import co.edu.poli.catalogo.compra.application.port.out.PedidoRepositoryPort;
import co.edu.poli.catalogo.compra.domain.*;
import co.edu.poli.catalogo.prenda.application.port.out.InventarioPort;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import co.edu.poli.catalogo.usuario.application.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService implements ComprarPrendaUseCase {
    private final UsuarioRepositoryPort usuarios;
    private final InventarioPort inventario;
    private final PedidoRepositoryPort pedidos;
    public CompraService(UsuarioRepositoryPort usuarios, InventarioPort inventario, PedidoRepositoryPort pedidos) {
        this.usuarios=usuarios; this.inventario=inventario; this.pedidos=pedidos;
    }
    @Override @Transactional
    public Pedido comprar(Long usuarioId, Long prendaId, String talla, int cantidad) {
        if(usuarioId==null || usuarios.buscarPorId(usuarioId).isEmpty()) throw new RecursoNoEncontradoException("Usuario no encontrado");
        if(cantidad<=0) throw new ReglaNegocioException("La cantidad debe ser mayor que cero");
        if(talla==null||talla.isBlank()) throw new ReglaNegocioException("La talla es obligatoria");
        Prenda prenda=inventario.obtenerConBloqueo(prendaId);
        if(!prenda.admiteTalla(talla)) throw new ReglaNegocioException("La talla seleccionada no está disponible para esta prenda");
        if(prenda.stock()<cantidad) throw new ReglaNegocioException("Stock insuficiente");
        BigDecimal total=prenda.precio().multiply(BigDecimal.valueOf(cantidad));
        Pedido pedido=new Pedido(null,usuarioId,LocalDateTime.now(),total,"CREADO",
                List.of(new DetallePedido(prendaId,talla.trim(),cantidad,prenda.precio())));
        Pedido guardado=pedidos.guardar(pedido);
        inventario.descontar(prendaId,cantidad);
        return guardado;
    }
}
