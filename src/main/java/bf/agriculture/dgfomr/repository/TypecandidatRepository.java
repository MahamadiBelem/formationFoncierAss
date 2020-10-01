package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Typecandidat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Typecandidat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypecandidatRepository extends JpaRepository<Typecandidat, Long> {
}
