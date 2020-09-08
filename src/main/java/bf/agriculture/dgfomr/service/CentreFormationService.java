package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.CentreFormation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link CentreFormation}.
 */
public interface CentreFormationService {

    /**
     * Save a centreFormation.
     *
     * @param centreFormation the entity to save.
     * @return the persisted entity.
     */
    CentreFormation save(CentreFormation centreFormation);

    /**
     * Get all the centreFormations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CentreFormation> findAll(Pageable pageable);

    /**
     * Get all the centreFormations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<CentreFormation> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" centreFormation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CentreFormation> findOne(Long id);

    /**
     * Delete the "id" centreFormation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
