package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Formations;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Formations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormationsRepository extends JpaRepository<Formations, Long> {
}
