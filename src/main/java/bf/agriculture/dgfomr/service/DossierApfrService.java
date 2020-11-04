package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.DossierApfr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DossierApfr}.
 */
public interface DossierApfrService {

    /**
     * Save a dossierApfr.
     *
     * @param dossierApfr the entity to save.
     * @return the persisted entity.
     */
    DossierApfr save(DossierApfr dossierApfr);

    /**
     * Get all the dossierApfrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DossierApfr> findAll(Pageable pageable);


    /**
     * Get the "id" dossierApfr.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DossierApfr> findOne(Long id);

    /**
     * Delete the "id" dossierApfr.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
