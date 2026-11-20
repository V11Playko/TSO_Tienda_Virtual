package co.edu.poli.catalogo.usuario.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="usuario")
public class UsuarioEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @Column(nullable=false, length=100) public String nombre;
    @Column(nullable=false, unique=true, length=150) public String correo;
    @Column(name="contrasena_hash", nullable=false, length=100) public String contrasenaHash;
    @Column(name="direccion_envio", nullable=false, length=255) public String direccionEnvio;
    @Column(name="creado_en", nullable=false) public LocalDateTime creadoEn;
    protected UsuarioEntity() {}
    public UsuarioEntity(String nombre, String correo, String hash, String direccion) {
        this.nombre=nombre; this.correo=correo; this.contrasenaHash=hash; this.direccionEnvio=direccion; this.creadoEn=LocalDateTime.now();
    }
}
