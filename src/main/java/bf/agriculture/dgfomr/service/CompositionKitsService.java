package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.CompositionKits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CompositionKits}.
 */
public interface CompositionKitsService {

    /**
     * Save a compositionKits.
     *
     * @param compositionKits the entity to save.
     * @return the persisted entity.
     */
    CompositionKits save(CompositionKits compositionKits);

    /**
     * Get all the compositionKits.
     *
     * @return the list of entities.
     */
    List<CompositionKits> findAll();

    /**
     * Get all the compositionKits with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CompositionKits> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" compositionKits.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompositionKits> findOne(Long id);

    /**
     * Delete the "id" compositionKits.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
