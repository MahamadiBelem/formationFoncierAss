package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Infrastructure}.
 */
public interface InfrastructureService {

    /**
     * Save a infrastructure.
     *
     * @param infrastructure the entity to save.
     * @return the persisted entity.
     */
    Infrastructure save(Infrastructure infrastructure);

    /**
     * Get all the infrastructures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Infrastructure> findAll(Pageable pageable);


    /**
     * Get the "id" infrastructure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Infrastructure> findOne(Long id);

    /**
     * Delete the "id" infrastructure.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
