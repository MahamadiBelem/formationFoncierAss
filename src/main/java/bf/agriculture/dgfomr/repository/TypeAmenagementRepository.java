package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.TypeAmenagement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeAmenagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeAmenagementRepository extends JpaRepository<TypeAmenagement, Long> {
}
