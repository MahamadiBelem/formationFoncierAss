package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Promoteurs;
import bf.agriculture.dgfomr.service.PromoteursService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Promoteurs}.
 */
@RestController
@RequestMapping("/api")
public class PromoteursResource {

    private final Logger log = LoggerFactory.getLogger(PromoteursResource.class);

    private static final String ENTITY_NAME = "promoteurs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromoteursService promoteursService;

    public PromoteursResource(PromoteursService promoteursService) {
        this.promoteursService = promoteursService;
    }

    /**
     * {@code POST  /promoteurs} : Create a new promoteurs.
     *
     * @param promoteurs the promoteurs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promoteurs, or with status {@code 400 (Bad Request)} if the promoteurs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/promoteurs")
    public ResponseEntity<Promoteurs> createPromoteurs(@Valid @RequestBody Promoteurs promoteurs) throws URISyntaxException {
        log.debug("REST request to save Promoteurs : {}", promoteurs);
        if (promoteurs.getId() != null) {
            throw new BadRequestAlertException("A new promoteurs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Promoteurs result = promoteursService.save(promoteurs);
        return ResponseEntity.created(new URI("/api/promoteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /promoteurs} : Updates an existing promoteurs.
     *
     * @param promoteurs the promoteurs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promoteurs,
     * or with status {@code 400 (Bad Request)} if the promoteurs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promoteurs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/promoteurs")
    public ResponseEntity<Promoteurs> updatePromoteurs(@Valid @RequestBody Promoteurs promoteurs) throws URISyntaxException {
        log.debug("REST request to update Promoteurs : {}", promoteurs);
        if (promoteurs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Promoteurs result = promoteursService.save(promoteurs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promoteurs.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /promoteurs} : get all the promoteurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promoteurs in body.
     */
    @GetMapping("/promoteurs")
    public List<Promoteurs> getAllPromoteurs() {
        log.debug("REST request to get all Promoteurs");
        return promoteursService.findAll();
    }

    /**
     * {@code GET  /promoteurs/:id} : get the "id" promoteurs.
     *
     * @param id the id of the promoteurs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promoteurs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/promoteurs/{id}")
    public ResponseEntity<Promoteurs> getPromoteurs(@PathVariable Long id) {
        log.debug("REST request to get Promoteurs : {}", id);
        Optional<Promoteurs> promoteurs = promoteursService.findOne(id);
        return ResponseUtil.wrapOrNotFound(promoteurs);
    }

    /**
     * {@code DELETE  /promoteurs/:id} : delete the "id" promoteurs.
     *
     * @param id the id of the promoteurs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/promoteurs/{id}")
    public ResponseEntity<Void> deletePromoteurs(@PathVariable Long id) {
        log.debug("REST request to delete Promoteurs : {}", id);
        promoteursService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
