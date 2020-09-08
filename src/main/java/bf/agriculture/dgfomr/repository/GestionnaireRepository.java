package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Gestionnaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Gestionnaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {
}
