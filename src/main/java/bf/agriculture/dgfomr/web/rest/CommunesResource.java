package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Communes;
import bf.agriculture.dgfomr.service.CommunesService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Communes}.
 */
@RestController
@RequestMapping("/api")
public class CommunesResource {

    private final Logger log = LoggerFactory.getLogger(CommunesResource.class);

    private static final String ENTITY_NAME = "communes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunesService communesService;

    public CommunesResource(CommunesService communesService) {
        this.communesService = communesService;
    }

    /**
     * {@code POST  /communes} : Create a new communes.
     *
     * @param communes the communes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communes, or with status {@code 400 (Bad Request)} if the communes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/communes")
    public ResponseEntity<Communes> createCommunes(@Valid @RequestBody Communes communes) throws URISyntaxException {
        log.debug("REST request to save Communes : {}", communes);
        if (communes.getId() != null) {
            throw new BadRequestAlertException("A new communes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Communes result = communesService.save(communes);
        return ResponseEntity.created(new URI("/api/communes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /communes} : Updates an existing communes.
     *
     * @param communes the communes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communes,
     * or with status {@code 400 (Bad Request)} if the communes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/communes")
    public ResponseEntity<Communes> updateCommunes(@Valid @RequestBody Communes communes) throws URISyntaxException {
        log.debug("REST request to update Communes : {}", communes);
        if (communes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Communes result = communesService.save(communes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /communes} : get all the communes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communes in body.
     */
    @GetMapping("/communes")
    public List<Communes> getAllCommunes() {
        log.debug("REST request to get all Communes");
        return communesService.findAll();
    }

    /**
     * {@code GET  /communes/:id} : get the "id" communes.
     *
     * @param id the id of the communes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/communes/{id}")
    public ResponseEntity<Communes> getCommunes(@PathVariable Long id) {
        log.debug("REST request to get Communes : {}", id);
        Optional<Communes> communes = communesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(communes);
    }

    /**
     * {@code DELETE  /communes/:id} : delete the "id" communes.
     *
     * @param id the id of the communes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/communes/{id}")
    public ResponseEntity<Void> deleteCommunes(@PathVariable Long id) {
        log.debug("REST request to delete Communes : {}", id);
        communesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
