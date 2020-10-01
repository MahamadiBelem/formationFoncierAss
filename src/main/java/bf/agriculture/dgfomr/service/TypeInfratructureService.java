package bf.agriculture.dgfomr.service;

import bf.agriculture.dgfomr.domain.TypeInfratructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TypeInfratructure}.
 */
public interface TypeInfratructureService {

    /**
     * Save a typeInfratructure.
     *
     * @param typeInfratructure the entity to save.
     * @return the persisted entity.
     */
    TypeInfratructure save(TypeInfratructure typeInfratructure);

    /**
     * Get all the typeInfratructures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeInfratructure> findAll(Pageable pageable);


    /**
     * Get the "id" typeInfratructure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeInfratructure> findOne(Long id);

    /**
     * Delete the "id" typeInfratructure.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
