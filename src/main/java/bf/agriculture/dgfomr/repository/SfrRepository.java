package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Sfr;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sfr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SfrRepository extends JpaRepository<Sfr, Long> {
}
