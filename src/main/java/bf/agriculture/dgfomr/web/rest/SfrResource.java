package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Sfr;
import bf.agriculture.dgfomr.service.SfrService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Sfr}.
 */
@RestController
@RequestMapping("/api")
public class SfrResource {

    private final Logger log = LoggerFactory.getLogger(SfrResource.class);

    private static final String ENTITY_NAME = "sfr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SfrService sfrService;

    public SfrResource(SfrService sfrService) {
        this.sfrService = sfrService;
    }

    /**
     * {@code POST  /sfrs} : Create a new sfr.
     *
     * @param sfr the sfr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sfr, or with status {@code 400 (Bad Request)} if the sfr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sfrs")
    public ResponseEntity<Sfr> createSfr(@RequestBody Sfr sfr) throws URISyntaxException {
        log.debug("REST request to save Sfr : {}", sfr);
        if (sfr.getId() != null) {
            throw new BadRequestAlertException("A new sfr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sfr result = sfrService.save(sfr);
        return ResponseEntity.created(new URI("/api/sfrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sfrs} : Updates an existing sfr.
     *
     * @param sfr the sfr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sfr,
     * or with status {@code 400 (Bad Request)} if the sfr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sfr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sfrs")
    public ResponseEntity<Sfr> updateSfr(@RequestBody Sfr sfr) throws URISyntaxException {
        log.debug("REST request to update Sfr : {}", sfr);
        if (sfr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sfr result = sfrService.save(sfr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sfr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sfrs} : get all the sfrs.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sfrs in body.
     */
    @GetMapping("/sfrs")
    public ResponseEntity<List<Sfr>> getAllSfrs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("communes-is-null".equals(filter)) {
            log.debug("REST request to get all Sfrs where communes is null");
            return new ResponseEntity<>(sfrService.findAllWhereCommunesIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Sfrs");
        Page<Sfr> page = sfrService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sfrs/:id} : get the "id" sfr.
     *
     * @param id the id of the sfr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sfr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sfrs/{id}")
    public ResponseEntity<Sfr> getSfr(@PathVariable Long id) {
        log.debug("REST request to get Sfr : {}", id);
        Optional<Sfr> sfr = sfrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sfr);
    }

    /**
     * {@code DELETE  /sfrs/:id} : delete the "id" sfr.
     *
     * @param id the id of the sfr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sfrs/{id}")
    public ResponseEntity<Void> deleteSfr(@PathVariable Long id) {
        log.debug("REST request to delete Sfr : {}", id);
        sfrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
