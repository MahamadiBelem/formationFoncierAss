package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.domain.Apprenantes;
import bf.agriculture.dgfomr.service.ApprenantesService;
import bf.agriculture.dgfomr.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.RandomStringUtils;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link bf.agriculture.dgfomr.domain.Apprenantes}.
 */
@RestController
@RequestMapping("/api")
public class ApprenantesResource {

    private final Logger log = LoggerFactory.getLogger(ApprenantesResource.class);

    private static final String ENTITY_NAME = "apprenantes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApprenantesService apprenantesService;

    public ApprenantesResource(ApprenantesService apprenantesService) {
        this.apprenantesService = apprenantesService;
    }

    /**
     * {@code POST  /apprenantes} : Create a new apprenantes.
     *
     * @param apprenantes the apprenantes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apprenantes, or with status {@code 400 (Bad Request)} if the apprenantes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apprenantes")
    public ResponseEntity<Apprenantes> createApprenantes(@Valid @RequestBody Apprenantes apprenantes) throws URISyntaxException {
        log.debug("REST request to save Apprenantes : {}", apprenantes);
        if (apprenantes.getId() != null) {
            throw new BadRequestAlertException("A new apprenantes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        apprenantes.setMatricule(Calendar.getInstance().get(Calendar.YEAR)+apprenantes.getMatricule()+ RandomStringUtils.randomAlphabetic(1).toUpperCase());
        Apprenantes result = apprenantesService.save(apprenantes);
        return ResponseEntity.created(new URI("/api/apprenantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    @GetMapping("/generate-matricule")
    public ResponseEntity<String> getMatricule()
    {
        return  ResponseEntity.status(HttpStatus.OK)
            .body(apprenantesService.generatematricule());
    }

    /**
     * {@code PUT  /apprenantes} : Updates an existing apprenantes.
     *
     * @param apprenantes the apprenantes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apprenantes,
     * or with status {@code 400 (Bad Request)} if the apprenantes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apprenantes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apprenantes")
    public ResponseEntity<Apprenantes> updateApprenantes(@Valid @RequestBody Apprenantes apprenantes) throws URISyntaxException {
        log.debug("REST request to update Apprenantes : {}", apprenantes);
        if (apprenantes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Apprenantes result = apprenantesService.save(apprenantes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apprenantes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /apprenantes} : get all the apprenantes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apprenantes in body.
     */
    @GetMapping("/apprenantes")
    public ResponseEntity<List<Apprenantes>> getAllApprenantes(Pageable pageable) {
        log.debug("REST request to get a page of Apprenantes");
        Page<Apprenantes> page = apprenantesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /apprenantes/:id} : get the "id" apprenantes.
     *
     * @param id the id of the apprenantes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apprenantes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apprenantes/{id}")
    public ResponseEntity<Apprenantes> getApprenantes(@PathVariable Long id) {
        log.debug("REST request to get Apprenantes : {}", id);
        Optional<Apprenantes> apprenantes = apprenantesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apprenantes);
    }

    /**
     * {@code DELETE  /apprenantes/:id} : delete the "id" apprenantes.
     *
     * @param id the id of the apprenantes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apprenantes/{id}")
    public ResponseEntity<Void> deleteApprenantes(@PathVariable Long id) {
        log.debug("REST request to delete Apprenantes : {}", id);
        apprenantesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
