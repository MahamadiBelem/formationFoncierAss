package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.DomaineFormation;
import bf.agriculture.dgfomr.service.DomaineFormationService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.DomaineFormation}.
 */
@RestController
@RequestMapping("/api")
public class DomaineFormationResource {

    private final Logger log = LoggerFactory.getLogger(DomaineFormationResource.class);

    private static final String ENTITY_NAME = "domaineFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DomaineFormationService domaineFormationService;

    public DomaineFormationResource(DomaineFormationService domaineFormationService) {
        this.domaineFormationService = domaineFormationService;
    }

    /**
     * {@code POST  /domaine-formations} : Create a new domaineFormation.
     *
     * @param domaineFormation the domaineFormation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new domaineFormation, or with status {@code 400 (Bad Request)} if the domaineFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/domaine-formations")
    public ResponseEntity<DomaineFormation> createDomaineFormation(@Valid @RequestBody DomaineFormation domaineFormation) throws URISyntaxException {
        log.debug("REST request to save DomaineFormation : {}", domaineFormation);
        if (domaineFormation.getId() != null) {
            throw new BadRequestAlertException("A new domaineFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DomaineFormation result = domaineFormationService.save(domaineFormation);
        return ResponseEntity.created(new URI("/api/domaine-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /domaine-formations} : Updates an existing domaineFormation.
     *
     * @param domaineFormation the domaineFormation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domaineFormation,
     * or with status {@code 400 (Bad Request)} if the domaineFormation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the domaineFormation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/domaine-formations")
    public ResponseEntity<DomaineFormation> updateDomaineFormation(@Valid @RequestBody DomaineFormation domaineFormation) throws URISyntaxException {
        log.debug("REST request to update DomaineFormation : {}", domaineFormation);
        if (domaineFormation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DomaineFormation result = domaineFormationService.save(domaineFormation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, domaineFormation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /domaine-formations} : get all the domaineFormations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of domaineFormations in body.
     */
    @GetMapping("/domaine-formations")
    public List<DomaineFormation> getAllDomaineFormations() {
        log.debug("REST request to get all DomaineFormations");
        return domaineFormationService.findAll();
    }

    /**
     * {@code GET  /domaine-formations/:id} : get the "id" domaineFormation.
     *
     * @param id the id of the domaineFormation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the domaineFormation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/domaine-formations/{id}")
    public ResponseEntity<DomaineFormation> getDomaineFormation(@PathVariable Long id) {
        log.debug("REST request to get DomaineFormation : {}", id);
        Optional<DomaineFormation> domaineFormation = domaineFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(domaineFormation);
    }

    /**
     * {@code DELETE  /domaine-formations/:id} : delete the "id" domaineFormation.
     *
     * @param id the id of the domaineFormation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/domaine-formations/{id}")
    public ResponseEntity<Void> deleteDomaineFormation(@PathVariable Long id) {
        log.debug("REST request to delete DomaineFormation : {}", id);
        domaineFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
