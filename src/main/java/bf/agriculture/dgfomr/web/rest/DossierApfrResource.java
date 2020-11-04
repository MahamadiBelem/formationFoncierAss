package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.DossierApfr;
import bf.agriculture.dgfomr.service.DossierApfrService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.DossierApfr}.
 */
@RestController
@RequestMapping("/api")
public class DossierApfrResource {

    private final Logger log = LoggerFactory.getLogger(DossierApfrResource.class);

    private static final String ENTITY_NAME = "dossierApfr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DossierApfrService dossierApfrService;

    public DossierApfrResource(DossierApfrService dossierApfrService) {
        this.dossierApfrService = dossierApfrService;
    }

    /**
     * {@code POST  /dossier-apfrs} : Create a new dossierApfr.
     *
     * @param dossierApfr the dossierApfr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dossierApfr, or with status {@code 400 (Bad Request)} if the dossierApfr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dossier-apfrs")
    public ResponseEntity<DossierApfr> createDossierApfr(@RequestBody DossierApfr dossierApfr) throws URISyntaxException {
        log.debug("REST request to save DossierApfr : {}", dossierApfr);
        if (dossierApfr.getId() != null) {
            throw new BadRequestAlertException("A new dossierApfr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DossierApfr result = dossierApfrService.save(dossierApfr);
        return ResponseEntity.created(new URI("/api/dossier-apfrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dossier-apfrs} : Updates an existing dossierApfr.
     *
     * @param dossierApfr the dossierApfr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dossierApfr,
     * or with status {@code 400 (Bad Request)} if the dossierApfr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dossierApfr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dossier-apfrs")
    public ResponseEntity<DossierApfr> updateDossierApfr(@RequestBody DossierApfr dossierApfr) throws URISyntaxException {
        log.debug("REST request to update DossierApfr : {}", dossierApfr);
        if (dossierApfr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DossierApfr result = dossierApfrService.save(dossierApfr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dossierApfr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dossier-apfrs} : get all the dossierApfrs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dossierApfrs in body.
     */
    @GetMapping("/dossier-apfrs")
    public ResponseEntity<List<DossierApfr>> getAllDossierApfrs(Pageable pageable) {
        log.debug("REST request to get a page of DossierApfrs");
        Page<DossierApfr> page = dossierApfrService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dossier-apfrs/:id} : get the "id" dossierApfr.
     *
     * @param id the id of the dossierApfr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dossierApfr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dossier-apfrs/{id}")
    public ResponseEntity<DossierApfr> getDossierApfr(@PathVariable Long id) {
        log.debug("REST request to get DossierApfr : {}", id);
        Optional<DossierApfr> dossierApfr = dossierApfrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dossierApfr);
    }

    /**
     * {@code DELETE  /dossier-apfrs/:id} : delete the "id" dossierApfr.
     *
     * @param id the id of the dossierApfr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dossier-apfrs/{id}")
    public ResponseEntity<Void> deleteDossierApfr(@PathVariable Long id) {
        log.debug("REST request to delete DossierApfr : {}", id);
        dossierApfrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
