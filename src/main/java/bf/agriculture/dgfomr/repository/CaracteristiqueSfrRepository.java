package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.CaracteristiqueSfr;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CaracteristiqueSfr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaracteristiqueSfrRepository extends JpaRepository<CaracteristiqueSfr, Long> {
}
