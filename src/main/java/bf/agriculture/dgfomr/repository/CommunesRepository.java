package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Communes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Communes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommunesRepository extends JpaRepository<Communes, Long> {
}
