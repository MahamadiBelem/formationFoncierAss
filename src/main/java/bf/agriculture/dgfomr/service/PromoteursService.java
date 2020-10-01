package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Promoteurs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Promoteurs> findAll(Pageable pageable);


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
