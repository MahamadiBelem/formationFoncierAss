package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.SortiePromotion;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SortiePromotion}.
 */
public interface SortiePromotionService {

    /**
     * Save a sortiePromotion.
     *
     * @param sortiePromotion the entity to save.
     * @return the persisted entity.
     */
    SortiePromotion save(SortiePromotion sortiePromotion);

    /**
     * Get all the sortiePromotions.
     *
     * @return the list of entities.
     */
    List<SortiePromotion> findAll();


    /**
     * Get the "id" sortiePromotion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SortiePromotion> findOne(Long id);

    /**
     * Delete the "id" sortiePromotion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
