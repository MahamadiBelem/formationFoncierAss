package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.ActiviteInstallation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ActiviteInstallation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActiviteInstallationRepository extends JpaRepository<ActiviteInstallation, Long> {
}
