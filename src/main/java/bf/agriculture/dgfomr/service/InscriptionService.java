package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Inscription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Inscription}.
 */
public interface InscriptionService {

    /**
     * Save a inscription.
     *
     * @param inscription the entity to save.
     * @return the persisted entity.
     */
    Inscription save(Inscription inscription);

    /**
     * Get all the inscriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Inscription> findAll(Pageable pageable);


    /**
     * Get the "id" inscription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Inscription> findOne(Long id);

    /**
     * Delete the "id" inscription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
