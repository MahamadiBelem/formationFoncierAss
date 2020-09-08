package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.NiveauRecrutement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NiveauRecrutement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiveauRecrutementRepository extends JpaRepository<NiveauRecrutement, Long> {
}
