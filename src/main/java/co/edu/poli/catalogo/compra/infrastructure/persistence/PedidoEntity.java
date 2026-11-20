package co.edu.poli.catalogo.compra.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="pedido")
public class PedidoEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @Column(name="usuario_id",nullable=false) public Long usuarioId;
    @Column(nullable=false) public LocalDateTime fecha;
    @Column(nullable=false,precision=12,scale=2) public BigDecimal total;
    @Column(nullable=false,length=20) public String estado;
    @OneToMany(mappedBy="pedido",cascade=CascadeType.ALL,orphanRemoval=true) public List<PedidoDetalleEntity> detalles=new ArrayList<>();
    protected PedidoEntity() {}
}
