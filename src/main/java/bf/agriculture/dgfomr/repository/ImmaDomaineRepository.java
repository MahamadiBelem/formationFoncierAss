package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.ImmaDomaine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ImmaDomaine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmaDomaineRepository extends JpaRepository<ImmaDomaine, Long> {
}
