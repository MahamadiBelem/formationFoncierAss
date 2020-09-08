package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Promoteurs;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Promoteurs}.
 */
public interface PromoteursService {

    /**
     * Save a promoteurs.
     *
     * @param promoteurs the entity to save.
     * @return the persisted entity.
     */
    Promoteurs save(Promoteurs promoteurs);

    /**
     * Get all the promoteurs.
     *
     * @return the list of entities.
     */
    List<Promoteurs> findAll();


    /**
     * Get the "id" promoteurs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Promoteurs> findOne(Long id);

    /**
     * Delete the "id" promoteurs.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
