package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Infrastructure;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Infrastructure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {
}
