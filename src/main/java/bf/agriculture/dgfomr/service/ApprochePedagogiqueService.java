package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.ApprochePedagogique;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ApprochePedagogique}.
 */
public interface ApprochePedagogiqueService {

    /**
     * Save a approchePedagogique.
     *
     * @param approchePedagogique the entity to save.
     * @return the persisted entity.
     */
    ApprochePedagogique save(ApprochePedagogique approchePedagogique);

    /**
     * Get all the approchePedagogiques.
     *
     * @return the list of entities.
     */
    List<ApprochePedagogique> findAll();


    /**
     * Get the "id" approchePedagogique.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApprochePedagogique> findOne(Long id);

    /**
     * Delete the "id" approchePedagogique.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
