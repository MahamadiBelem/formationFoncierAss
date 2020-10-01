package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Installation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Installation}.
 */
public interface InstallationService {

    /**
     * Save a installation.
     *
     * @param installation the entity to save.
     * @return the persisted entity.
     */
    Installation save(Installation installation);

    /**
     * Get all the installations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Installation> findAll(Pageable pageable);

    /**
     * Get all the installations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Installation> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" installation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Installation> findOne(Long id);

    /**
     * Delete the "id" installation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
