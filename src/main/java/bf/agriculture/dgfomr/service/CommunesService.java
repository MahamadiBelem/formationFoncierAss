package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Communes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Communes}.
 */
public interface CommunesService {

    /**
     * Save a communes.
     *
     * @param communes the entity to save.
     * @return the persisted entity.
     */
    Communes save(Communes communes);

    /**
     * Get all the communes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Communes> findAll(Pageable pageable);


    /**
     * Get the "id" communes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Communes> findOne(Long id);

    /**
     * Delete the "id" communes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
