package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.CentreFormation;
import bf.agriculture.dgfomr.service.CentreFormationService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.CentreFormation}.
 */
@RestController
@RequestMapping("/api")
public class CentreFormationResource {

    private final Logger log = LoggerFactory.getLogger(CentreFormationResource.class);

    private static final String ENTITY_NAME = "centreFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CentreFormationService centreFormationService;

    public CentreFormationResource(CentreFormationService centreFormationService) {
        this.centreFormationService = centreFormationService;
    }

    /**
     * {@code POST  /centre-formations} : Create a new centreFormation.
     *
     * @param centreFormation the centreFormation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new centreFormation, or with status {@code 400 (Bad Request)} if the centreFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/centre-formations")
    public ResponseEntity<CentreFormation> createCentreFormation(@Valid @RequestBody CentreFormation centreFormation) throws URISyntaxException {
        log.debug("REST request to save CentreFormation : {}", centreFormation);
        if (centreFormation.getId() != null) {
            throw new BadRequestAlertException("A new centreFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentreFormation result = centreFormationService.save(centreFormation);
        return ResponseEntity.created(new URI("/api/centre-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /centre-formations} : Updates an existing centreFormation.
     *
     * @param centreFormation the centreFormation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centreFormation,
     * or with status {@code 400 (Bad Request)} if the centreFormation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the centreFormation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/centre-formations")
    public ResponseEntity<CentreFormation> updateCentreFormation(@Valid @RequestBody CentreFormation centreFormation) throws URISyntaxException {
        log.debug("REST request to update CentreFormation : {}", centreFormation);
        if (centreFormation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CentreFormation result = centreFormationService.save(centreFormation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, centreFormation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /centre-formations} : get all the centreFormations.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of centreFormations in body.
     */
    @GetMapping("/centre-formations")
    public ResponseEntity<List<CentreFormation>> getAllCentreFormations(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of CentreFormations");
        Page<CentreFormation> page;
        if (eagerload) {
            page = centreFormationService.findAllWithEagerRelationships(pageable);
        } else {
            page = centreFormationService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /centre-formations/:id} : get the "id" centreFormation.
     *
     * @param id the id of the centreFormation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the centreFormation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/centre-formations/{id}")
    public ResponseEntity<CentreFormation> getCentreFormation(@PathVariable Long id) {
        log.debug("REST request to get CentreFormation : {}", id);
        Optional<CentreFormation> centreFormation = centreFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centreFormation);
    }

    /**
     * {@code DELETE  /centre-formations/:id} : delete the "id" centreFormation.
     *
     * @param id the id of the centreFormation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/centre-formations/{id}")
    public ResponseEntity<Void> deleteCentreFormation(@PathVariable Long id) {
        log.debug("REST request to delete CentreFormation : {}", id);
        centreFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
