package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Villages;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Villages}.
 */
public interface VillagesService {

    /**
     * Save a villages.
     *
     * @param villages the entity to save.
     * @return the persisted entity.
     */
    Villages save(Villages villages);

    /**
     * Get all the villages.
     *
     * @return the list of entities.
     */
    List<Villages> findAll();


    /**
     * Get the "id" villages.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Villages> findOne(Long id);

    /**
     * Delete the "id" villages.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
