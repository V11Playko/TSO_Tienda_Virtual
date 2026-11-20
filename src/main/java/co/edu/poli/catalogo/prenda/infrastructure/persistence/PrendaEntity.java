package co.edu.poli.catalogo.prenda.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name="prenda")
public class PrendaEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @Column(nullable=false, length=120) public String nombre;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="categoria_id", nullable=false) public CategoriaEntity categoria;
    @Column(nullable=false, length=10) public String genero;
    @Column(nullable=false, length=20) public String talla;
    @Column(nullable=false, length=40) public String color;
    @Column(nullable=false, length=60) public String material;
    @Column(nullable=false, length=60) public String marca;
    @Column(nullable=false, precision=12, scale=2) public BigDecimal precio;
    @Column(nullable=false) public int stock;
    @Column(name="imagen_url", length=255) public String imagenUrl;
    @Column(length=1000) public String descripcion;
    protected PrendaEntity() {}
}
