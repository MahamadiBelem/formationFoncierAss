package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.TypeFormation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeFormationRepository extends JpaRepository<TypeFormation, Long> {
}
