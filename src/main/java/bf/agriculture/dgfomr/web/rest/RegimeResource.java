package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Regime;
import bf.agriculture.dgfomr.service.RegimeService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Regime}.
 */
@RestController
@RequestMapping("/api")
public class RegimeResource {

    private final Logger log = LoggerFactory.getLogger(RegimeResource.class);

    private static final String ENTITY_NAME = "regime";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegimeService regimeService;

    public RegimeResource(RegimeService regimeService) {
        this.regimeService = regimeService;
    }

    /**
     * {@code POST  /regimes} : Create a new regime.
     *
     * @param regime the regime to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new regime, or with status {@code 400 (Bad Request)} if the regime has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/regimes")
    public ResponseEntity<Regime> createRegime(@Valid @RequestBody Regime regime) throws URISyntaxException {
        log.debug("REST request to save Regime : {}", regime);
        if (regime.getId() != null) {
            throw new BadRequestAlertException("A new regime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Regime result = regimeService.save(regime);
        return ResponseEntity.created(new URI("/api/regimes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /regimes} : Updates an existing regime.
     *
     * @param regime the regime to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regime,
     * or with status {@code 400 (Bad Request)} if the regime is not valid,
     * or with status {@code 500 (Internal Server Error)} if the regime couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/regimes")
    public ResponseEntity<Regime> updateRegime(@Valid @RequestBody Regime regime) throws URISyntaxException {
        log.debug("REST request to update Regime : {}", regime);
        if (regime.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Regime result = regimeService.save(regime);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, regime.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /regimes} : get all the regimes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regimes in body.
     */
    @GetMapping("/regimes")
    public List<Regime> getAllRegimes() {
        log.debug("REST request to get all Regimes");
        return regimeService.findAll();
    }

    /**
     * {@code GET  /regimes/:id} : get the "id" regime.
     *
     * @param id the id of the regime to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the regime, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/regimes/{id}")
    public ResponseEntity<Regime> getRegime(@PathVariable Long id) {
        log.debug("REST request to get Regime : {}", id);
        Optional<Regime> regime = regimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(regime);
    }

    /**
     * {@code DELETE  /regimes/:id} : delete the "id" regime.
     *
     * @param id the id of the regime to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/regimes/{id}")
    public ResponseEntity<Void> deleteRegime(@PathVariable Long id) {
        log.debug("REST request to delete Regime : {}", id);
        regimeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
