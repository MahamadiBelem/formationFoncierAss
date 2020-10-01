package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.FormationCentreFormation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormationCentreFormation}.
 */
public interface FormationCentreFormationService {

    /**
     * Save a formationCentreFormation.
     *
     * @param formationCentreFormation the entity to save.
     * @return the persisted entity.
     */
    FormationCentreFormation save(FormationCentreFormation formationCentreFormation);

    /**
     * Get all the formationCentreFormations.
     *
     * @return the list of entities.
     */
    List<FormationCentreFormation> findAll();


    /**
     * Get the "id" formationCentreFormation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormationCentreFormation> findOne(Long id);

    /**
     * Delete the "id" formationCentreFormation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
