package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Villages;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Villages entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VillagesRepository extends JpaRepository<Villages, Long> {
}
