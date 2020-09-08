package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Kits;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Kits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KitsRepository extends JpaRepository<Kits, Long> {
}
