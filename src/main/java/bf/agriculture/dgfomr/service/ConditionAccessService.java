package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.ConditionAccess;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ConditionAccess}.
 */
public interface ConditionAccessService {

    /**
     * Save a conditionAccess.
     *
     * @param conditionAccess the entity to save.
     * @return the persisted entity.
     */
    ConditionAccess save(ConditionAccess conditionAccess);

    /**
     * Get all the conditionAccesses.
     *
     * @return the list of entities.
     */
    List<ConditionAccess> findAll();


    /**
     * Get the "id" conditionAccess.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConditionAccess> findOne(Long id);

    /**
     * Delete the "id" conditionAccess.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
