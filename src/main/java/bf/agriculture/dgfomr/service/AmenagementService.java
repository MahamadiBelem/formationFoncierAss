package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Amenagement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Amenagement}.
 */
public interface AmenagementService {

    /**
     * Save a amenagement.
     *
     * @param amenagement the entity to save.
     * @return the persisted entity.
     */
    Amenagement save(Amenagement amenagement);

    /**
     * Get all the amenagements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Amenagement> findAll(Pageable pageable);


    /**
     * Get the "id" amenagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Amenagement> findOne(Long id);

    /**
     * Delete the "id" amenagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
