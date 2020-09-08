package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.NiveauInstruction;
import bf.agriculture.dgfomr.service.NiveauInstructionService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.NiveauInstruction}.
 */
@RestController
@RequestMapping("/api")
public class NiveauInstructionResource {

    private final Logger log = LoggerFactory.getLogger(NiveauInstructionResource.class);

    private static final String ENTITY_NAME = "niveauInstruction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NiveauInstructionService niveauInstructionService;

    public NiveauInstructionResource(NiveauInstructionService niveauInstructionService) {
        this.niveauInstructionService = niveauInstructionService;
    }

    /**
     * {@code POST  /niveau-instructions} : Create a new niveauInstruction.
     *
     * @param niveauInstruction the niveauInstruction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new niveauInstruction, or with status {@code 400 (Bad Request)} if the niveauInstruction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/niveau-instructions")
    public ResponseEntity<NiveauInstruction> createNiveauInstruction(@Valid @RequestBody NiveauInstruction niveauInstruction) throws URISyntaxException {
        log.debug("REST request to save NiveauInstruction : {}", niveauInstruction);
        if (niveauInstruction.getId() != null) {
            throw new BadRequestAlertException("A new niveauInstruction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NiveauInstruction result = niveauInstructionService.save(niveauInstruction);
        return ResponseEntity.created(new URI("/api/niveau-instructions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /niveau-instructions} : Updates an existing niveauInstruction.
     *
     * @param niveauInstruction the niveauInstruction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated niveauInstruction,
     * or with status {@code 400 (Bad Request)} if the niveauInstruction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the niveauInstruction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/niveau-instructions")
    public ResponseEntity<NiveauInstruction> updateNiveauInstruction(@Valid @RequestBody NiveauInstruction niveauInstruction) throws URISyntaxException {
        log.debug("REST request to update NiveauInstruction : {}", niveauInstruction);
        if (niveauInstruction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NiveauInstruction result = niveauInstructionService.save(niveauInstruction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, niveauInstruction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /niveau-instructions} : get all the niveauInstructions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of niveauInstructions in body.
     */
    @GetMapping("/niveau-instructions")
    public List<NiveauInstruction> getAllNiveauInstructions() {
        log.debug("REST request to get all NiveauInstructions");
        return niveauInstructionService.findAll();
    }

    /**
     * {@code GET  /niveau-instructions/:id} : get the "id" niveauInstruction.
     *
     * @param id the id of the niveauInstruction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the niveauInstruction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/niveau-instructions/{id}")
    public ResponseEntity<NiveauInstruction> getNiveauInstruction(@PathVariable Long id) {
        log.debug("REST request to get NiveauInstruction : {}", id);
        Optional<NiveauInstruction> niveauInstruction = niveauInstructionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niveauInstruction);
    }

    /**
     * {@code DELETE  /niveau-instructions/:id} : delete the "id" niveauInstruction.
     *
     * @param id the id of the niveauInstruction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/niveau-instructions/{id}")
    public ResponseEntity<Void> deleteNiveauInstruction(@PathVariable Long id) {
        log.debug("REST request to delete NiveauInstruction : {}", id);
        niveauInstructionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
