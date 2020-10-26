package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.CompositionKits;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CompositionKits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompositionKitsRepository extends JpaRepository<CompositionKits, Long> {
}
