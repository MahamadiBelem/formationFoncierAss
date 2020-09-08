package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Specialites;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Specialites}.
 */
public interface SpecialitesService {

    /**
     * Save a specialites.
     *
     * @param specialites the entity to save.
     * @return the persisted entity.
     */
    Specialites save(Specialites specialites);

    /**
     * Get all the specialites.
     *
     * @return the list of entities.
     */
    List<Specialites> findAll();


    /**
     * Get the "id" specialites.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Specialites> findOne(Long id);

    /**
     * Delete the "id" specialites.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
