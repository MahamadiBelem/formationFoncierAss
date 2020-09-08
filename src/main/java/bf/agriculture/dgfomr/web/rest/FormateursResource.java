package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Formateurs;
import bf.agriculture.dgfomr.service.FormateursService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Formateurs}.
 */
@RestController
@RequestMapping("/api")
public class FormateursResource {

    private final Logger log = LoggerFactory.getLogger(FormateursResource.class);

    private static final String ENTITY_NAME = "formateurs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormateursService formateursService;

    public FormateursResource(FormateursService formateursService) {
        this.formateursService = formateursService;
    }

    /**
     * {@code POST  /formateurs} : Create a new formateurs.
     *
     * @param formateurs the formateurs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formateurs, or with status {@code 400 (Bad Request)} if the formateurs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formateurs")
    public ResponseEntity<Formateurs> createFormateurs(@Valid @RequestBody Formateurs formateurs) throws URISyntaxException {
        log.debug("REST request to save Formateurs : {}", formateurs);
        if (formateurs.getId() != null) {
            throw new BadRequestAlertException("A new formateurs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Formateurs result = formateursService.save(formateurs);
        return ResponseEntity.created(new URI("/api/formateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formateurs} : Updates an existing formateurs.
     *
     * @param formateurs the formateurs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formateurs,
     * or with status {@code 400 (Bad Request)} if the formateurs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formateurs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formateurs")
    public ResponseEntity<Formateurs> updateFormateurs(@Valid @RequestBody Formateurs formateurs) throws URISyntaxException {
        log.debug("REST request to update Formateurs : {}", formateurs);
        if (formateurs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Formateurs result = formateursService.save(formateurs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formateurs.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formateurs} : get all the formateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formateurs in body.
     */
    @GetMapping("/formateurs")
    public List<Formateurs> getAllFormateurs() {
        log.debug("REST request to get all Formateurs");
        return formateursService.findAll();
    }

    /**
     * {@code GET  /formateurs/:id} : get the "id" formateurs.
     *
     * @param id the id of the formateurs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formateurs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formateurs/{id}")
    public ResponseEntity<Formateurs> getFormateurs(@PathVariable Long id) {
        log.debug("REST request to get Formateurs : {}", id);
        Optional<Formateurs> formateurs = formateursService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formateurs);
    }

    /**
     * {@code DELETE  /formateurs/:id} : delete the "id" formateurs.
     *
     * @param id the id of the formateurs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formateurs/{id}")
    public ResponseEntity<Void> deleteFormateurs(@PathVariable Long id) {
        log.debug("REST request to delete Formateurs : {}", id);
        formateursService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
