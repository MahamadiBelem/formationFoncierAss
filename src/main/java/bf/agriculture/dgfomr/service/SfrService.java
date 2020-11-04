package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.Sfr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Sfr}.
 */
public interface SfrService {

    /**
     * Save a sfr.
     *
     * @param sfr the entity to save.
     * @return the persisted entity.
     */
    Sfr save(Sfr sfr);

    /**
     * Get all the sfrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Sfr> findAll(Pageable pageable);
    /**
     * Get all the SfrDTO where Communes is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Sfr> findAllWhereCommunesIsNull();


    /**
     * Get the "id" sfr.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Sfr> findOne(Long id);

    /**
     * Delete the "id" sfr.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
