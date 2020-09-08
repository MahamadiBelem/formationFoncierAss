package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.TypeInfratructure;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeInfratructure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeInfratructureRepository extends JpaRepository<TypeInfratructure, Long> {
}
