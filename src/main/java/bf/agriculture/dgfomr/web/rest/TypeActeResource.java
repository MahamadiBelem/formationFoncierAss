package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.TypeActe;
import bf.agriculture.dgfomr.service.TypeActeService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.TypeActe}.
 */
@RestController
@RequestMapping("/api")
public class TypeActeResource {

    private final Logger log = LoggerFactory.getLogger(TypeActeResource.class);

    private static final String ENTITY_NAME = "typeActe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeActeService typeActeService;

    public TypeActeResource(TypeActeService typeActeService) {
        this.typeActeService = typeActeService;
    }

    /**
     * {@code POST  /type-actes} : Create a new typeActe.
     *
     * @param typeActe the typeActe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeActe, or with status {@code 400 (Bad Request)} if the typeActe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-actes")
    public ResponseEntity<TypeActe> createTypeActe(@RequestBody TypeActe typeActe) throws URISyntaxException {
        log.debug("REST request to save TypeActe : {}", typeActe);
        if (typeActe.getId() != null) {
            throw new BadRequestAlertException("A new typeActe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeActe result = typeActeService.save(typeActe);
        return ResponseEntity.created(new URI("/api/type-actes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-actes} : Updates an existing typeActe.
     *
     * @param typeActe the typeActe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeActe,
     * or with status {@code 400 (Bad Request)} if the typeActe is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeActe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-actes")
    public ResponseEntity<TypeActe> updateTypeActe(@RequestBody TypeActe typeActe) throws URISyntaxException {
        log.debug("REST request to update TypeActe : {}", typeActe);
        if (typeActe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeActe result = typeActeService.save(typeActe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeActe.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-actes} : get all the typeActes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeActes in body.
     */
    @GetMapping("/type-actes")
    public List<TypeActe> getAllTypeActes() {
        log.debug("REST request to get all TypeActes");
        return typeActeService.findAll();
    }

    /**
     * {@code GET  /type-actes/:id} : get the "id" typeActe.
     *
     * @param id the id of the typeActe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeActe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-actes/{id}")
    public ResponseEntity<TypeActe> getTypeActe(@PathVariable Long id) {
        log.debug("REST request to get TypeActe : {}", id);
        Optional<TypeActe> typeActe = typeActeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeActe);
    }

    /**
     * {@code DELETE  /type-actes/:id} : delete the "id" typeActe.
     *
     * @param id the id of the typeActe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-actes/{id}")
    public ResponseEntity<Void> deleteTypeActe(@PathVariable Long id) {
        log.debug("REST request to delete TypeActe : {}", id);
        typeActeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
