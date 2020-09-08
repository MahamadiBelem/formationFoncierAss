package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Kits;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Kits}.
 */
public interface KitsService {

    /**
     * Save a kits.
     *
     * @param kits the entity to save.
     * @return the persisted entity.
     */
    Kits save(Kits kits);

    /**
     * Get all the kits.
     *
     * @return the list of entities.
     */
    List<Kits> findAll();


    /**
     * Get the "id" kits.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Kits> findOne(Long id);

    /**
     * Delete the "id" kits.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
