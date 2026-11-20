package co.edu.poli.catalogo.prenda.infrastructure.persistence;

import jakarta.persistence.*;

@Entity @Table(name="categoria")
public class CategoriaEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @Column(nullable=false, unique=true, length=60) public String nombre;
    protected CategoriaEntity() {}
}
