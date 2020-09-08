package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.TypeInfratructure;
import bf.agriculture.dgfomr.service.TypeInfratructureService;
import bf.agriculture.dgfomr.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.TypeInfratructure}.
 */
@RestController
@RequestMapping("/api")
public class TypeInfratructureResource {

    private final Logger log = LoggerFactory.getLogger(TypeInfratructureResource.class);

    private static final String ENTITY_NAME = "typeInfratructure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeInfratructureService typeInfratructureService;

    public TypeInfratructureResource(TypeInfratructureService typeInfratructureService) {
        this.typeInfratructureService = typeInfratructureService;
    }

    /**
     * {@code POST  /type-infratructures} : Create a new typeInfratructure.
     *
     * @param typeInfratructure the typeInfratructure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeInfratructure, or with status {@code 400 (Bad Request)} if the typeInfratructure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-infratructures")
    public ResponseEntity<TypeInfratructure> createTypeInfratructure(@RequestBody TypeInfratructure typeInfratructure) throws URISyntaxException {
        log.debug("REST request to save TypeInfratructure : {}", typeInfratructure);
        if (typeInfratructure.getId() != null) {
            throw new BadRequestAlertException("A new typeInfratructure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeInfratructure result = typeInfratructureService.save(typeInfratructure);
        return ResponseEntity.created(new URI("/api/type-infratructures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-infratructures} : Updates an existing typeInfratructure.
     *
     * @param typeInfratructure the typeInfratructure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeInfratructure,
     * or with status {@code 400 (Bad Request)} if the typeInfratructure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeInfratructure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-infratructures")
    public ResponseEntity<TypeInfratructure> updateTypeInfratructure(@RequestBody TypeInfratructure typeInfratructure) throws URISyntaxException {
        log.debug("REST request to update TypeInfratructure : {}", typeInfratructure);
        if (typeInfratructure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeInfratructure result = typeInfratructureService.save(typeInfratructure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeInfratructure.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-infratructures} : get all the typeInfratructures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeInfratructures in body.
     */
    @GetMapping("/type-infratructures")
    public List<TypeInfratructure> getAllTypeInfratructures() {
        log.debug("REST request to get all TypeInfratructures");
        return typeInfratructureService.findAll();
    }

    /**
     * {@code GET  /type-infratructures/:id} : get the "id" typeInfratructure.
     *
     * @param id the id of the typeInfratructure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeInfratructure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-infratructures/{id}")
    public ResponseEntity<TypeInfratructure> getTypeInfratructure(@PathVariable Long id) {
        log.debug("REST request to get TypeInfratructure : {}", id);
        Optional<TypeInfratructure> typeInfratructure = typeInfratructureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeInfratructure);
    }

    /**
     * {@code DELETE  /type-infratructures/:id} : delete the "id" typeInfratructure.
     *
     * @param id the id of the typeInfratructure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-infratructures/{id}")
    public ResponseEntity<Void> deleteTypeInfratructure(@PathVariable Long id) {
        log.debug("REST request to delete TypeInfratructure : {}", id);
        typeInfratructureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
