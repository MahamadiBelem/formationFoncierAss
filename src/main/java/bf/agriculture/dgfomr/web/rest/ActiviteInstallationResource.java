package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.ActiviteInstallation;
import bf.agriculture.dgfomr.service.ActiviteInstallationService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.ActiviteInstallation}.
 */
@RestController
@RequestMapping("/api")
public class ActiviteInstallationResource {

    private final Logger log = LoggerFactory.getLogger(ActiviteInstallationResource.class);

    private static final String ENTITY_NAME = "activiteInstallation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActiviteInstallationService activiteInstallationService;

    public ActiviteInstallationResource(ActiviteInstallationService activiteInstallationService) {
        this.activiteInstallationService = activiteInstallationService;
    }

    /**
     * {@code POST  /activite-installations} : Create a new activiteInstallation.
     *
     * @param activiteInstallation the activiteInstallation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activiteInstallation, or with status {@code 400 (Bad Request)} if the activiteInstallation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activite-installations")
    public ResponseEntity<ActiviteInstallation> createActiviteInstallation(@Valid @RequestBody ActiviteInstallation activiteInstallation) throws URISyntaxException {
        log.debug("REST request to save ActiviteInstallation : {}", activiteInstallation);
        if (activiteInstallation.getId() != null) {
            throw new BadRequestAlertException("A new activiteInstallation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActiviteInstallation result = activiteInstallationService.save(activiteInstallation);
        return ResponseEntity.created(new URI("/api/activite-installations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activite-installations} : Updates an existing activiteInstallation.
     *
     * @param activiteInstallation the activiteInstallation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activiteInstallation,
     * or with status {@code 400 (Bad Request)} if the activiteInstallation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activiteInstallation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activite-installations")
    public ResponseEntity<ActiviteInstallation> updateActiviteInstallation(@Valid @RequestBody ActiviteInstallation activiteInstallation) throws URISyntaxException {
        log.debug("REST request to update ActiviteInstallation : {}", activiteInstallation);
        if (activiteInstallation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActiviteInstallation result = activiteInstallationService.save(activiteInstallation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activiteInstallation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /activite-installations} : get all the activiteInstallations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activiteInstallations in body.
     */
    @GetMapping("/activite-installations")
    public List<ActiviteInstallation> getAllActiviteInstallations() {
        log.debug("REST request to get all ActiviteInstallations");
        return activiteInstallationService.findAll();
    }

    /**
     * {@code GET  /activite-installations/:id} : get the "id" activiteInstallation.
     *
     * @param id the id of the activiteInstallation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activiteInstallation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activite-installations/{id}")
    public ResponseEntity<ActiviteInstallation> getActiviteInstallation(@PathVariable Long id) {
        log.debug("REST request to get ActiviteInstallation : {}", id);
        Optional<ActiviteInstallation> activiteInstallation = activiteInstallationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activiteInstallation);
    }

    /**
     * {@code DELETE  /activite-installations/:id} : delete the "id" activiteInstallation.
     *
     * @param id the id of the activiteInstallation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activite-installations/{id}")
    public ResponseEntity<Void> deleteActiviteInstallation(@PathVariable Long id) {
        log.debug("REST request to delete ActiviteInstallation : {}", id);
        activiteInstallationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
