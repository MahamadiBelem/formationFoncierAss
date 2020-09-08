package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Specialites;
import bf.agriculture.dgfomr.service.SpecialitesService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Specialites}.
 */
@RestController
@RequestMapping("/api")
public class SpecialitesResource {

    private final Logger log = LoggerFactory.getLogger(SpecialitesResource.class);

    private static final String ENTITY_NAME = "specialites";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecialitesService specialitesService;

    public SpecialitesResource(SpecialitesService specialitesService) {
        this.specialitesService = specialitesService;
    }

    /**
     * {@code POST  /specialites} : Create a new specialites.
     *
     * @param specialites the specialites to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specialites, or with status {@code 400 (Bad Request)} if the specialites has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specialites")
    public ResponseEntity<Specialites> createSpecialites(@Valid @RequestBody Specialites specialites) throws URISyntaxException {
        log.debug("REST request to save Specialites : {}", specialites);
        if (specialites.getId() != null) {
            throw new BadRequestAlertException("A new specialites cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Specialites result = specialitesService.save(specialites);
        return ResponseEntity.created(new URI("/api/specialites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specialites} : Updates an existing specialites.
     *
     * @param specialites the specialites to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialites,
     * or with status {@code 400 (Bad Request)} if the specialites is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specialites couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialites")
    public ResponseEntity<Specialites> updateSpecialites(@Valid @RequestBody Specialites specialites) throws URISyntaxException {
        log.debug("REST request to update Specialites : {}", specialites);
        if (specialites.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Specialites result = specialitesService.save(specialites);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialites.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specialites} : get all the specialites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialites in body.
     */
    @GetMapping("/specialites")
    public List<Specialites> getAllSpecialites() {
        log.debug("REST request to get all Specialites");
        return specialitesService.findAll();
    }

    /**
     * {@code GET  /specialites/:id} : get the "id" specialites.
     *
     * @param id the id of the specialites to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specialites, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specialites/{id}")
    public ResponseEntity<Specialites> getSpecialites(@PathVariable Long id) {
        log.debug("REST request to get Specialites : {}", id);
        Optional<Specialites> specialites = specialitesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specialites);
    }

    /**
     * {@code DELETE  /specialites/:id} : delete the "id" specialites.
     *
     * @param id the id of the specialites to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specialites/{id}")
    public ResponseEntity<Void> deleteSpecialites(@PathVariable Long id) {
        log.debug("REST request to delete Specialites : {}", id);
        specialitesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
