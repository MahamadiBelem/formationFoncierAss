package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.TypeAmenagement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeAmenagement> findAll(Pageable pageable);


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
