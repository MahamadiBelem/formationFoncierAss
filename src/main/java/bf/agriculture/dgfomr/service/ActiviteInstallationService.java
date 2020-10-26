package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.ActiviteInstallation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ActiviteInstallation}.
 */
public interface ActiviteInstallationService {

    /**
     * Save a activiteInstallation.
     *
     * @param activiteInstallation the entity to save.
     * @return the persisted entity.
     */
    ActiviteInstallation save(ActiviteInstallation activiteInstallation);

    /**
     * Get all the activiteInstallations.
     *
     * @return the list of entities.
     */
    List<ActiviteInstallation> findAll();

    /**
     * Get all the activiteInstallations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ActiviteInstallation> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" activiteInstallation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActiviteInstallation> findOne(Long id);

    /**
     * Delete the "id" activiteInstallation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
