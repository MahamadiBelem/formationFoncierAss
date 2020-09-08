package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.TypeFormation;
import bf.agriculture.dgfomr.service.TypeFormationService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.TypeFormation}.
 */
@RestController
@RequestMapping("/api")
public class TypeFormationResource {

    private final Logger log = LoggerFactory.getLogger(TypeFormationResource.class);

    private static final String ENTITY_NAME = "typeFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeFormationService typeFormationService;

    public TypeFormationResource(TypeFormationService typeFormationService) {
        this.typeFormationService = typeFormationService;
    }

    /**
     * {@code POST  /type-formations} : Create a new typeFormation.
     *
     * @param typeFormation the typeFormation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeFormation, or with status {@code 400 (Bad Request)} if the typeFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-formations")
    public ResponseEntity<TypeFormation> createTypeFormation(@RequestBody TypeFormation typeFormation) throws URISyntaxException {
        log.debug("REST request to save TypeFormation : {}", typeFormation);
        if (typeFormation.getId() != null) {
            throw new BadRequestAlertException("A new typeFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeFormation result = typeFormationService.save(typeFormation);
        return ResponseEntity.created(new URI("/api/type-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-formations} : Updates an existing typeFormation.
     *
     * @param typeFormation the typeFormation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeFormation,
     * or with status {@code 400 (Bad Request)} if the typeFormation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeFormation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-formations")
    public ResponseEntity<TypeFormation> updateTypeFormation(@RequestBody TypeFormation typeFormation) throws URISyntaxException {
        log.debug("REST request to update TypeFormation : {}", typeFormation);
        if (typeFormation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeFormation result = typeFormationService.save(typeFormation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeFormation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-formations} : get all the typeFormations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeFormations in body.
     */
    @GetMapping("/type-formations")
    public List<TypeFormation> getAllTypeFormations() {
        log.debug("REST request to get all TypeFormations");
        return typeFormationService.findAll();
    }

    /**
     * {@code GET  /type-formations/:id} : get the "id" typeFormation.
     *
     * @param id the id of the typeFormation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeFormation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-formations/{id}")
    public ResponseEntity<TypeFormation> getTypeFormation(@PathVariable Long id) {
        log.debug("REST request to get TypeFormation : {}", id);
        Optional<TypeFormation> typeFormation = typeFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeFormation);
    }

    /**
     * {@code DELETE  /type-formations/:id} : delete the "id" typeFormation.
     *
     * @param id the id of the typeFormation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-formations/{id}")
    public ResponseEntity<Void> deleteTypeFormation(@PathVariable Long id) {
        log.debug("REST request to delete TypeFormation : {}", id);
        typeFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
