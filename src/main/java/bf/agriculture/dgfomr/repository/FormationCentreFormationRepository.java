package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.FormationCentreFormation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FormationCentreFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormationCentreFormationRepository extends JpaRepository<FormationCentreFormation, Long> {
}
