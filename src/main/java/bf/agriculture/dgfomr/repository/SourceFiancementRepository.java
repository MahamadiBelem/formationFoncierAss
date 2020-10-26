package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.SourceFiancement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SourceFiancement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SourceFiancementRepository extends JpaRepository<SourceFiancement, Long> {
}
