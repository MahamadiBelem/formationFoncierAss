package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Kits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Kits> findAll(Pageable pageable);


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
