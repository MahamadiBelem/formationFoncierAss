package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Specialites;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Specialites entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecialitesRepository extends JpaRepository<Specialites, Long> {
}
