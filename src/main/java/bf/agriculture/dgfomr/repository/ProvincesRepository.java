package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Provinces;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Provinces entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvincesRepository extends JpaRepository<Provinces, Long> {
}
