package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.TypeFormation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TypeFormation}.
 */
public interface TypeFormationService {

    /**
     * Save a typeFormation.
     *
     * @param typeFormation the entity to save.
     * @return the persisted entity.
     */
    TypeFormation save(TypeFormation typeFormation);

    /**
     * Get all the typeFormations.
     *
     * @return the list of entities.
     */
    List<TypeFormation> findAll();


    /**
     * Get the "id" typeFormation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeFormation> findOne(Long id);

    /**
     * Delete the "id" typeFormation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
