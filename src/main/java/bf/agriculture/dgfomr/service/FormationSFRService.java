package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.FormationSFR;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link FormationSFR}.
 */
public interface FormationSFRService {

    /**
     * Save a formationSFR.
     *
     * @param formationSFR the entity to save.
     * @return the persisted entity.
     */
    FormationSFR save(FormationSFR formationSFR);

    /**
     * Get all the formationSFRS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FormationSFR> findAll(Pageable pageable);


    /**
     * Get the "id" formationSFR.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormationSFR> findOne(Long id);

    /**
     * Delete the "id" formationSFR.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
