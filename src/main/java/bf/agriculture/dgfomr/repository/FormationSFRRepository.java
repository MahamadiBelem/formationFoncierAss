package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.FormationSFR;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FormationSFR entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormationSFRRepository extends JpaRepository<FormationSFR, Long> {
}
