package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.DomaineFormation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DomaineFormation}.
 */
public interface DomaineFormationService {

    /**
     * Save a domaineFormation.
     *
     * @param domaineFormation the entity to save.
     * @return the persisted entity.
     */
    DomaineFormation save(DomaineFormation domaineFormation);

    /**
     * Get all the domaineFormations.
     *
     * @return the list of entities.
     */
    List<DomaineFormation> findAll();


    /**
     * Get the "id" domaineFormation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DomaineFormation> findOne(Long id);

    /**
     * Delete the "id" domaineFormation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
