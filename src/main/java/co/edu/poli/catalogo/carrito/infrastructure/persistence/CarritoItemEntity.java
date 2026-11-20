package co.edu.poli.catalogo.carrito.infrastructure.persistence;

import co.edu.poli.catalogo.prenda.infrastructure.persistence.PrendaEntity;
import jakarta.persistence.*;

@Entity @Table(name="carrito_item")
public class CarritoItemEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="carrito_id", nullable=false) public CarritoEntity carrito;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="prenda_id", nullable=false) public PrendaEntity prenda;
    @Column(nullable=false, length=20) public String talla;
    @Column(nullable=false) public int cantidad;
    protected CarritoItemEntity() {}
}
