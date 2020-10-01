package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Formations;
import bf.agriculture.dgfomr.service.FormationsService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Formations}.
 */
@RestController
@RequestMapping("/api")
public class FormationsResource {

    private final Logger log = LoggerFactory.getLogger(FormationsResource.class);

    private static final String ENTITY_NAME = "formations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormationsService formationsService;

    public FormationsResource(FormationsService formationsService) {
        this.formationsService = formationsService;
    }

    /**
     * {@code POST  /formations} : Create a new formations.
     *
     * @param formations the formations to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formations, or with status {@code 400 (Bad Request)} if the formations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formations")
    public ResponseEntity<Formations> createFormations(@RequestBody Formations formations) throws URISyntaxException {
        log.debug("REST request to save Formations : {}", formations);
        if (formations.getId() != null) {
            throw new BadRequestAlertException("A new formations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Formations result = formationsService.save(formations);
        return ResponseEntity.created(new URI("/api/formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formations} : Updates an existing formations.
     *
     * @param formations the formations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formations,
     * or with status {@code 400 (Bad Request)} if the formations is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formations")
    public ResponseEntity<Formations> updateFormations(@RequestBody Formations formations) throws URISyntaxException {
        log.debug("REST request to update Formations : {}", formations);
        if (formations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Formations result = formationsService.save(formations);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formations.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formations} : get all the formations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formations in body.
     */
    @GetMapping("/formations")
    public ResponseEntity<List<Formations>> getAllFormations(Pageable pageable) {
        log.debug("REST request to get a page of Formations");
        Page<Formations> page = formationsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /formations/:id} : get the "id" formations.
     *
     * @param id the id of the formations to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formations, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formations/{id}")
    public ResponseEntity<Formations> getFormations(@PathVariable Long id) {
        log.debug("REST request to get Formations : {}", id);
        Optional<Formations> formations = formationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formations);
    }

    /**
     * {@code DELETE  /formations/:id} : delete the "id" formations.
     *
     * @param id the id of the formations to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formations/{id}")
    public ResponseEntity<Void> deleteFormations(@PathVariable Long id) {
        log.debug("REST request to delete Formations : {}", id);
        formationsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
