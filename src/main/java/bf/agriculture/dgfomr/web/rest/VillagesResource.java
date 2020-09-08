package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Villages;
import bf.agriculture.dgfomr.service.VillagesService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Villages}.
 */
@RestController
@RequestMapping("/api")
public class VillagesResource {

    private final Logger log = LoggerFactory.getLogger(VillagesResource.class);

    private static final String ENTITY_NAME = "villages";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VillagesService villagesService;

    public VillagesResource(VillagesService villagesService) {
        this.villagesService = villagesService;
    }

    /**
     * {@code POST  /villages} : Create a new villages.
     *
     * @param villages the villages to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new villages, or with status {@code 400 (Bad Request)} if the villages has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/villages")
    public ResponseEntity<Villages> createVillages(@Valid @RequestBody Villages villages) throws URISyntaxException {
        log.debug("REST request to save Villages : {}", villages);
        if (villages.getId() != null) {
            throw new BadRequestAlertException("A new villages cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Villages result = villagesService.save(villages);
        return ResponseEntity.created(new URI("/api/villages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /villages} : Updates an existing villages.
     *
     * @param villages the villages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated villages,
     * or with status {@code 400 (Bad Request)} if the villages is not valid,
     * or with status {@code 500 (Internal Server Error)} if the villages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/villages")
    public ResponseEntity<Villages> updateVillages(@Valid @RequestBody Villages villages) throws URISyntaxException {
        log.debug("REST request to update Villages : {}", villages);
        if (villages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Villages result = villagesService.save(villages);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, villages.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /villages} : get all the villages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of villages in body.
     */
    @GetMapping("/villages")
    public List<Villages> getAllVillages() {
        log.debug("REST request to get all Villages");
        return villagesService.findAll();
    }

    /**
     * {@code GET  /villages/:id} : get the "id" villages.
     *
     * @param id the id of the villages to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the villages, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/villages/{id}")
    public ResponseEntity<Villages> getVillages(@PathVariable Long id) {
        log.debug("REST request to get Villages : {}", id);
        Optional<Villages> villages = villagesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(villages);
    }

    /**
     * {@code DELETE  /villages/:id} : delete the "id" villages.
     *
     * @param id the id of the villages to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/villages/{id}")
    public ResponseEntity<Void> deleteVillages(@PathVariable Long id) {
        log.debug("REST request to delete Villages : {}", id);
        villagesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
