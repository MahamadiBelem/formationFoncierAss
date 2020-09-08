package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.SortiePromotion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SortiePromotion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SortiePromotionRepository extends JpaRepository<SortiePromotion, Long> {
}
