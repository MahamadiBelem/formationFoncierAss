package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.ImmaDomaine;
import bf.agriculture.dgfomr.service.ImmaDomaineService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.ImmaDomaine}.
 */
@RestController
@RequestMapping("/api")
public class ImmaDomaineResource {

    private final Logger log = LoggerFactory.getLogger(ImmaDomaineResource.class);

    private static final String ENTITY_NAME = "immaDomaine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImmaDomaineService immaDomaineService;

    public ImmaDomaineResource(ImmaDomaineService immaDomaineService) {
        this.immaDomaineService = immaDomaineService;
    }

    /**
     * {@code POST  /imma-domaines} : Create a new immaDomaine.
     *
     * @param immaDomaine the immaDomaine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new immaDomaine, or with status {@code 400 (Bad Request)} if the immaDomaine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/imma-domaines")
    public ResponseEntity<ImmaDomaine> createImmaDomaine(@RequestBody ImmaDomaine immaDomaine) throws URISyntaxException {
        log.debug("REST request to save ImmaDomaine : {}", immaDomaine);
        if (immaDomaine.getId() != null) {
            throw new BadRequestAlertException("A new immaDomaine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImmaDomaine result = immaDomaineService.save(immaDomaine);
        return ResponseEntity.created(new URI("/api/imma-domaines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /imma-domaines} : Updates an existing immaDomaine.
     *
     * @param immaDomaine the immaDomaine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated immaDomaine,
     * or with status {@code 400 (Bad Request)} if the immaDomaine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the immaDomaine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/imma-domaines")
    public ResponseEntity<ImmaDomaine> updateImmaDomaine(@RequestBody ImmaDomaine immaDomaine) throws URISyntaxException {
        log.debug("REST request to update ImmaDomaine : {}", immaDomaine);
        if (immaDomaine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImmaDomaine result = immaDomaineService.save(immaDomaine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, immaDomaine.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /imma-domaines} : get all the immaDomaines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of immaDomaines in body.
     */
    @GetMapping("/imma-domaines")
    public ResponseEntity<List<ImmaDomaine>> getAllImmaDomaines(Pageable pageable) {
        log.debug("REST request to get a page of ImmaDomaines");
        Page<ImmaDomaine> page = immaDomaineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /imma-domaines/:id} : get the "id" immaDomaine.
     *
     * @param id the id of the immaDomaine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the immaDomaine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/imma-domaines/{id}")
    public ResponseEntity<ImmaDomaine> getImmaDomaine(@PathVariable Long id) {
        log.debug("REST request to get ImmaDomaine : {}", id);
        Optional<ImmaDomaine> immaDomaine = immaDomaineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(immaDomaine);
    }

    /**
     * {@code DELETE  /imma-domaines/:id} : delete the "id" immaDomaine.
     *
     * @param id the id of the immaDomaine to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/imma-domaines/{id}")
    public ResponseEntity<Void> deleteImmaDomaine(@PathVariable Long id) {
        log.debug("REST request to delete ImmaDomaine : {}", id);
        immaDomaineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
