package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.SortiePromotion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SortiePromotion> findAll(Pageable pageable);


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

    /**
     * find student have statut is sortie
     * @return List
     */
    List<SortiePromotion>findbyIsSortie();
}
