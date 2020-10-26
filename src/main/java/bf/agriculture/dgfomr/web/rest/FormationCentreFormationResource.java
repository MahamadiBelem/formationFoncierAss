package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.FormationCentreFormation;
import bf.agriculture.dgfomr.service.FormationCentreFormationService;
import bf.agriculture.dgfomr.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.FormationCentreFormation}.
 */
@RestController
@RequestMapping("/api")
public class FormationCentreFormationResource {

    private final Logger log = LoggerFactory.getLogger(FormationCentreFormationResource.class);

    private static final String ENTITY_NAME = "formationCentreFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormationCentreFormationService formationCentreFormationService;

    public FormationCentreFormationResource(FormationCentreFormationService formationCentreFormationService) {
        this.formationCentreFormationService = formationCentreFormationService;
    }

    /**
     * {@code POST  /formation-centre-formations} : Create a new formationCentreFormation.
     *
     * @param formationCentreFormation the formationCentreFormation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formationCentreFormation, or with status {@code 400 (Bad Request)} if the formationCentreFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formation-centre-formations")
    public ResponseEntity<FormationCentreFormation> createFormationCentreFormation(@RequestBody FormationCentreFormation formationCentreFormation) throws URISyntaxException {
        log.debug("REST request to save FormationCentreFormation : {}", formationCentreFormation);
        if (formationCentreFormation.getId() != null) {
            throw new BadRequestAlertException("A new formationCentreFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormationCentreFormation result = formationCentreFormationService.save(formationCentreFormation);
        return ResponseEntity.created(new URI("/api/formation-centre-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formation-centre-formations} : Updates an existing formationCentreFormation.
     *
     * @param formationCentreFormation the formationCentreFormation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formationCentreFormation,
     * or with status {@code 400 (Bad Request)} if the formationCentreFormation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formationCentreFormation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formation-centre-formations")
    public ResponseEntity<FormationCentreFormation> updateFormationCentreFormation(@RequestBody FormationCentreFormation formationCentreFormation) throws URISyntaxException {
        log.debug("REST request to update FormationCentreFormation : {}", formationCentreFormation);
        if (formationCentreFormation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormationCentreFormation result = formationCentreFormationService.save(formationCentreFormation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formationCentreFormation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formation-centre-formations} : get all the formationCentreFormations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formationCentreFormations in body.
     */
    @GetMapping("/formation-centre-formations")
    public ResponseEntity<List<FormationCentreFormation>> getAllFormationCentreFormations(Pageable pageable) {
        log.debug("REST request to get a page of FormationCentreFormations");
        Page<FormationCentreFormation> page = formationCentreFormationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /formation-centre-formations/:id} : get the "id" formationCentreFormation.
     *
     * @param id the id of the formationCentreFormation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formationCentreFormation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formation-centre-formations/{id}")
    public ResponseEntity<FormationCentreFormation> getFormationCentreFormation(@PathVariable Long id) {
        log.debug("REST request to get FormationCentreFormation : {}", id);
        Optional<FormationCentreFormation> formationCentreFormation = formationCentreFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formationCentreFormation);
    }

    /**
     * {@code DELETE  /formation-centre-formations/:id} : delete the "id" formationCentreFormation.
     *
     * @param id the id of the formationCentreFormation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formation-centre-formations/{id}")
    public ResponseEntity<Void> deleteFormationCentreFormation(@PathVariable Long id) {
        log.debug("REST request to delete FormationCentreFormation : {}", id);
        formationCentreFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
