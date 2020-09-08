package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.PublicCible;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PublicCible}.
 */
public interface PublicCibleService {

    /**
     * Save a publicCible.
     *
     * @param publicCible the entity to save.
     * @return the persisted entity.
     */
    PublicCible save(PublicCible publicCible);

    /**
     * Get all the publicCibles.
     *
     * @return the list of entities.
     */
    List<PublicCible> findAll();


    /**
     * Get the "id" publicCible.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PublicCible> findOne(Long id);

    /**
     * Delete the "id" publicCible.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
