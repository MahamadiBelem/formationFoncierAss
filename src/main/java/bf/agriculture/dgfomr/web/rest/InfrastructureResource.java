package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Infrastructure;
import bf.agriculture.dgfomr.service.InfrastructureService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Infrastructure}.
 */
@RestController
@RequestMapping("/api")
public class InfrastructureResource {

    private final Logger log = LoggerFactory.getLogger(InfrastructureResource.class);

    private static final String ENTITY_NAME = "infrastructure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfrastructureService infrastructureService;

    public InfrastructureResource(InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
    }

    /**
     * {@code POST  /infrastructures} : Create a new infrastructure.
     *
     * @param infrastructure the infrastructure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new infrastructure, or with status {@code 400 (Bad Request)} if the infrastructure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infrastructures")
    public ResponseEntity<Infrastructure> createInfrastructure(@Valid @RequestBody Infrastructure infrastructure) throws URISyntaxException {
        log.debug("REST request to save Infrastructure : {}", infrastructure);
        if (infrastructure.getId() != null) {
            throw new BadRequestAlertException("A new infrastructure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Infrastructure result = infrastructureService.save(infrastructure);
        return ResponseEntity.created(new URI("/api/infrastructures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /infrastructures} : Updates an existing infrastructure.
     *
     * @param infrastructure the infrastructure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated infrastructure,
     * or with status {@code 400 (Bad Request)} if the infrastructure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the infrastructure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infrastructures")
    public ResponseEntity<Infrastructure> updateInfrastructure(@Valid @RequestBody Infrastructure infrastructure) throws URISyntaxException {
        log.debug("REST request to update Infrastructure : {}", infrastructure);
        if (infrastructure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Infrastructure result = infrastructureService.save(infrastructure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, infrastructure.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /infrastructures} : get all the infrastructures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infrastructures in body.
     */
    @GetMapping("/infrastructures")
    public ResponseEntity<List<Infrastructure>> getAllInfrastructures(Pageable pageable) {
        log.debug("REST request to get a page of Infrastructures");
        Page<Infrastructure> page = infrastructureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /infrastructures/:id} : get the "id" infrastructure.
     *
     * @param id the id of the infrastructure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the infrastructure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infrastructures/{id}")
    public ResponseEntity<Infrastructure> getInfrastructure(@PathVariable Long id) {
        log.debug("REST request to get Infrastructure : {}", id);
        Optional<Infrastructure> infrastructure = infrastructureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(infrastructure);
    }

    /**
     * {@code DELETE  /infrastructures/:id} : delete the "id" infrastructure.
     *
     * @param id the id of the infrastructure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infrastructures/{id}")
    public ResponseEntity<Void> deleteInfrastructure(@PathVariable Long id) {
        log.debug("REST request to delete Infrastructure : {}", id);
        infrastructureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
