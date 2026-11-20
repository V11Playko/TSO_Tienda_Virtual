package co.edu.poli.catalogo.carrito.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface CarritoJpaRepository extends JpaRepository<CarritoEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from CarritoEntity c where c.usuarioId=:usuarioId")
    Optional<CarritoEntity> porUsuarioBloqueado(@Param("usuarioId") Long usuarioId);
    @Query("select distinct c from CarritoEntity c left join fetch c.items i left join fetch i.prenda p left join fetch p.categoria where c.usuarioId=:usuarioId")
    Optional<CarritoEntity> porUsuario(@Param("usuarioId") Long usuarioId);
}
