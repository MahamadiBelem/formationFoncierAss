package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.FormationSFR;
import bf.agriculture.dgfomr.service.FormationSFRService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.FormationSFR}.
 */
@RestController
@RequestMapping("/api")
public class FormationSFRResource {

    private final Logger log = LoggerFactory.getLogger(FormationSFRResource.class);

    private static final String ENTITY_NAME = "formationSFR";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormationSFRService formationSFRService;

    public FormationSFRResource(FormationSFRService formationSFRService) {
        this.formationSFRService = formationSFRService;
    }

    /**
     * {@code POST  /formation-sfrs} : Create a new formationSFR.
     *
     * @param formationSFR the formationSFR to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formationSFR, or with status {@code 400 (Bad Request)} if the formationSFR has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formation-sfrs")
    public ResponseEntity<FormationSFR> createFormationSFR(@RequestBody FormationSFR formationSFR) throws URISyntaxException {
        log.debug("REST request to save FormationSFR : {}", formationSFR);
        if (formationSFR.getId() != null) {
            throw new BadRequestAlertException("A new formationSFR cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormationSFR result = formationSFRService.save(formationSFR);
        return ResponseEntity.created(new URI("/api/formation-sfrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formation-sfrs} : Updates an existing formationSFR.
     *
     * @param formationSFR the formationSFR to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formationSFR,
     * or with status {@code 400 (Bad Request)} if the formationSFR is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formationSFR couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formation-sfrs")
    public ResponseEntity<FormationSFR> updateFormationSFR(@RequestBody FormationSFR formationSFR) throws URISyntaxException {
        log.debug("REST request to update FormationSFR : {}", formationSFR);
        if (formationSFR.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormationSFR result = formationSFRService.save(formationSFR);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formationSFR.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formation-sfrs} : get all the formationSFRS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formationSFRS in body.
     */
    @GetMapping("/formation-sfrs")
    public ResponseEntity<List<FormationSFR>> getAllFormationSFRS(Pageable pageable) {
        log.debug("REST request to get a page of FormationSFRS");
        Page<FormationSFR> page = formationSFRService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /formation-sfrs/:id} : get the "id" formationSFR.
     *
     * @param id the id of the formationSFR to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formationSFR, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formation-sfrs/{id}")
    public ResponseEntity<FormationSFR> getFormationSFR(@PathVariable Long id) {
        log.debug("REST request to get FormationSFR : {}", id);
        Optional<FormationSFR> formationSFR = formationSFRService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formationSFR);
    }

    /**
     * {@code DELETE  /formation-sfrs/:id} : delete the "id" formationSFR.
     *
     * @param id the id of the formationSFR to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formation-sfrs/{id}")
    public ResponseEntity<Void> deleteFormationSFR(@PathVariable Long id) {
        log.debug("REST request to delete FormationSFR : {}", id);
        formationSFRService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
