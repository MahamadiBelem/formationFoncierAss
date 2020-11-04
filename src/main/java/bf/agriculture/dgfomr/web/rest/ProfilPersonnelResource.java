package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.ProfilPersonnel;
import bf.agriculture.dgfomr.service.ProfilPersonnelService;
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
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.ProfilPersonnel}.
 */
@RestController
@RequestMapping("/api")
public class ProfilPersonnelResource {

    private final Logger log = LoggerFactory.getLogger(ProfilPersonnelResource.class);

    private static final String ENTITY_NAME = "profilPersonnel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfilPersonnelService profilPersonnelService;

    public ProfilPersonnelResource(ProfilPersonnelService profilPersonnelService) {
        this.profilPersonnelService = profilPersonnelService;
    }

    /**
     * {@code POST  /profil-personnels} : Create a new profilPersonnel.
     *
     * @param profilPersonnel the profilPersonnel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profilPersonnel, or with status {@code 400 (Bad Request)} if the profilPersonnel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profil-personnels")
    public ResponseEntity<ProfilPersonnel> createProfilPersonnel(@RequestBody ProfilPersonnel profilPersonnel) throws URISyntaxException {
        log.debug("REST request to save ProfilPersonnel : {}", profilPersonnel);
        if (profilPersonnel.getId() != null) {
            throw new BadRequestAlertException("A new profilPersonnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilPersonnel result = profilPersonnelService.save(profilPersonnel);
        return ResponseEntity.created(new URI("/api/profil-personnels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profil-personnels} : Updates an existing profilPersonnel.
     *
     * @param profilPersonnel the profilPersonnel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profilPersonnel,
     * or with status {@code 400 (Bad Request)} if the profilPersonnel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profilPersonnel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profil-personnels")
    public ResponseEntity<ProfilPersonnel> updateProfilPersonnel(@RequestBody ProfilPersonnel profilPersonnel) throws URISyntaxException {
        log.debug("REST request to update ProfilPersonnel : {}", profilPersonnel);
        if (profilPersonnel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilPersonnel result = profilPersonnelService.save(profilPersonnel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profilPersonnel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profil-personnels} : get all the profilPersonnels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profilPersonnels in body.
     */
    @GetMapping("/profil-personnels")
    public List<ProfilPersonnel> getAllProfilPersonnels() {
        log.debug("REST request to get all ProfilPersonnels");
        return profilPersonnelService.findAll();
    }

    /**
     * {@code GET  /profil-personnels/:id} : get the "id" profilPersonnel.
     *
     * @param id the id of the profilPersonnel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profilPersonnel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profil-personnels/{id}")
    public ResponseEntity<ProfilPersonnel> getProfilPersonnel(@PathVariable Long id) {
        log.debug("REST request to get ProfilPersonnel : {}", id);
        Optional<ProfilPersonnel> profilPersonnel = profilPersonnelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profilPersonnel);
    }

    /**
     * {@code DELETE  /profil-personnels/:id} : delete the "id" profilPersonnel.
     *
     * @param id the id of the profilPersonnel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profil-personnels/{id}")
    public ResponseEntity<Void> deleteProfilPersonnel(@PathVariable Long id) {
        log.debug("REST request to delete ProfilPersonnel : {}", id);
        profilPersonnelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
