package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.PublicCible;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PublicCible entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicCibleRepository extends JpaRepository<PublicCible, Long> {
}
