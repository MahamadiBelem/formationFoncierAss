package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Formations;
import bf.agriculture.dgfomr.repository.FormationsRepository;
import bf.agriculture.dgfomr.service.FormationsService;

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
 * Integration tests for the {@link FormationsResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormationsResourceIT {

    private static final String DEFAULT_THEME = "AAAAAAAAAA";
    private static final String UPDATED_THEME = "BBBBBBBBBB";

    private static final String DEFAULT_LEBELLE_FORMATION = "AAAAAAAAAA";
    private static final String UPDATED_LEBELLE_FORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_COUT_FORMATION = "AAAAAAAAAA";
    private static final String UPDATED_COUT_FORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_FINANCEMENT = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_FINANCEMENT = "BBBBBBBBBB";

    @Autowired
    private FormationsRepository formationsRepository;

    @Autowired
    private FormationsService formationsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormationsMockMvc;

    private Formations formations;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formations createEntity(EntityManager em) {
        Formations formations = new Formations()
            .theme(DEFAULT_THEME)
            .lebelleFormation(DEFAULT_LEBELLE_FORMATION)
            .coutFormation(DEFAULT_COUT_FORMATION)
            .sourceFinancement(DEFAULT_SOURCE_FINANCEMENT);
        return formations;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formations createUpdatedEntity(EntityManager em) {
        Formations formations = new Formations()
            .theme(UPDATED_THEME)
            .lebelleFormation(UPDATED_LEBELLE_FORMATION)
            .coutFormation(UPDATED_COUT_FORMATION)
            .sourceFinancement(UPDATED_SOURCE_FINANCEMENT);
        return formations;
    }

    @BeforeEach
    public void initTest() {
        formations = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormations() throws Exception {
        int databaseSizeBeforeCreate = formationsRepository.findAll().size();
        // Create the Formations
        restFormationsMockMvc.perform(post("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formations)))
            .andExpect(status().isCreated());

        // Validate the Formations in the database
        List<Formations> formationsList = formationsRepository.findAll();
        assertThat(formationsList).hasSize(databaseSizeBeforeCreate + 1);
        Formations testFormations = formationsList.get(formationsList.size() - 1);
        assertThat(testFormations.getTheme()).isEqualTo(DEFAULT_THEME);
        assertThat(testFormations.getLebelleFormation()).isEqualTo(DEFAULT_LEBELLE_FORMATION);
        assertThat(testFormations.getCoutFormation()).isEqualTo(DEFAULT_COUT_FORMATION);
        assertThat(testFormations.getSourceFinancement()).isEqualTo(DEFAULT_SOURCE_FINANCEMENT);
    }

    @Test
    @Transactional
    public void createFormationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formationsRepository.findAll().size();

        // Create the Formations with an existing ID
        formations.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormationsMockMvc.perform(post("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formations)))
            .andExpect(status().isBadRequest());

        // Validate the Formations in the database
        List<Formations> formationsList = formationsRepository.findAll();
        assertThat(formationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormations() throws Exception {
        // Initialize the database
        formationsRepository.saveAndFlush(formations);

        // Get all the formationsList
        restFormationsMockMvc.perform(get("/api/formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formations.getId().intValue())))
            .andExpect(jsonPath("$.[*].theme").value(hasItem(DEFAULT_THEME)))
            .andExpect(jsonPath("$.[*].lebelleFormation").value(hasItem(DEFAULT_LEBELLE_FORMATION)))
            .andExpect(jsonPath("$.[*].coutFormation").value(hasItem(DEFAULT_COUT_FORMATION)))
            .andExpect(jsonPath("$.[*].sourceFinancement").value(hasItem(DEFAULT_SOURCE_FINANCEMENT)));
    }
    
    @Test
    @Transactional
    public void getFormations() throws Exception {
        // Initialize the database
        formationsRepository.saveAndFlush(formations);

        // Get the formations
        restFormationsMockMvc.perform(get("/api/formations/{id}", formations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formations.getId().intValue()))
            .andExpect(jsonPath("$.theme").value(DEFAULT_THEME))
            .andExpect(jsonPath("$.lebelleFormation").value(DEFAULT_LEBELLE_FORMATION))
            .andExpect(jsonPath("$.coutFormation").value(DEFAULT_COUT_FORMATION))
            .andExpect(jsonPath("$.sourceFinancement").value(DEFAULT_SOURCE_FINANCEMENT));
    }
    @Test
    @Transactional
    public void getNonExistingFormations() throws Exception {
        // Get the formations
        restFormationsMockMvc.perform(get("/api/formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormations() throws Exception {
        // Initialize the database
        formationsService.save(formations);

        int databaseSizeBeforeUpdate = formationsRepository.findAll().size();

        // Update the formations
        Formations updatedFormations = formationsRepository.findById(formations.getId()).get();
        // Disconnect from session so that the updates on updatedFormations are not directly saved in db
        em.detach(updatedFormations);
        updatedFormations
            .theme(UPDATED_THEME)
            .lebelleFormation(UPDATED_LEBELLE_FORMATION)
            .coutFormation(UPDATED_COUT_FORMATION)
            .sourceFinancement(UPDATED_SOURCE_FINANCEMENT);

        restFormationsMockMvc.perform(put("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormations)))
            .andExpect(status().isOk());

        // Validate the Formations in the database
        List<Formations> formationsList = formationsRepository.findAll();
        assertThat(formationsList).hasSize(databaseSizeBeforeUpdate);
        Formations testFormations = formationsList.get(formationsList.size() - 1);
        assertThat(testFormations.getTheme()).isEqualTo(UPDATED_THEME);
        assertThat(testFormations.getLebelleFormation()).isEqualTo(UPDATED_LEBELLE_FORMATION);
        assertThat(testFormations.getCoutFormation()).isEqualTo(UPDATED_COUT_FORMATION);
        assertThat(testFormations.getSourceFinancement()).isEqualTo(UPDATED_SOURCE_FINANCEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingFormations() throws Exception {
        int databaseSizeBeforeUpdate = formationsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormationsMockMvc.perform(put("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formations)))
            .andExpect(status().isBadRequest());

        // Validate the Formations in the database
        List<Formations> formationsList = formationsRepository.findAll();
        assertThat(formationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormations() throws Exception {
        // Initialize the database
        formationsService.save(formations);

        int databaseSizeBeforeDelete = formationsRepository.findAll().size();

        // Delete the formations
        restFormationsMockMvc.perform(delete("/api/formations/{id}", formations.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formations> formationsList = formationsRepository.findAll();
        assertThat(formationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
