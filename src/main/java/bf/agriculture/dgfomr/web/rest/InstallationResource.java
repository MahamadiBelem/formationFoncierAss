package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Installation;
import bf.agriculture.dgfomr.service.InstallationService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Installation}.
 */
@RestController
@RequestMapping("/api")
public class InstallationResource {

    private final Logger log = LoggerFactory.getLogger(InstallationResource.class);

    private static final String ENTITY_NAME = "installation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstallationService installationService;

    public InstallationResource(InstallationService installationService) {
        this.installationService = installationService;
    }

    /**
     * {@code POST  /installations} : Create a new installation.
     *
     * @param installation the installation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new installation, or with status {@code 400 (Bad Request)} if the installation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/installations")
    public ResponseEntity<Installation> createInstallation(@Valid @RequestBody Installation installation) throws URISyntaxException {
        log.debug("REST request to save Installation : {}", installation);
        if (installation.getId() != null) {
            throw new BadRequestAlertException("A new installation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Installation result = installationService.save(installation);
        return ResponseEntity.created(new URI("/api/installations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /installations} : Updates an existing installation.
     *
     * @param installation the installation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated installation,
     * or with status {@code 400 (Bad Request)} if the installation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the installation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/installations")
    public ResponseEntity<Installation> updateInstallation(@Valid @RequestBody Installation installation) throws URISyntaxException {
        log.debug("REST request to update Installation : {}", installation);
        if (installation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Installation result = installationService.save(installation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, installation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /installations} : get all the installations.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of installations in body.
     */
    @GetMapping("/installations")
    public List<Installation> getAllInstallations(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Installations");
        return installationService.findAll();
    }

    /**
     * {@code GET  /installations/:id} : get the "id" installation.
     *
     * @param id the id of the installation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the installation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/installations/{id}")
    public ResponseEntity<Installation> getInstallation(@PathVariable Long id) {
        log.debug("REST request to get Installation : {}", id);
        Optional<Installation> installation = installationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(installation);
    }

    /**
     * {@code DELETE  /installations/:id} : delete the "id" installation.
     *
     * @param id the id of the installation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/installations/{id}")
    public ResponseEntity<Void> deleteInstallation(@PathVariable Long id) {
        log.debug("REST request to delete Installation : {}", id);
        installationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
