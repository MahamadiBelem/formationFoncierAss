package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.TypeDemandeur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeDemandeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDemandeurRepository extends JpaRepository<TypeDemandeur, Long> {
}
