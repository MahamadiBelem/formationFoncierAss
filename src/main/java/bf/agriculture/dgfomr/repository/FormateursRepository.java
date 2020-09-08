package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Formateurs;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Formateurs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormateursRepository extends JpaRepository<Formateurs, Long> {
}
