package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.TypeAmenagement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TypeAmenagement}.
 */
public interface TypeAmenagementService {

    /**
     * Save a typeAmenagement.
     *
     * @param typeAmenagement the entity to save.
     * @return the persisted entity.
     */
    TypeAmenagement save(TypeAmenagement typeAmenagement);

    /**
     * Get all the typeAmenagements.
     *
     * @return the list of entities.
     */
    List<TypeAmenagement> findAll();


    /**
     * Get the "id" typeAmenagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeAmenagement> findOne(Long id);

    /**
     * Delete the "id" typeAmenagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
