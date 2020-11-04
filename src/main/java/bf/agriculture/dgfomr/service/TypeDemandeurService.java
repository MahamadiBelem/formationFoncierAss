package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.TypeDemandeur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TypeDemandeur}.
 */
public interface TypeDemandeurService {

    /**
     * Save a typeDemandeur.
     *
     * @param typeDemandeur the entity to save.
     * @return the persisted entity.
     */
    TypeDemandeur save(TypeDemandeur typeDemandeur);

    /**
     * Get all the typeDemandeurs.
     *
     * @return the list of entities.
     */
    List<TypeDemandeur> findAll();


    /**
     * Get the "id" typeDemandeur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeDemandeur> findOne(Long id);

    /**
     * Delete the "id" typeDemandeur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
