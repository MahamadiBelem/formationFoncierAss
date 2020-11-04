package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.TypeActe;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TypeActe}.
 */
public interface TypeActeService {

    /**
     * Save a typeActe.
     *
     * @param typeActe the entity to save.
     * @return the persisted entity.
     */
    TypeActe save(TypeActe typeActe);

    /**
     * Get all the typeActes.
     *
     * @return the list of entities.
     */
    List<TypeActe> findAll();


    /**
     * Get the "id" typeActe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeActe> findOne(Long id);

    /**
     * Delete the "id" typeActe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
