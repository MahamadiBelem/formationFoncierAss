package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.ConditionAccess;
import bf.agriculture.dgfomr.service.ConditionAccessService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.ConditionAccess}.
 */
@RestController
@RequestMapping("/api")
public class ConditionAccessResource {

    private final Logger log = LoggerFactory.getLogger(ConditionAccessResource.class);

    private static final String ENTITY_NAME = "conditionAccess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConditionAccessService conditionAccessService;

    public ConditionAccessResource(ConditionAccessService conditionAccessService) {
        this.conditionAccessService = conditionAccessService;
    }

    /**
     * {@code POST  /condition-accesses} : Create a new conditionAccess.
     *
     * @param conditionAccess the conditionAccess to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conditionAccess, or with status {@code 400 (Bad Request)} if the conditionAccess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/condition-accesses")
    public ResponseEntity<ConditionAccess> createConditionAccess(@Valid @RequestBody ConditionAccess conditionAccess) throws URISyntaxException {
        log.debug("REST request to save ConditionAccess : {}", conditionAccess);
        if (conditionAccess.getId() != null) {
            throw new BadRequestAlertException("A new conditionAccess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConditionAccess result = conditionAccessService.save(conditionAccess);
        return ResponseEntity.created(new URI("/api/condition-accesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /condition-accesses} : Updates an existing conditionAccess.
     *
     * @param conditionAccess the conditionAccess to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conditionAccess,
     * or with status {@code 400 (Bad Request)} if the conditionAccess is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conditionAccess couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/condition-accesses")
    public ResponseEntity<ConditionAccess> updateConditionAccess(@Valid @RequestBody ConditionAccess conditionAccess) throws URISyntaxException {
        log.debug("REST request to update ConditionAccess : {}", conditionAccess);
        if (conditionAccess.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConditionAccess result = conditionAccessService.save(conditionAccess);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conditionAccess.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /condition-accesses} : get all the conditionAccesses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conditionAccesses in body.
     */
    @GetMapping("/condition-accesses")
    public List<ConditionAccess> getAllConditionAccesses() {
        log.debug("REST request to get all ConditionAccesses");
        return conditionAccessService.findAll();
    }

    /**
     * {@code GET  /condition-accesses/:id} : get the "id" conditionAccess.
     *
     * @param id the id of the conditionAccess to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conditionAccess, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/condition-accesses/{id}")
    public ResponseEntity<ConditionAccess> getConditionAccess(@PathVariable Long id) {
        log.debug("REST request to get ConditionAccess : {}", id);
        Optional<ConditionAccess> conditionAccess = conditionAccessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conditionAccess);
    }

    /**
     * {@code DELETE  /condition-accesses/:id} : delete the "id" conditionAccess.
     *
     * @param id the id of the conditionAccess to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/condition-accesses/{id}")
    public ResponseEntity<Void> deleteConditionAccess(@PathVariable Long id) {
        log.debug("REST request to delete ConditionAccess : {}", id);
        conditionAccessService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
