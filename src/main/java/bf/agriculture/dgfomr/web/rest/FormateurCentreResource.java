package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.FormateurCentre;
import bf.agriculture.dgfomr.service.FormateurCentreService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.FormateurCentre}.
 */
@RestController
@RequestMapping("/api")
public class FormateurCentreResource {

    private final Logger log = LoggerFactory.getLogger(FormateurCentreResource.class);

    private static final String ENTITY_NAME = "formateurCentre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormateurCentreService formateurCentreService;

    public FormateurCentreResource(FormateurCentreService formateurCentreService) {
        this.formateurCentreService = formateurCentreService;
    }

    /**
     * {@code POST  /formateur-centres} : Create a new formateurCentre.
     *
     * @param formateurCentre the formateurCentre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formateurCentre, or with status {@code 400 (Bad Request)} if the formateurCentre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/formateur-centres")
    public ResponseEntity<FormateurCentre> createFormateurCentre(@Valid @RequestBody FormateurCentre formateurCentre) throws URISyntaxException {
        log.debug("REST request to save FormateurCentre : {}", formateurCentre);
        if (formateurCentre.getId() != null) {
            throw new BadRequestAlertException("A new formateurCentre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormateurCentre result = formateurCentreService.save(formateurCentre);
        return ResponseEntity.created(new URI("/api/formateur-centres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /formateur-centres} : Updates an existing formateurCentre.
     *
     * @param formateurCentre the formateurCentre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formateurCentre,
     * or with status {@code 400 (Bad Request)} if the formateurCentre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formateurCentre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/formateur-centres")
    public ResponseEntity<FormateurCentre> updateFormateurCentre(@Valid @RequestBody FormateurCentre formateurCentre) throws URISyntaxException {
        log.debug("REST request to update FormateurCentre : {}", formateurCentre);
        if (formateurCentre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormateurCentre result = formateurCentreService.save(formateurCentre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formateurCentre.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /formateur-centres} : get all the formateurCentres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formateurCentres in body.
     */
    @GetMapping("/formateur-centres")
    public List<FormateurCentre> getAllFormateurCentres() {
        log.debug("REST request to get all FormateurCentres");
        return formateurCentreService.findAll();
    }

    /**
     * {@code GET  /formateur-centres/:id} : get the "id" formateurCentre.
     *
     * @param id the id of the formateurCentre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formateurCentre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/formateur-centres/{id}")
    public ResponseEntity<FormateurCentre> getFormateurCentre(@PathVariable Long id) {
        log.debug("REST request to get FormateurCentre : {}", id);
        Optional<FormateurCentre> formateurCentre = formateurCentreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formateurCentre);
    }

    /**
     * {@code DELETE  /formateur-centres/:id} : delete the "id" formateurCentre.
     *
     * @param id the id of the formateurCentre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/formateur-centres/{id}")
    public ResponseEntity<Void> deleteFormateurCentre(@PathVariable Long id) {
        log.debug("REST request to delete FormateurCentre : {}", id);
        formateurCentreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
