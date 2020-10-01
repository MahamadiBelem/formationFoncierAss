package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Regime;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Regime}.
 */
public interface RegimeService {

    /**
     * Save a regime.
     *
     * @param regime the entity to save.
     * @return the persisted entity.
     */
    Regime save(Regime regime);

    /**
     * Get all the regimes.
     *
     * @return the list of entities.
     */
    List<Regime> findAll();


    /**
     * Get the "id" regime.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Regime> findOne(Long id);

    /**
     * Delete the "id" regime.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
