package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.FormateurCentre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link FormateurCentre}.
 */
public interface FormateurCentreService {

    /**
     * Save a formateurCentre.
     *
     * @param formateurCentre the entity to save.
     * @return the persisted entity.
     */
    FormateurCentre save(FormateurCentre formateurCentre);

    /**
     * Get all the formateurCentres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FormateurCentre> findAll(Pageable pageable);


    /**
     * Get the "id" formateurCentre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormateurCentre> findOne(Long id);

    /**
     * Delete the "id" formateurCentre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
