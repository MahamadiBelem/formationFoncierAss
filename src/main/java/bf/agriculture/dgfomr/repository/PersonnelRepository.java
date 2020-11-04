package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Personnel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Personnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
