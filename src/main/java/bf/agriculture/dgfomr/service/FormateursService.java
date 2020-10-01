package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Formateurs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Formateurs}.
 */
public interface FormateursService {

    /**
     * Save a formateurs.
     *
     * @param formateurs the entity to save.
     * @return the persisted entity.
     */
    Formateurs save(Formateurs formateurs);

    /**
     * Get all the formateurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Formateurs> findAll(Pageable pageable);


    /**
     * Get the "id" formateurs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Formateurs> findOne(Long id);

    /**
     * Delete the "id" formateurs.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
