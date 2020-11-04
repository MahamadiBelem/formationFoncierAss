package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.CaracteristiqueSfr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link CaracteristiqueSfr}.
 */
public interface CaracteristiqueSfrService {

    /**
     * Save a caracteristiqueSfr.
     *
     * @param caracteristiqueSfr the entity to save.
     * @return the persisted entity.
     */
    CaracteristiqueSfr save(CaracteristiqueSfr caracteristiqueSfr);

    /**
     * Get all the caracteristiqueSfrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaracteristiqueSfr> findAll(Pageable pageable);


    /**
     * Get the "id" caracteristiqueSfr.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaracteristiqueSfr> findOne(Long id);

    /**
     * Delete the "id" caracteristiqueSfr.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
