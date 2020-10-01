package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.TypeAmenagement;
import bf.agriculture.dgfomr.service.TypeAmenagementService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.TypeAmenagement}.
 */
@RestController
@RequestMapping("/api")
public class TypeAmenagementResource {

    private final Logger log = LoggerFactory.getLogger(TypeAmenagementResource.class);

    private static final String ENTITY_NAME = "typeAmenagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeAmenagementService typeAmenagementService;

    public TypeAmenagementResource(TypeAmenagementService typeAmenagementService) {
        this.typeAmenagementService = typeAmenagementService;
    }

    /**
     * {@code POST  /type-amenagements} : Create a new typeAmenagement.
     *
     * @param typeAmenagement the typeAmenagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeAmenagement, or with status {@code 400 (Bad Request)} if the typeAmenagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-amenagements")
    public ResponseEntity<TypeAmenagement> createTypeAmenagement(@RequestBody TypeAmenagement typeAmenagement) throws URISyntaxException {
        log.debug("REST request to save TypeAmenagement : {}", typeAmenagement);
        if (typeAmenagement.getId() != null) {
            throw new BadRequestAlertException("A new typeAmenagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeAmenagement result = typeAmenagementService.save(typeAmenagement);
        return ResponseEntity.created(new URI("/api/type-amenagements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-amenagements} : Updates an existing typeAmenagement.
     *
     * @param typeAmenagement the typeAmenagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAmenagement,
     * or with status {@code 400 (Bad Request)} if the typeAmenagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeAmenagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-amenagements")
    public ResponseEntity<TypeAmenagement> updateTypeAmenagement(@RequestBody TypeAmenagement typeAmenagement) throws URISyntaxException {
        log.debug("REST request to update TypeAmenagement : {}", typeAmenagement);
        if (typeAmenagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeAmenagement result = typeAmenagementService.save(typeAmenagement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeAmenagement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-amenagements} : get all the typeAmenagements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeAmenagements in body.
     */
    @GetMapping("/type-amenagements")
    public ResponseEntity<List<TypeAmenagement>> getAllTypeAmenagements(Pageable pageable) {
        log.debug("REST request to get a page of TypeAmenagements");
        Page<TypeAmenagement> page = typeAmenagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-amenagements/:id} : get the "id" typeAmenagement.
     *
     * @param id the id of the typeAmenagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeAmenagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-amenagements/{id}")
    public ResponseEntity<TypeAmenagement> getTypeAmenagement(@PathVariable Long id) {
        log.debug("REST request to get TypeAmenagement : {}", id);
        Optional<TypeAmenagement> typeAmenagement = typeAmenagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeAmenagement);
    }

    /**
     * {@code DELETE  /type-amenagements/:id} : delete the "id" typeAmenagement.
     *
     * @param id the id of the typeAmenagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-amenagements/{id}")
    public ResponseEntity<Void> deleteTypeAmenagement(@PathVariable Long id) {
        log.debug("REST request to delete TypeAmenagement : {}", id);
        typeAmenagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
