package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Amenagement;
import bf.agriculture.dgfomr.service.AmenagementService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Amenagement}.
 */
@RestController
@RequestMapping("/api")
public class AmenagementResource {

    private final Logger log = LoggerFactory.getLogger(AmenagementResource.class);

    private static final String ENTITY_NAME = "amenagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AmenagementService amenagementService;

    public AmenagementResource(AmenagementService amenagementService) {
        this.amenagementService = amenagementService;
    }

    /**
     * {@code POST  /amenagements} : Create a new amenagement.
     *
     * @param amenagement the amenagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new amenagement, or with status {@code 400 (Bad Request)} if the amenagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/amenagements")
    public ResponseEntity<Amenagement> createAmenagement(@Valid @RequestBody Amenagement amenagement) throws URISyntaxException {
        log.debug("REST request to save Amenagement : {}", amenagement);
        if (amenagement.getId() != null) {
            throw new BadRequestAlertException("A new amenagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Amenagement result = amenagementService.save(amenagement);
        return ResponseEntity.created(new URI("/api/amenagements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /amenagements} : Updates an existing amenagement.
     *
     * @param amenagement the amenagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated amenagement,
     * or with status {@code 400 (Bad Request)} if the amenagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the amenagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/amenagements")
    public ResponseEntity<Amenagement> updateAmenagement(@Valid @RequestBody Amenagement amenagement) throws URISyntaxException {
        log.debug("REST request to update Amenagement : {}", amenagement);
        if (amenagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Amenagement result = amenagementService.save(amenagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, amenagement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /amenagements} : get all the amenagements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of amenagements in body.
     */
    @GetMapping("/amenagements")
    public ResponseEntity<List<Amenagement>> getAllAmenagements(Pageable pageable) {
        log.debug("REST request to get a page of Amenagements");
        Page<Amenagement> page = amenagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /amenagements/:id} : get the "id" amenagement.
     *
     * @param id the id of the amenagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the amenagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/amenagements/{id}")
    public ResponseEntity<Amenagement> getAmenagement(@PathVariable Long id) {
        log.debug("REST request to get Amenagement : {}", id);
        Optional<Amenagement> amenagement = amenagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(amenagement);
    }

    /**
     * {@code DELETE  /amenagements/:id} : delete the "id" amenagement.
     *
     * @param id the id of the amenagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/amenagements/{id}")
    public ResponseEntity<Void> deleteAmenagement(@PathVariable Long id) {
        log.debug("REST request to delete Amenagement : {}", id);
        amenagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
