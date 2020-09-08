package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.NiveauRecrutement;
import bf.agriculture.dgfomr.service.NiveauRecrutementService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.NiveauRecrutement}.
 */
@RestController
@RequestMapping("/api")
public class NiveauRecrutementResource {

    private final Logger log = LoggerFactory.getLogger(NiveauRecrutementResource.class);

    private static final String ENTITY_NAME = "niveauRecrutement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NiveauRecrutementService niveauRecrutementService;

    public NiveauRecrutementResource(NiveauRecrutementService niveauRecrutementService) {
        this.niveauRecrutementService = niveauRecrutementService;
    }

    /**
     * {@code POST  /niveau-recrutements} : Create a new niveauRecrutement.
     *
     * @param niveauRecrutement the niveauRecrutement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new niveauRecrutement, or with status {@code 400 (Bad Request)} if the niveauRecrutement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/niveau-recrutements")
    public ResponseEntity<NiveauRecrutement> createNiveauRecrutement(@Valid @RequestBody NiveauRecrutement niveauRecrutement) throws URISyntaxException {
        log.debug("REST request to save NiveauRecrutement : {}", niveauRecrutement);
        if (niveauRecrutement.getId() != null) {
            throw new BadRequestAlertException("A new niveauRecrutement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NiveauRecrutement result = niveauRecrutementService.save(niveauRecrutement);
        return ResponseEntity.created(new URI("/api/niveau-recrutements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /niveau-recrutements} : Updates an existing niveauRecrutement.
     *
     * @param niveauRecrutement the niveauRecrutement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated niveauRecrutement,
     * or with status {@code 400 (Bad Request)} if the niveauRecrutement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the niveauRecrutement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/niveau-recrutements")
    public ResponseEntity<NiveauRecrutement> updateNiveauRecrutement(@Valid @RequestBody NiveauRecrutement niveauRecrutement) throws URISyntaxException {
        log.debug("REST request to update NiveauRecrutement : {}", niveauRecrutement);
        if (niveauRecrutement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NiveauRecrutement result = niveauRecrutementService.save(niveauRecrutement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, niveauRecrutement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /niveau-recrutements} : get all the niveauRecrutements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of niveauRecrutements in body.
     */
    @GetMapping("/niveau-recrutements")
    public List<NiveauRecrutement> getAllNiveauRecrutements() {
        log.debug("REST request to get all NiveauRecrutements");
        return niveauRecrutementService.findAll();
    }

    /**
     * {@code GET  /niveau-recrutements/:id} : get the "id" niveauRecrutement.
     *
     * @param id the id of the niveauRecrutement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the niveauRecrutement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/niveau-recrutements/{id}")
    public ResponseEntity<NiveauRecrutement> getNiveauRecrutement(@PathVariable Long id) {
        log.debug("REST request to get NiveauRecrutement : {}", id);
        Optional<NiveauRecrutement> niveauRecrutement = niveauRecrutementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niveauRecrutement);
    }

    /**
     * {@code DELETE  /niveau-recrutements/:id} : delete the "id" niveauRecrutement.
     *
     * @param id the id of the niveauRecrutement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/niveau-recrutements/{id}")
    public ResponseEntity<Void> deleteNiveauRecrutement(@PathVariable Long id) {
        log.debug("REST request to delete NiveauRecrutement : {}", id);
        niveauRecrutementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
