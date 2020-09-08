package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Contributions;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Contributions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContributionsRepository extends JpaRepository<Contributions, Long> {
}
