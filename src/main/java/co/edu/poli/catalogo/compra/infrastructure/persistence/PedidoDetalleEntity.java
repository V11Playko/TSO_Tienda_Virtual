package co.edu.poli.catalogo.compra.infrastructure.persistence;

import co.edu.poli.catalogo.prenda.infrastructure.persistence.PrendaEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name="pedido_detalle")
public class PedidoDetalleEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="pedido_id",nullable=false) public PedidoEntity pedido;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="prenda_id",nullable=false) public PrendaEntity prenda;
    @Column(nullable=false,length=20) public String talla;
    @Column(nullable=false) public int cantidad;
    @Column(name="precio_unitario",nullable=false,precision=12,scale=2) public BigDecimal precioUnitario;
    protected PedidoDetalleEntity() {}
}
