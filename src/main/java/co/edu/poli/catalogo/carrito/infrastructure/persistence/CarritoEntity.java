package co.edu.poli.catalogo.carrito.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="carrito")
public class CarritoEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @Column(name="usuario_id", nullable=false, unique=true) public Long usuarioId;
    @Column(name="actualizado_en", nullable=false) public LocalDateTime actualizadoEn;
    @OneToMany(mappedBy="carrito", cascade=CascadeType.ALL, orphanRemoval=true) public List<CarritoItemEntity> items=new ArrayList<>();
    protected CarritoEntity() {}
    public CarritoEntity(Long usuarioId) { this.usuarioId=usuarioId; this.actualizadoEn=LocalDateTime.now(); }
}
