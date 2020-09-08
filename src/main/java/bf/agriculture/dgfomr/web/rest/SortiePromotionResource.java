package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.SortiePromotion;
import bf.agriculture.dgfomr.service.SortiePromotionService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.SortiePromotion}.
 */
@RestController
@RequestMapping("/api")
public class SortiePromotionResource {

    private final Logger log = LoggerFactory.getLogger(SortiePromotionResource.class);

    private static final String ENTITY_NAME = "sortiePromotion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SortiePromotionService sortiePromotionService;

    public SortiePromotionResource(SortiePromotionService sortiePromotionService) {
        this.sortiePromotionService = sortiePromotionService;
    }

    /**
     * {@code POST  /sortie-promotions} : Create a new sortiePromotion.
     *
     * @param sortiePromotion the sortiePromotion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sortiePromotion, or with status {@code 400 (Bad Request)} if the sortiePromotion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sortie-promotions")
    public ResponseEntity<SortiePromotion> createSortiePromotion(@Valid @RequestBody SortiePromotion sortiePromotion) throws URISyntaxException {
        log.debug("REST request to save SortiePromotion : {}", sortiePromotion);
        if (sortiePromotion.getId() != null) {
            throw new BadRequestAlertException("A new sortiePromotion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SortiePromotion result = sortiePromotionService.save(sortiePromotion);
        return ResponseEntity.created(new URI("/api/sortie-promotions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sortie-promotions} : Updates an existing sortiePromotion.
     *
     * @param sortiePromotion the sortiePromotion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sortiePromotion,
     * or with status {@code 400 (Bad Request)} if the sortiePromotion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sortiePromotion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sortie-promotions")
    public ResponseEntity<SortiePromotion> updateSortiePromotion(@Valid @RequestBody SortiePromotion sortiePromotion) throws URISyntaxException {
        log.debug("REST request to update SortiePromotion : {}", sortiePromotion);
        if (sortiePromotion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SortiePromotion result = sortiePromotionService.save(sortiePromotion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sortiePromotion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sortie-promotions} : get all the sortiePromotions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sortiePromotions in body.
     */
    @GetMapping("/sortie-promotions")
    public List<SortiePromotion> getAllSortiePromotions() {
        log.debug("REST request to get all SortiePromotions");
        return sortiePromotionService.findAll();
    }

    /**
     * {@code GET  /sortie-promotions/:id} : get the "id" sortiePromotion.
     *
     * @param id the id of the sortiePromotion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sortiePromotion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sortie-promotions/{id}")
    public ResponseEntity<SortiePromotion> getSortiePromotion(@PathVariable Long id) {
        log.debug("REST request to get SortiePromotion : {}", id);
        Optional<SortiePromotion> sortiePromotion = sortiePromotionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sortiePromotion);
    }

    /**
     * {@code DELETE  /sortie-promotions/:id} : delete the "id" sortiePromotion.
     *
     * @param id the id of the sortiePromotion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sortie-promotions/{id}")
    public ResponseEntity<Void> deleteSortiePromotion(@PathVariable Long id) {
        log.debug("REST request to delete SortiePromotion : {}", id);
        sortiePromotionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
