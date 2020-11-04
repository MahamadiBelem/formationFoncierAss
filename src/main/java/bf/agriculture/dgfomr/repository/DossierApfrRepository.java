package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.DossierApfr;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DossierApfr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DossierApfrRepository extends JpaRepository<DossierApfr, Long> {
}
