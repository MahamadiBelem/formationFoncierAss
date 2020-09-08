package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.ApprochePedagogique;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ApprochePedagogique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprochePedagogiqueRepository extends JpaRepository<ApprochePedagogique, Long> {
}
