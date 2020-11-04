package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.TypeActe;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeActe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeActeRepository extends JpaRepository<TypeActe, Long> {
}
