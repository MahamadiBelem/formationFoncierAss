package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.ProfilPersonnel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ProfilPersonnel}.
 */
public interface ProfilPersonnelService {

    /**
     * Save a profilPersonnel.
     *
     * @param profilPersonnel the entity to save.
     * @return the persisted entity.
     */
    ProfilPersonnel save(ProfilPersonnel profilPersonnel);

    /**
     * Get all the profilPersonnels.
     *
     * @return the list of entities.
     */
    List<ProfilPersonnel> findAll();


    /**
     * Get the "id" profilPersonnel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfilPersonnel> findOne(Long id);

    /**
     * Delete the "id" profilPersonnel.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
