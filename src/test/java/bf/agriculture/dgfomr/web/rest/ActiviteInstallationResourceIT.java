package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.ActiviteInstallation;
import bf.agriculture.dgfomr.repository.ActiviteInstallationRepository;
import bf.agriculture.dgfomr.service.ActiviteInstallationService;

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
 * Integration tests for the {@link ActiviteInstallationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ActiviteInstallationResourceIT {

    private static final String DEFAULT_LIBELLE_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_ACTIVITE = "BBBBBBBBBB";

    @Autowired
    private ActiviteInstallationRepository activiteInstallationRepository;

    @Autowired
    private ActiviteInstallationService activiteInstallationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restActiviteInstallationMockMvc;

    private ActiviteInstallation activiteInstallation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActiviteInstallation createEntity(EntityManager em) {
        ActiviteInstallation activiteInstallation = new ActiviteInstallation()
            .libelleActivite(DEFAULT_LIBELLE_ACTIVITE);
        return activiteInstallation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActiviteInstallation createUpdatedEntity(EntityManager em) {
        ActiviteInstallation activiteInstallation = new ActiviteInstallation()
            .libelleActivite(UPDATED_LIBELLE_ACTIVITE);
        return activiteInstallation;
    }

    @BeforeEach
    public void initTest() {
        activiteInstallation = createEntity(em);
    }

    @Test
    @Transactional
    public void createActiviteInstallation() throws Exception {
        int databaseSizeBeforeCreate = activiteInstallationRepository.findAll().size();
        // Create the ActiviteInstallation
        restActiviteInstallationMockMvc.perform(post("/api/activite-installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activiteInstallation)))
            .andExpect(status().isCreated());

        // Validate the ActiviteInstallation in the database
        List<ActiviteInstallation> activiteInstallationList = activiteInstallationRepository.findAll();
        assertThat(activiteInstallationList).hasSize(databaseSizeBeforeCreate + 1);
        ActiviteInstallation testActiviteInstallation = activiteInstallationList.get(activiteInstallationList.size() - 1);
        assertThat(testActiviteInstallation.getLibelleActivite()).isEqualTo(DEFAULT_LIBELLE_ACTIVITE);
    }

    @Test
    @Transactional
    public void createActiviteInstallationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activiteInstallationRepository.findAll().size();

        // Create the ActiviteInstallation with an existing ID
        activiteInstallation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActiviteInstallationMockMvc.perform(post("/api/activite-installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activiteInstallation)))
            .andExpect(status().isBadRequest());

        // Validate the ActiviteInstallation in the database
        List<ActiviteInstallation> activiteInstallationList = activiteInstallationRepository.findAll();
        assertThat(activiteInstallationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleActiviteIsRequired() throws Exception {
        int databaseSizeBeforeTest = activiteInstallationRepository.findAll().size();
        // set the field null
        activiteInstallation.setLibelleActivite(null);

        // Create the ActiviteInstallation, which fails.


        restActiviteInstallationMockMvc.perform(post("/api/activite-installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activiteInstallation)))
            .andExpect(status().isBadRequest());

        List<ActiviteInstallation> activiteInstallationList = activiteInstallationRepository.findAll();
        assertThat(activiteInstallationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActiviteInstallations() throws Exception {
        // Initialize the database
        activiteInstallationRepository.saveAndFlush(activiteInstallation);

        // Get all the activiteInstallationList
        restActiviteInstallationMockMvc.perform(get("/api/activite-installations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activiteInstallation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleActivite").value(hasItem(DEFAULT_LIBELLE_ACTIVITE)));
    }
    
    @Test
    @Transactional
    public void getActiviteInstallation() throws Exception {
        // Initialize the database
        activiteInstallationRepository.saveAndFlush(activiteInstallation);

        // Get the activiteInstallation
        restActiviteInstallationMockMvc.perform(get("/api/activite-installations/{id}", activiteInstallation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(activiteInstallation.getId().intValue()))
            .andExpect(jsonPath("$.libelleActivite").value(DEFAULT_LIBELLE_ACTIVITE));
    }
    @Test
    @Transactional
    public void getNonExistingActiviteInstallation() throws Exception {
        // Get the activiteInstallation
        restActiviteInstallationMockMvc.perform(get("/api/activite-installations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActiviteInstallation() throws Exception {
        // Initialize the database
        activiteInstallationService.save(activiteInstallation);

        int databaseSizeBeforeUpdate = activiteInstallationRepository.findAll().size();

        // Update the activiteInstallation
        ActiviteInstallation updatedActiviteInstallation = activiteInstallationRepository.findById(activiteInstallation.getId()).get();
        // Disconnect from session so that the updates on updatedActiviteInstallation are not directly saved in db
        em.detach(updatedActiviteInstallation);
        updatedActiviteInstallation
            .libelleActivite(UPDATED_LIBELLE_ACTIVITE);

        restActiviteInstallationMockMvc.perform(put("/api/activite-installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedActiviteInstallation)))
            .andExpect(status().isOk());

        // Validate the ActiviteInstallation in the database
        List<ActiviteInstallation> activiteInstallationList = activiteInstallationRepository.findAll();
        assertThat(activiteInstallationList).hasSize(databaseSizeBeforeUpdate);
        ActiviteInstallation testActiviteInstallation = activiteInstallationList.get(activiteInstallationList.size() - 1);
        assertThat(testActiviteInstallation.getLibelleActivite()).isEqualTo(UPDATED_LIBELLE_ACTIVITE);
    }

    @Test
    @Transactional
    public void updateNonExistingActiviteInstallation() throws Exception {
        int databaseSizeBeforeUpdate = activiteInstallationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActiviteInstallationMockMvc.perform(put("/api/activite-installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(activiteInstallation)))
            .andExpect(status().isBadRequest());

        // Validate the ActiviteInstallation in the database
        List<ActiviteInstallation> activiteInstallationList = activiteInstallationRepository.findAll();
        assertThat(activiteInstallationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActiviteInstallation() throws Exception {
        // Initialize the database
        activiteInstallationService.save(activiteInstallation);

        int databaseSizeBeforeDelete = activiteInstallationRepository.findAll().size();

        // Delete the activiteInstallation
        restActiviteInstallationMockMvc.perform(delete("/api/activite-installations/{id}", activiteInstallation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActiviteInstallation> activiteInstallationList = activiteInstallationRepository.findAll();
        assertThat(activiteInstallationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
