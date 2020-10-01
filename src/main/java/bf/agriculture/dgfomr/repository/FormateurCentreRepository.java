package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.FormateurCentre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FormateurCentre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormateurCentreRepository extends JpaRepository<FormateurCentre, Long> {
}
