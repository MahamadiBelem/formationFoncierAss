package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.SourceFiancement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SourceFiancement}.
 */
public interface SourceFiancementService {

    /**
     * Save a sourceFiancement.
     *
     * @param sourceFiancement the entity to save.
     * @return the persisted entity.
     */
    SourceFiancement save(SourceFiancement sourceFiancement);

    /**
     * Get all the sourceFiancements.
     *
     * @return the list of entities.
     */
    List<SourceFiancement> findAll();


    /**
     * Get the "id" sourceFiancement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SourceFiancement> findOne(Long id);

    /**
     * Delete the "id" sourceFiancement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
