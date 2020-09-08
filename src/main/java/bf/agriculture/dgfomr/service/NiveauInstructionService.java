package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.NiveauInstruction;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link NiveauInstruction}.
 */
public interface NiveauInstructionService {

    /**
     * Save a niveauInstruction.
     *
     * @param niveauInstruction the entity to save.
     * @return the persisted entity.
     */
    NiveauInstruction save(NiveauInstruction niveauInstruction);

    /**
     * Get all the niveauInstructions.
     *
     * @return the list of entities.
     */
    List<NiveauInstruction> findAll();


    /**
     * Get the "id" niveauInstruction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NiveauInstruction> findOne(Long id);

    /**
     * Delete the "id" niveauInstruction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
