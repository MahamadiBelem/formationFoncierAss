package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Typecandidat;
import bf.agriculture.dgfomr.service.TypecandidatService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Typecandidat}.
 */
@RestController
@RequestMapping("/api")
public class TypecandidatResource {

    private final Logger log = LoggerFactory.getLogger(TypecandidatResource.class);

    private static final String ENTITY_NAME = "typecandidat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypecandidatService typecandidatService;

    public TypecandidatResource(TypecandidatService typecandidatService) {
        this.typecandidatService = typecandidatService;
    }

    /**
     * {@code POST  /typecandidats} : Create a new typecandidat.
     *
     * @param typecandidat the typecandidat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typecandidat, or with status {@code 400 (Bad Request)} if the typecandidat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typecandidats")
    public ResponseEntity<Typecandidat> createTypecandidat(@Valid @RequestBody Typecandidat typecandidat) throws URISyntaxException {
        log.debug("REST request to save Typecandidat : {}", typecandidat);
        if (typecandidat.getId() != null) {
            throw new BadRequestAlertException("A new typecandidat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Typecandidat result = typecandidatService.save(typecandidat);
        return ResponseEntity.created(new URI("/api/typecandidats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /typecandidats} : Updates an existing typecandidat.
     *
     * @param typecandidat the typecandidat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typecandidat,
     * or with status {@code 400 (Bad Request)} if the typecandidat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typecandidat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typecandidats")
    public ResponseEntity<Typecandidat> updateTypecandidat(@Valid @RequestBody Typecandidat typecandidat) throws URISyntaxException {
        log.debug("REST request to update Typecandidat : {}", typecandidat);
        if (typecandidat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Typecandidat result = typecandidatService.save(typecandidat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typecandidat.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /typecandidats} : get all the typecandidats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typecandidats in body.
     */
    @GetMapping("/typecandidats")
    public List<Typecandidat> getAllTypecandidats() {
        log.debug("REST request to get all Typecandidats");
        return typecandidatService.findAll();
    }

    /**
     * {@code GET  /typecandidats/:id} : get the "id" typecandidat.
     *
     * @param id the id of the typecandidat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typecandidat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/typecandidats/{id}")
    public ResponseEntity<Typecandidat> getTypecandidat(@PathVariable Long id) {
        log.debug("REST request to get Typecandidat : {}", id);
        Optional<Typecandidat> typecandidat = typecandidatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typecandidat);
    }

    /**
     * {@code DELETE  /typecandidats/:id} : delete the "id" typecandidat.
     *
     * @param id the id of the typecandidat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typecandidats/{id}")
    public ResponseEntity<Void> deleteTypecandidat(@PathVariable Long id) {
        log.debug("REST request to delete Typecandidat : {}", id);
        typecandidatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
