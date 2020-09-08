package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.DomaineFormation;
import bf.agriculture.dgfomr.repository.DomaineFormationRepository;
import bf.agriculture.dgfomr.service.DomaineFormationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DomaineFormationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DomaineFormationResourceIT {

    private static final String DEFAULT_LIBELLE_DOMAINE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_DOMAINE = "BBBBBBBBBB";

    @Autowired
    private DomaineFormationRepository domaineFormationRepository;

    @Autowired
    private DomaineFormationService domaineFormationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDomaineFormationMockMvc;

    private DomaineFormation domaineFormation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DomaineFormation createEntity(EntityManager em) {
        DomaineFormation domaineFormation = new DomaineFormation()
            .libelleDomaine(DEFAULT_LIBELLE_DOMAINE);
        return domaineFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DomaineFormation createUpdatedEntity(EntityManager em) {
        DomaineFormation domaineFormation = new DomaineFormation()
            .libelleDomaine(UPDATED_LIBELLE_DOMAINE);
        return domaineFormation;
    }

    @BeforeEach
    public void initTest() {
        domaineFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createDomaineFormation() throws Exception {
        int databaseSizeBeforeCreate = domaineFormationRepository.findAll().size();
        // Create the DomaineFormation
        restDomaineFormationMockMvc.perform(post("/api/domaine-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domaineFormation)))
            .andExpect(status().isCreated());

        // Validate the DomaineFormation in the database
        List<DomaineFormation> domaineFormationList = domaineFormationRepository.findAll();
        assertThat(domaineFormationList).hasSize(databaseSizeBeforeCreate + 1);
        DomaineFormation testDomaineFormation = domaineFormationList.get(domaineFormationList.size() - 1);
        assertThat(testDomaineFormation.getLibelleDomaine()).isEqualTo(DEFAULT_LIBELLE_DOMAINE);
    }

    @Test
    @Transactional
    public void createDomaineFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = domaineFormationRepository.findAll().size();

        // Create the DomaineFormation with an existing ID
        domaineFormation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomaineFormationMockMvc.perform(post("/api/domaine-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domaineFormation)))
            .andExpect(status().isBadRequest());

        // Validate the DomaineFormation in the database
        List<DomaineFormation> domaineFormationList = domaineFormationRepository.findAll();
        assertThat(domaineFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleDomaineIsRequired() throws Exception {
        int databaseSizeBeforeTest = domaineFormationRepository.findAll().size();
        // set the field null
        domaineFormation.setLibelleDomaine(null);

        // Create the DomaineFormation, which fails.


        restDomaineFormationMockMvc.perform(post("/api/domaine-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domaineFormation)))
            .andExpect(status().isBadRequest());

        List<DomaineFormation> domaineFormationList = domaineFormationRepository.findAll();
        assertThat(domaineFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDomaineFormations() throws Exception {
        // Initialize the database
        domaineFormationRepository.saveAndFlush(domaineFormation);

        // Get all the domaineFormationList
        restDomaineFormationMockMvc.perform(get("/api/domaine-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domaineFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleDomaine").value(hasItem(DEFAULT_LIBELLE_DOMAINE)));
    }
    
    @Test
    @Transactional
    public void getDomaineFormation() throws Exception {
        // Initialize the database
        domaineFormationRepository.saveAndFlush(domaineFormation);

        // Get the domaineFormation
        restDomaineFormationMockMvc.perform(get("/api/domaine-formations/{id}", domaineFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(domaineFormation.getId().intValue()))
            .andExpect(jsonPath("$.libelleDomaine").value(DEFAULT_LIBELLE_DOMAINE));
    }
    @Test
    @Transactional
    public void getNonExistingDomaineFormation() throws Exception {
        // Get the domaineFormation
        restDomaineFormationMockMvc.perform(get("/api/domaine-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDomaineFormation() throws Exception {
        // Initialize the database
        domaineFormationService.save(domaineFormation);

        int databaseSizeBeforeUpdate = domaineFormationRepository.findAll().size();

        // Update the domaineFormation
        DomaineFormation updatedDomaineFormation = domaineFormationRepository.findById(domaineFormation.getId()).get();
        // Disconnect from session so that the updates on updatedDomaineFormation are not directly saved in db
        em.detach(updatedDomaineFormation);
        updatedDomaineFormation
            .libelleDomaine(UPDATED_LIBELLE_DOMAINE);

        restDomaineFormationMockMvc.perform(put("/api/domaine-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDomaineFormation)))
            .andExpect(status().isOk());

        // Validate the DomaineFormation in the database
        List<DomaineFormation> domaineFormationList = domaineFormationRepository.findAll();
        assertThat(domaineFormationList).hasSize(databaseSizeBeforeUpdate);
        DomaineFormation testDomaineFormation = domaineFormationList.get(domaineFormationList.size() - 1);
        assertThat(testDomaineFormation.getLibelleDomaine()).isEqualTo(UPDATED_LIBELLE_DOMAINE);
    }

    @Test
    @Transactional
    public void updateNonExistingDomaineFormation() throws Exception {
        int databaseSizeBeforeUpdate = domaineFormationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomaineFormationMockMvc.perform(put("/api/domaine-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domaineFormation)))
            .andExpect(status().isBadRequest());

        // Validate the DomaineFormation in the database
        List<DomaineFormation> domaineFormationList = domaineFormationRepository.findAll();
        assertThat(domaineFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDomaineFormation() throws Exception {
        // Initialize the database
        domaineFormationService.save(domaineFormation);

        int databaseSizeBeforeDelete = domaineFormationRepository.findAll().size();

        // Delete the domaineFormation
        restDomaineFormationMockMvc.perform(delete("/api/domaine-formations/{id}", domaineFormation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DomaineFormation> domaineFormationList = domaineFormationRepository.findAll();
        assertThat(domaineFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
