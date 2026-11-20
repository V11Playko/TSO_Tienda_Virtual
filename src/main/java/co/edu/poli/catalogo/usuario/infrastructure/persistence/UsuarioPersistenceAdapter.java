package co.edu.poli.catalogo.usuario.infrastructure.persistence;

import co.edu.poli.catalogo.usuario.application.port.out.UsuarioRepositoryPort;
import co.edu.poli.catalogo.usuario.domain.Usuario;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository repo;
    public UsuarioPersistenceAdapter(UsuarioJpaRepository repo) { this.repo=repo; }
    public boolean existePorCorreo(String correo) { return repo.existsByCorreoIgnoreCase(correo); }
    public Optional<Usuario> buscarPorCorreo(String correo) { return repo.findByCorreoIgnoreCase(correo).map(UsuarioMapper::desde); }
    public Optional<Usuario> buscarPorId(Long id) { return repo.findById(id).map(UsuarioMapper::desde); }
    public Usuario guardar(String nombre, String correo, String hash, String direccion) {
        return UsuarioMapper.desde(repo.save(new UsuarioEntity(nombre, correo, hash, direccion)));
    }
}
