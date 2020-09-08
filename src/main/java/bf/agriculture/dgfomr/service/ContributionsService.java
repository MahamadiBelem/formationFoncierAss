package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Contributions;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Contributions}.
 */
public interface ContributionsService {

    /**
     * Save a contributions.
     *
     * @param contributions the entity to save.
     * @return the persisted entity.
     */
    Contributions save(Contributions contributions);

    /**
     * Get all the contributions.
     *
     * @return the list of entities.
     */
    List<Contributions> findAll();


    /**
     * Get the "id" contributions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Contributions> findOne(Long id);

    /**
     * Delete the "id" contributions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
