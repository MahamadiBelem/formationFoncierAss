package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.SortiePromotion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the SortiePromotion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SortiePromotionRepository extends JpaRepository<SortiePromotion, Long> {

    @Query("select s from SortiePromotion s where s.issortie=true")
    public List<SortiePromotion>getIsSortie();

}
