package co.edu.poli.catalogo.prenda.infrastructure.persistence;

import co.edu.poli.catalogo.busqueda.domain.FiltroBusqueda;
import co.edu.poli.catalogo.prenda.application.port.out.PrendaRepositoryPort;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PrendaPersistenceAdapter implements PrendaRepositoryPort, co.edu.poli.catalogo.prenda.application.port.out.InventarioPort {
    private final PrendaJpaRepository repo;
    public PrendaPersistenceAdapter(PrendaJpaRepository repo) { this.repo=repo; }
    public List<Prenda> listar() { return repo.findAll().stream().map(PrendaMapper::desde).toList(); }
    public Optional<Prenda> buscarPorId(Long id) { return repo.findById(id).map(PrendaMapper::desde); }
    @org.springframework.transaction.annotation.Transactional
    public Prenda obtenerConBloqueo(Long id) {
        return repo.findByIdBloqueado(id).map(PrendaMapper::desde)
                .orElseThrow(()->new co.edu.poli.catalogo.comun.RecursoNoEncontradoException("No existe la prenda " + id));
    }
    @org.springframework.transaction.annotation.Transactional
    public void descontar(Long id, int cantidad) {
        PrendaEntity e=repo.findByIdBloqueado(id).orElseThrow(()->new co.edu.poli.catalogo.comun.RecursoNoEncontradoException("No existe la prenda " + id));
        if (cantidad<=0 || e.stock<cantidad) throw new co.edu.poli.catalogo.comun.ReglaNegocioException("Stock insuficiente");
        e.stock-=cantidad;
    }
    public List<Prenda> buscarPorNombre(String nombre) {
        Specification<PrendaEntity> spec=(r,q,cb)->cb.like(cb.lower(r.get("nombre")), "%"+nombre.toLowerCase()+"%");
        return repo.findAll(spec).stream().map(PrendaMapper::desde).toList();
    }
    public List<Prenda> buscar(FiltroBusqueda f) {
        Specification<PrendaEntity> s=Specification.where(null);
        if (tiene(f.categoria())) s=s.and((r,q,cb)->cb.like(cb.lower(r.get("categoria").get("nombre")), "%"+f.categoria().trim().toLowerCase()+"%"));
        if (tiene(f.genero())) s=s.and((r,q,cb)->cb.equal(cb.lower(r.get("genero")), f.genero().trim().toLowerCase()));
        if (tiene(f.talla())) s=s.and((r,q,cb)->cb.like(cb.lower(r.get("talla")), "%"+f.talla().trim().toLowerCase()+"%"));
        if (tiene(f.color())) s=s.and((r,q,cb)->cb.like(cb.lower(r.get("color")), "%"+f.color().trim().toLowerCase()+"%"));
        if (tiene(f.marca())) s=s.and((r,q,cb)->cb.like(cb.lower(r.get("marca")), "%"+f.marca().trim().toLowerCase()+"%"));
        if (f.precioMin()!=null) s=s.and((r,q,cb)->cb.greaterThanOrEqualTo(r.get("precio"), f.precioMin()));
        if (f.precioMax()!=null) s=s.and((r,q,cb)->cb.lessThanOrEqualTo(r.get("precio"), f.precioMax()));
        if (Boolean.TRUE.equals(f.disponible())) s=s.and((r,q,cb)->cb.greaterThan(r.get("stock"), 0));
        if (Boolean.FALSE.equals(f.disponible())) s=s.and((r,q,cb)->cb.equal(r.get("stock"), 0));
        return repo.findAll(s).stream().map(PrendaMapper::desde).toList();
    }
    private boolean tiene(String v) { return v!=null && !v.isBlank(); }
}
