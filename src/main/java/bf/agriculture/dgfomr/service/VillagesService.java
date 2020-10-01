package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Villages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Villages> findAll(Pageable pageable);


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
