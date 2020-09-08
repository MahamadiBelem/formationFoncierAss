package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.ApprochePedagogique;
import bf.agriculture.dgfomr.service.ApprochePedagogiqueService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.ApprochePedagogique}.
 */
@RestController
@RequestMapping("/api")
public class ApprochePedagogiqueResource {

    private final Logger log = LoggerFactory.getLogger(ApprochePedagogiqueResource.class);

    private static final String ENTITY_NAME = "approchePedagogique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApprochePedagogiqueService approchePedagogiqueService;

    public ApprochePedagogiqueResource(ApprochePedagogiqueService approchePedagogiqueService) {
        this.approchePedagogiqueService = approchePedagogiqueService;
    }

    /**
     * {@code POST  /approche-pedagogiques} : Create a new approchePedagogique.
     *
     * @param approchePedagogique the approchePedagogique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new approchePedagogique, or with status {@code 400 (Bad Request)} if the approchePedagogique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/approche-pedagogiques")
    public ResponseEntity<ApprochePedagogique> createApprochePedagogique(@Valid @RequestBody ApprochePedagogique approchePedagogique) throws URISyntaxException {
        log.debug("REST request to save ApprochePedagogique : {}", approchePedagogique);
        if (approchePedagogique.getId() != null) {
            throw new BadRequestAlertException("A new approchePedagogique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApprochePedagogique result = approchePedagogiqueService.save(approchePedagogique);
        return ResponseEntity.created(new URI("/api/approche-pedagogiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /approche-pedagogiques} : Updates an existing approchePedagogique.
     *
     * @param approchePedagogique the approchePedagogique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated approchePedagogique,
     * or with status {@code 400 (Bad Request)} if the approchePedagogique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the approchePedagogique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/approche-pedagogiques")
    public ResponseEntity<ApprochePedagogique> updateApprochePedagogique(@Valid @RequestBody ApprochePedagogique approchePedagogique) throws URISyntaxException {
        log.debug("REST request to update ApprochePedagogique : {}", approchePedagogique);
        if (approchePedagogique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApprochePedagogique result = approchePedagogiqueService.save(approchePedagogique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, approchePedagogique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /approche-pedagogiques} : get all the approchePedagogiques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of approchePedagogiques in body.
     */
    @GetMapping("/approche-pedagogiques")
    public List<ApprochePedagogique> getAllApprochePedagogiques() {
        log.debug("REST request to get all ApprochePedagogiques");
        return approchePedagogiqueService.findAll();
    }

    /**
     * {@code GET  /approche-pedagogiques/:id} : get the "id" approchePedagogique.
     *
     * @param id the id of the approchePedagogique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the approchePedagogique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/approche-pedagogiques/{id}")
    public ResponseEntity<ApprochePedagogique> getApprochePedagogique(@PathVariable Long id) {
        log.debug("REST request to get ApprochePedagogique : {}", id);
        Optional<ApprochePedagogique> approchePedagogique = approchePedagogiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(approchePedagogique);
    }

    /**
     * {@code DELETE  /approche-pedagogiques/:id} : delete the "id" approchePedagogique.
     *
     * @param id the id of the approchePedagogique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/approche-pedagogiques/{id}")
    public ResponseEntity<Void> deleteApprochePedagogique(@PathVariable Long id) {
        log.debug("REST request to delete ApprochePedagogique : {}", id);
        approchePedagogiqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
