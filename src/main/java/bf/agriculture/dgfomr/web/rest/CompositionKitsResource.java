package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.CompositionKits;
import bf.agriculture.dgfomr.service.CompositionKitsService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.CompositionKits}.
 */
@RestController
@RequestMapping("/api")
public class CompositionKitsResource {

    private final Logger log = LoggerFactory.getLogger(CompositionKitsResource.class);

    private static final String ENTITY_NAME = "compositionKits";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompositionKitsService compositionKitsService;

    public CompositionKitsResource(CompositionKitsService compositionKitsService) {
        this.compositionKitsService = compositionKitsService;
    }

    /**
     * {@code POST  /composition-kits} : Create a new compositionKits.
     *
     * @param compositionKits the compositionKits to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compositionKits, or with status {@code 400 (Bad Request)} if the compositionKits has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/composition-kits")
    public ResponseEntity<CompositionKits> createCompositionKits(@RequestBody CompositionKits compositionKits) throws URISyntaxException {
        log.debug("REST request to save CompositionKits : {}", compositionKits);
        if (compositionKits.getId() != null) {
            throw new BadRequestAlertException("A new compositionKits cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompositionKits result = compositionKitsService.save(compositionKits);
        return ResponseEntity.created(new URI("/api/composition-kits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /composition-kits} : Updates an existing compositionKits.
     *
     * @param compositionKits the compositionKits to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated compositionKits,
     * or with status {@code 400 (Bad Request)} if the compositionKits is not valid,
     * or with status {@code 500 (Internal Server Error)} if the compositionKits couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/composition-kits")
    public ResponseEntity<CompositionKits> updateCompositionKits(@RequestBody CompositionKits compositionKits) throws URISyntaxException {
        log.debug("REST request to update CompositionKits : {}", compositionKits);
        if (compositionKits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompositionKits result = compositionKitsService.save(compositionKits);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, compositionKits.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /composition-kits} : get all the compositionKits.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of compositionKits in body.
     */
    @GetMapping("/composition-kits")
    public List<CompositionKits> getAllCompositionKits(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all CompositionKits");
        return compositionKitsService.findAll();
    }

    /**
     * {@code GET  /composition-kits/:id} : get the "id" compositionKits.
     *
     * @param id the id of the compositionKits to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the compositionKits, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/composition-kits/{id}")
    public ResponseEntity<CompositionKits> getCompositionKits(@PathVariable Long id) {
        log.debug("REST request to get CompositionKits : {}", id);
        Optional<CompositionKits> compositionKits = compositionKitsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compositionKits);
    }

    /**
     * {@code DELETE  /composition-kits/:id} : delete the "id" compositionKits.
     *
     * @param id the id of the compositionKits to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/composition-kits/{id}")
    public ResponseEntity<Void> deleteCompositionKits(@PathVariable Long id) {
        log.debug("REST request to delete CompositionKits : {}", id);
        compositionKitsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
