package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Provinces;
import bf.agriculture.dgfomr.service.ProvincesService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Provinces}.
 */
@RestController
@RequestMapping("/api")
public class ProvincesResource {

    private final Logger log = LoggerFactory.getLogger(ProvincesResource.class);

    private static final String ENTITY_NAME = "provinces";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProvincesService provincesService;

    public ProvincesResource(ProvincesService provincesService) {
        this.provincesService = provincesService;
    }

    /**
     * {@code POST  /provinces} : Create a new provinces.
     *
     * @param provinces the provinces to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new provinces, or with status {@code 400 (Bad Request)} if the provinces has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/provinces")
    public ResponseEntity<Provinces> createProvinces(@Valid @RequestBody Provinces provinces) throws URISyntaxException {
        log.debug("REST request to save Provinces : {}", provinces);
        if (provinces.getId() != null) {
            throw new BadRequestAlertException("A new provinces cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Provinces result = provincesService.save(provinces);
        return ResponseEntity.created(new URI("/api/provinces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /provinces} : Updates an existing provinces.
     *
     * @param provinces the provinces to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated provinces,
     * or with status {@code 400 (Bad Request)} if the provinces is not valid,
     * or with status {@code 500 (Internal Server Error)} if the provinces couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/provinces")
    public ResponseEntity<Provinces> updateProvinces(@Valid @RequestBody Provinces provinces) throws URISyntaxException {
        log.debug("REST request to update Provinces : {}", provinces);
        if (provinces.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Provinces result = provincesService.save(provinces);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, provinces.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /provinces} : get all the provinces.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of provinces in body.
     */
    @GetMapping("/provinces")
    public List<Provinces> getAllProvinces() {
        log.debug("REST request to get all Provinces");
        return provincesService.findAll();
    }

    /**
     * {@code GET  /provinces/:id} : get the "id" provinces.
     *
     * @param id the id of the provinces to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the provinces, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/provinces/{id}")
    public ResponseEntity<Provinces> getProvinces(@PathVariable Long id) {
        log.debug("REST request to get Provinces : {}", id);
        Optional<Provinces> provinces = provincesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(provinces);
    }

    /**
     * {@code DELETE  /provinces/:id} : delete the "id" provinces.
     *
     * @param id the id of the provinces to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/provinces/{id}")
    public ResponseEntity<Void> deleteProvinces(@PathVariable Long id) {
        log.debug("REST request to delete Provinces : {}", id);
        provincesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
