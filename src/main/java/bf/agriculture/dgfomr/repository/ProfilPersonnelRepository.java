package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.ProfilPersonnel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProfilPersonnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilPersonnelRepository extends JpaRepository<ProfilPersonnel, Long> {
}
