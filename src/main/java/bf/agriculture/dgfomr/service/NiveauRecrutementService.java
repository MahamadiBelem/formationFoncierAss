package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.NiveauRecrutement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link NiveauRecrutement}.
 */
public interface NiveauRecrutementService {

    /**
     * Save a niveauRecrutement.
     *
     * @param niveauRecrutement the entity to save.
     * @return the persisted entity.
     */
    NiveauRecrutement save(NiveauRecrutement niveauRecrutement);

    /**
     * Get all the niveauRecrutements.
     *
     * @return the list of entities.
     */
    List<NiveauRecrutement> findAll();


    /**
     * Get the "id" niveauRecrutement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NiveauRecrutement> findOne(Long id);

    /**
     * Delete the "id" niveauRecrutement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
