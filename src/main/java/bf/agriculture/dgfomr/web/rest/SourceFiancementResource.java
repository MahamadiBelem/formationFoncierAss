package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.SourceFiancement;
import bf.agriculture.dgfomr.service.SourceFiancementService;
import bf.agriculture.dgfomr.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.SourceFiancement}.
 */
@RestController
@RequestMapping("/api")
public class SourceFiancementResource {

    private final Logger log = LoggerFactory.getLogger(SourceFiancementResource.class);

    private static final String ENTITY_NAME = "sourceFiancement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SourceFiancementService sourceFiancementService;

    public SourceFiancementResource(SourceFiancementService sourceFiancementService) {
        this.sourceFiancementService = sourceFiancementService;
    }

    /**
     * {@code POST  /source-fiancements} : Create a new sourceFiancement.
     *
     * @param sourceFiancement the sourceFiancement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sourceFiancement, or with status {@code 400 (Bad Request)} if the sourceFiancement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/source-fiancements")
    public ResponseEntity<SourceFiancement> createSourceFiancement(@Valid @RequestBody SourceFiancement sourceFiancement) throws URISyntaxException {
        log.debug("REST request to save SourceFiancement : {}", sourceFiancement);
        if (sourceFiancement.getId() != null) {
            throw new BadRequestAlertException("A new sourceFiancement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SourceFiancement result = sourceFiancementService.save(sourceFiancement);
        return ResponseEntity.created(new URI("/api/source-fiancements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /source-fiancements} : Updates an existing sourceFiancement.
     *
     * @param sourceFiancement the sourceFiancement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sourceFiancement,
     * or with status {@code 400 (Bad Request)} if the sourceFiancement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sourceFiancement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/source-fiancements")
    public ResponseEntity<SourceFiancement> updateSourceFiancement(@Valid @RequestBody SourceFiancement sourceFiancement) throws URISyntaxException {
        log.debug("REST request to update SourceFiancement : {}", sourceFiancement);
        if (sourceFiancement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SourceFiancement result = sourceFiancementService.save(sourceFiancement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sourceFiancement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /source-fiancements} : get all the sourceFiancements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sourceFiancements in body.
     */
    @GetMapping("/source-fiancements")
    public List<SourceFiancement> getAllSourceFiancements() {
        log.debug("REST request to get all SourceFiancements");
        return sourceFiancementService.findAll();
    }

    /**
     * {@code GET  /source-fiancements/:id} : get the "id" sourceFiancement.
     *
     * @param id the id of the sourceFiancement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sourceFiancement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/source-fiancements/{id}")
    public ResponseEntity<SourceFiancement> getSourceFiancement(@PathVariable Long id) {
        log.debug("REST request to get SourceFiancement : {}", id);
        Optional<SourceFiancement> sourceFiancement = sourceFiancementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sourceFiancement);
    }

    /**
     * {@code DELETE  /source-fiancements/:id} : delete the "id" sourceFiancement.
     *
     * @param id the id of the sourceFiancement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/source-fiancements/{id}")
    public ResponseEntity<Void> deleteSourceFiancement(@PathVariable Long id) {
        log.debug("REST request to delete SourceFiancement : {}", id);
        sourceFiancementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
