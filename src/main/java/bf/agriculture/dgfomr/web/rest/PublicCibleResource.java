package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.PublicCible;
import bf.agriculture.dgfomr.service.PublicCibleService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.PublicCible}.
 */
@RestController
@RequestMapping("/api")
public class PublicCibleResource {

    private final Logger log = LoggerFactory.getLogger(PublicCibleResource.class);

    private static final String ENTITY_NAME = "publicCible";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PublicCibleService publicCibleService;

    public PublicCibleResource(PublicCibleService publicCibleService) {
        this.publicCibleService = publicCibleService;
    }

    /**
     * {@code POST  /public-cibles} : Create a new publicCible.
     *
     * @param publicCible the publicCible to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new publicCible, or with status {@code 400 (Bad Request)} if the publicCible has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/public-cibles")
    public ResponseEntity<PublicCible> createPublicCible(@Valid @RequestBody PublicCible publicCible) throws URISyntaxException {
        log.debug("REST request to save PublicCible : {}", publicCible);
        if (publicCible.getId() != null) {
            throw new BadRequestAlertException("A new publicCible cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PublicCible result = publicCibleService.save(publicCible);
        return ResponseEntity.created(new URI("/api/public-cibles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /public-cibles} : Updates an existing publicCible.
     *
     * @param publicCible the publicCible to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated publicCible,
     * or with status {@code 400 (Bad Request)} if the publicCible is not valid,
     * or with status {@code 500 (Internal Server Error)} if the publicCible couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/public-cibles")
    public ResponseEntity<PublicCible> updatePublicCible(@Valid @RequestBody PublicCible publicCible) throws URISyntaxException {
        log.debug("REST request to update PublicCible : {}", publicCible);
        if (publicCible.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublicCible result = publicCibleService.save(publicCible);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, publicCible.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /public-cibles} : get all the publicCibles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of publicCibles in body.
     */
    @GetMapping("/public-cibles")
    public List<PublicCible> getAllPublicCibles() {
        log.debug("REST request to get all PublicCibles");
        return publicCibleService.findAll();
    }

    /**
     * {@code GET  /public-cibles/:id} : get the "id" publicCible.
     *
     * @param id the id of the publicCible to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the publicCible, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public-cibles/{id}")
    public ResponseEntity<PublicCible> getPublicCible(@PathVariable Long id) {
        log.debug("REST request to get PublicCible : {}", id);
        Optional<PublicCible> publicCible = publicCibleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publicCible);
    }

    /**
     * {@code DELETE  /public-cibles/:id} : delete the "id" publicCible.
     *
     * @param id the id of the publicCible to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/public-cibles/{id}")
    public ResponseEntity<Void> deletePublicCible(@PathVariable Long id) {
        log.debug("REST request to delete PublicCible : {}", id);
        publicCibleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
