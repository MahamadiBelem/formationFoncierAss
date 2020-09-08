package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.DomaineFormation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DomaineFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomaineFormationRepository extends JpaRepository<DomaineFormation, Long> {
}
