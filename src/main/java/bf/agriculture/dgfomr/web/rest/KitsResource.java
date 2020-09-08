package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Kits;
import bf.agriculture.dgfomr.service.KitsService;
import bf.agriculture.dgfomr.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Kits}.
 */
@RestController
@RequestMapping("/api")
public class KitsResource {

    private final Logger log = LoggerFactory.getLogger(KitsResource.class);

    private static final String ENTITY_NAME = "kits";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KitsService kitsService;

    public KitsResource(KitsService kitsService) {
        this.kitsService = kitsService;
    }

    /**
     * {@code POST  /kits} : Create a new kits.
     *
     * @param kits the kits to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kits, or with status {@code 400 (Bad Request)} if the kits has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kits")
    public ResponseEntity<Kits> createKits(@RequestBody Kits kits) throws URISyntaxException {
        log.debug("REST request to save Kits : {}", kits);
        if (kits.getId() != null) {
            throw new BadRequestAlertException("A new kits cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Kits result = kitsService.save(kits);
        return ResponseEntity.created(new URI("/api/kits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kits} : Updates an existing kits.
     *
     * @param kits the kits to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kits,
     * or with status {@code 400 (Bad Request)} if the kits is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kits couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kits")
    public ResponseEntity<Kits> updateKits(@RequestBody Kits kits) throws URISyntaxException {
        log.debug("REST request to update Kits : {}", kits);
        if (kits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Kits result = kitsService.save(kits);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kits.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kits} : get all the kits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kits in body.
     */
    @GetMapping("/kits")
    public List<Kits> getAllKits() {
        log.debug("REST request to get all Kits");
        return kitsService.findAll();
    }

    /**
     * {@code GET  /kits/:id} : get the "id" kits.
     *
     * @param id the id of the kits to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kits, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kits/{id}")
    public ResponseEntity<Kits> getKits(@PathVariable Long id) {
        log.debug("REST request to get Kits : {}", id);
        Optional<Kits> kits = kitsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kits);
    }

    /**
     * {@code DELETE  /kits/:id} : delete the "id" kits.
     *
     * @param id the id of the kits to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kits/{id}")
    public ResponseEntity<Void> deleteKits(@PathVariable Long id) {
        log.debug("REST request to delete Kits : {}", id);
        kitsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
