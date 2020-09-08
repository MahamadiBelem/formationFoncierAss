package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Amenagement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Amenagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmenagementRepository extends JpaRepository<Amenagement, Long> {
}
