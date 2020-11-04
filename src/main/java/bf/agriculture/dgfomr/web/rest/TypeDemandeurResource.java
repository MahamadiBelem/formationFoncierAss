package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.TypeDemandeur;
import bf.agriculture.dgfomr.service.TypeDemandeurService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.TypeDemandeur}.
 */
@RestController
@RequestMapping("/api")
public class TypeDemandeurResource {

    private final Logger log = LoggerFactory.getLogger(TypeDemandeurResource.class);

    private static final String ENTITY_NAME = "typeDemandeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeDemandeurService typeDemandeurService;

    public TypeDemandeurResource(TypeDemandeurService typeDemandeurService) {
        this.typeDemandeurService = typeDemandeurService;
    }

    /**
     * {@code POST  /type-demandeurs} : Create a new typeDemandeur.
     *
     * @param typeDemandeur the typeDemandeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeDemandeur, or with status {@code 400 (Bad Request)} if the typeDemandeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-demandeurs")
    public ResponseEntity<TypeDemandeur> createTypeDemandeur(@RequestBody TypeDemandeur typeDemandeur) throws URISyntaxException {
        log.debug("REST request to save TypeDemandeur : {}", typeDemandeur);
        if (typeDemandeur.getId() != null) {
            throw new BadRequestAlertException("A new typeDemandeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeDemandeur result = typeDemandeurService.save(typeDemandeur);
        return ResponseEntity.created(new URI("/api/type-demandeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-demandeurs} : Updates an existing typeDemandeur.
     *
     * @param typeDemandeur the typeDemandeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeDemandeur,
     * or with status {@code 400 (Bad Request)} if the typeDemandeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeDemandeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-demandeurs")
    public ResponseEntity<TypeDemandeur> updateTypeDemandeur(@RequestBody TypeDemandeur typeDemandeur) throws URISyntaxException {
        log.debug("REST request to update TypeDemandeur : {}", typeDemandeur);
        if (typeDemandeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeDemandeur result = typeDemandeurService.save(typeDemandeur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeDemandeur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-demandeurs} : get all the typeDemandeurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeDemandeurs in body.
     */
    @GetMapping("/type-demandeurs")
    public List<TypeDemandeur> getAllTypeDemandeurs() {
        log.debug("REST request to get all TypeDemandeurs");
        return typeDemandeurService.findAll();
    }

    /**
     * {@code GET  /type-demandeurs/:id} : get the "id" typeDemandeur.
     *
     * @param id the id of the typeDemandeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeDemandeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-demandeurs/{id}")
    public ResponseEntity<TypeDemandeur> getTypeDemandeur(@PathVariable Long id) {
        log.debug("REST request to get TypeDemandeur : {}", id);
        Optional<TypeDemandeur> typeDemandeur = typeDemandeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeDemandeur);
    }

    /**
     * {@code DELETE  /type-demandeurs/:id} : delete the "id" typeDemandeur.
     *
     * @param id the id of the typeDemandeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-demandeurs/{id}")
    public ResponseEntity<Void> deleteTypeDemandeur(@PathVariable Long id) {
        log.debug("REST request to delete TypeDemandeur : {}", id);
        typeDemandeurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
