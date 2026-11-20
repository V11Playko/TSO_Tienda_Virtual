package co.edu.poli.catalogo.prenda.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PrendaJpaRepository extends JpaRepository<PrendaEntity, Long>, JpaSpecificationExecutor<PrendaEntity> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from PrendaEntity p join fetch p.categoria where p.id = :id")
    Optional<PrendaEntity> findByIdBloqueado(@Param("id") Long id);
}
