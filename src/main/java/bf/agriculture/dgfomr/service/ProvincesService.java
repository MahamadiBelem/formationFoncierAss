package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Provinces;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Provinces}.
 */
public interface ProvincesService {

    /**
     * Save a provinces.
     *
     * @param provinces the entity to save.
     * @return the persisted entity.
     */
    Provinces save(Provinces provinces);

    /**
     * Get all the provinces.
     *
     * @return the list of entities.
     */
    List<Provinces> findAll();


    /**
     * Get the "id" provinces.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Provinces> findOne(Long id);

    /**
     * Delete the "id" provinces.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
