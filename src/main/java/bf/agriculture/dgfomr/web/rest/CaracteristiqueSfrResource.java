package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.CaracteristiqueSfr;
import bf.agriculture.dgfomr.service.CaracteristiqueSfrService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.CaracteristiqueSfr}.
 */
@RestController
@RequestMapping("/api")
public class CaracteristiqueSfrResource {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueSfrResource.class);

    private static final String ENTITY_NAME = "caracteristiqueSfr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaracteristiqueSfrService caracteristiqueSfrService;

    public CaracteristiqueSfrResource(CaracteristiqueSfrService caracteristiqueSfrService) {
        this.caracteristiqueSfrService = caracteristiqueSfrService;
    }

    /**
     * {@code POST  /caracteristique-sfrs} : Create a new caracteristiqueSfr.
     *
     * @param caracteristiqueSfr the caracteristiqueSfr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caracteristiqueSfr, or with status {@code 400 (Bad Request)} if the caracteristiqueSfr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caracteristique-sfrs")
    public ResponseEntity<CaracteristiqueSfr> createCaracteristiqueSfr(@RequestBody CaracteristiqueSfr caracteristiqueSfr) throws URISyntaxException {
        log.debug("REST request to save CaracteristiqueSfr : {}", caracteristiqueSfr);
        if (caracteristiqueSfr.getId() != null) {
            throw new BadRequestAlertException("A new caracteristiqueSfr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaracteristiqueSfr result = caracteristiqueSfrService.save(caracteristiqueSfr);
        return ResponseEntity.created(new URI("/api/caracteristique-sfrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caracteristique-sfrs} : Updates an existing caracteristiqueSfr.
     *
     * @param caracteristiqueSfr the caracteristiqueSfr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caracteristiqueSfr,
     * or with status {@code 400 (Bad Request)} if the caracteristiqueSfr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caracteristiqueSfr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caracteristique-sfrs")
    public ResponseEntity<CaracteristiqueSfr> updateCaracteristiqueSfr(@RequestBody CaracteristiqueSfr caracteristiqueSfr) throws URISyntaxException {
        log.debug("REST request to update CaracteristiqueSfr : {}", caracteristiqueSfr);
        if (caracteristiqueSfr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaracteristiqueSfr result = caracteristiqueSfrService.save(caracteristiqueSfr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caracteristiqueSfr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caracteristique-sfrs} : get all the caracteristiqueSfrs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caracteristiqueSfrs in body.
     */
    @GetMapping("/caracteristique-sfrs")
    public ResponseEntity<List<CaracteristiqueSfr>> getAllCaracteristiqueSfrs(Pageable pageable) {
        log.debug("REST request to get a page of CaracteristiqueSfrs");
        Page<CaracteristiqueSfr> page = caracteristiqueSfrService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /caracteristique-sfrs/:id} : get the "id" caracteristiqueSfr.
     *
     * @param id the id of the caracteristiqueSfr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caracteristiqueSfr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caracteristique-sfrs/{id}")
    public ResponseEntity<CaracteristiqueSfr> getCaracteristiqueSfr(@PathVariable Long id) {
        log.debug("REST request to get CaracteristiqueSfr : {}", id);
        Optional<CaracteristiqueSfr> caracteristiqueSfr = caracteristiqueSfrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caracteristiqueSfr);
    }

    /**
     * {@code DELETE  /caracteristique-sfrs/:id} : delete the "id" caracteristiqueSfr.
     *
     * @param id the id of the caracteristiqueSfr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caracteristique-sfrs/{id}")
    public ResponseEntity<Void> deleteCaracteristiqueSfr(@PathVariable Long id) {
        log.debug("REST request to delete CaracteristiqueSfr : {}", id);
        caracteristiqueSfrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
