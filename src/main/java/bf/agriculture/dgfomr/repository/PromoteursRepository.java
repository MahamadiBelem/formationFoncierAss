package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Promoteurs;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Promoteurs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromoteursRepository extends JpaRepository<Promoteurs, Long> {
}
