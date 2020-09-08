package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Formations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Formations}.
 */
public interface FormationsService {

    /**
     * Save a formations.
     *
     * @param formations the entity to save.
     * @return the persisted entity.
     */
    Formations save(Formations formations);

    /**
     * Get all the formations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Formations> findAll(Pageable pageable);

    /**
     * Get all the formations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Formations> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" formations.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Formations> findOne(Long id);

    /**
     * Delete the "id" formations.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
