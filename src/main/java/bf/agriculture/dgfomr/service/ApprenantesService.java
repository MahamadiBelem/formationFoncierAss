package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Apprenantes;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Apprenantes}.
 */
public interface ApprenantesService {

    /**
     * Save a apprenantes.
     *
     * @param apprenantes the entity to save.
     * @return the persisted entity.
     */
    Apprenantes save(Apprenantes apprenantes);

    /**
     * Get all the apprenantes.
     *
     * @return the list of entities.
     */
    List<Apprenantes> findAll();


    /**
     * Get the "id" apprenantes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Apprenantes> findOne(Long id);

    /**
     * Delete the "id" apprenantes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
