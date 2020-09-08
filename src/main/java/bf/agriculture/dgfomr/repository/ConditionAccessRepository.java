package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.ConditionAccess;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ConditionAccess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConditionAccessRepository extends JpaRepository<ConditionAccess, Long> {
}
