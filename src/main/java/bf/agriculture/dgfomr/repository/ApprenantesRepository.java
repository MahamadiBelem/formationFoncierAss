package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Apprenantes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Apprenantes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprenantesRepository extends JpaRepository<Apprenantes, Long> {
}
