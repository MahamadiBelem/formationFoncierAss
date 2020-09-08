package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Contributions;
import bf.agriculture.dgfomr.service.ContributionsService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Contributions}.
 */
@RestController
@RequestMapping("/api")
public class ContributionsResource {

    private final Logger log = LoggerFactory.getLogger(ContributionsResource.class);

    private static final String ENTITY_NAME = "contributions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContributionsService contributionsService;

    public ContributionsResource(ContributionsService contributionsService) {
        this.contributionsService = contributionsService;
    }

    /**
     * {@code POST  /contributions} : Create a new contributions.
     *
     * @param contributions the contributions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contributions, or with status {@code 400 (Bad Request)} if the contributions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contributions")
    public ResponseEntity<Contributions> createContributions(@Valid @RequestBody Contributions contributions) throws URISyntaxException {
        log.debug("REST request to save Contributions : {}", contributions);
        if (contributions.getId() != null) {
            throw new BadRequestAlertException("A new contributions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contributions result = contributionsService.save(contributions);
        return ResponseEntity.created(new URI("/api/contributions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contributions} : Updates an existing contributions.
     *
     * @param contributions the contributions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contributions,
     * or with status {@code 400 (Bad Request)} if the contributions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contributions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contributions")
    public ResponseEntity<Contributions> updateContributions(@Valid @RequestBody Contributions contributions) throws URISyntaxException {
        log.debug("REST request to update Contributions : {}", contributions);
        if (contributions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Contributions result = contributionsService.save(contributions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contributions.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contributions} : get all the contributions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contributions in body.
     */
    @GetMapping("/contributions")
    public List<Contributions> getAllContributions() {
        log.debug("REST request to get all Contributions");
        return contributionsService.findAll();
    }

    /**
     * {@code GET  /contributions/:id} : get the "id" contributions.
     *
     * @param id the id of the contributions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contributions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contributions/{id}")
    public ResponseEntity<Contributions> getContributions(@PathVariable Long id) {
        log.debug("REST request to get Contributions : {}", id);
        Optional<Contributions> contributions = contributionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contributions);
    }

    /**
     * {@code DELETE  /contributions/:id} : delete the "id" contributions.
     *
     * @param id the id of the contributions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contributions/{id}")
    public ResponseEntity<Void> deleteContributions(@PathVariable Long id) {
        log.debug("REST request to delete Contributions : {}", id);
        contributionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
