package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Typecandidat;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Typecandidat}.
 */
public interface TypecandidatService {

    /**
     * Save a typecandidat.
     *
     * @param typecandidat the entity to save.
     * @return the persisted entity.
     */
    Typecandidat save(Typecandidat typecandidat);

    /**
     * Get all the typecandidats.
     *
     * @return the list of entities.
     */
    List<Typecandidat> findAll();


    /**
     * Get the "id" typecandidat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Typecandidat> findOne(Long id);

    /**
     * Delete the "id" typecandidat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
