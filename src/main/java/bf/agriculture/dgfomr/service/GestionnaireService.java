package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Gestionnaire;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Gestionnaire}.
 */
public interface GestionnaireService {

    /**
     * Save a gestionnaire.
     *
     * @param gestionnaire the entity to save.
     * @return the persisted entity.
     */
    Gestionnaire save(Gestionnaire gestionnaire);

    /**
     * Get all the gestionnaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Gestionnaire> findAll(Pageable pageable);


    /**
     * Get the "id" gestionnaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Gestionnaire> findOne(Long id);

    /**
     * Delete the "id" gestionnaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
