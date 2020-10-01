package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Regime;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Regime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegimeRepository extends JpaRepository<Regime, Long> {
}
