package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.ImmaDomaine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ImmaDomaine}.
 */
public interface ImmaDomaineService {

    /**
     * Save a immaDomaine.
     *
     * @param immaDomaine the entity to save.
     * @return the persisted entity.
     */
    ImmaDomaine save(ImmaDomaine immaDomaine);

    /**
     * Get all the immaDomaines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ImmaDomaine> findAll(Pageable pageable);


    /**
     * Get the "id" immaDomaine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ImmaDomaine> findOne(Long id);

    /**
     * Delete the "id" immaDomaine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
