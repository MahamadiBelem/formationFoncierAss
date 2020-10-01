package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Provinces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Provinces> findAll(Pageable pageable);


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
