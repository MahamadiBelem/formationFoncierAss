package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Formateurs;
import bf.agriculture.dgfomr.repository.FormateursRepository;
import bf.agriculture.dgfomr.service.FormateursService;

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
 * Integration tests for the {@link FormateursResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormateursResourceIT {

    private static final String DEFAULT_NOM_COMPLET = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COMPLET = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALITE = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALITE = "BBBBBBBBBB";

    private static final String DEFAULT_REGIME = "AAAAAAAAAA";
    private static final String UPDATED_REGIME = "BBBBBBBBBB";

    @Autowired
    private FormateursRepository formateursRepository;

    @Autowired
    private FormateursService formateursService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormateursMockMvc;

    private Formateurs formateurs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formateurs createEntity(EntityManager em) {
        Formateurs formateurs = new Formateurs()
            .nomComplet(DEFAULT_NOM_COMPLET)
            .specialite(DEFAULT_SPECIALITE)
            .regime(DEFAULT_REGIME);
        return formateurs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formateurs createUpdatedEntity(EntityManager em) {
        Formateurs formateurs = new Formateurs()
            .nomComplet(UPDATED_NOM_COMPLET)
            .specialite(UPDATED_SPECIALITE)
            .regime(UPDATED_REGIME);
        return formateurs;
    }

    @BeforeEach
    public void initTest() {
        formateurs = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormateurs() throws Exception {
        int databaseSizeBeforeCreate = formateursRepository.findAll().size();
        // Create the Formateurs
        restFormateursMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurs)))
            .andExpect(status().isCreated());

        // Validate the Formateurs in the database
        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeCreate + 1);
        Formateurs testFormateurs = formateursList.get(formateursList.size() - 1);
        assertThat(testFormateurs.getNomComplet()).isEqualTo(DEFAULT_NOM_COMPLET);
        assertThat(testFormateurs.getSpecialite()).isEqualTo(DEFAULT_SPECIALITE);
        assertThat(testFormateurs.getRegime()).isEqualTo(DEFAULT_REGIME);
    }

    @Test
    @Transactional
    public void createFormateursWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formateursRepository.findAll().size();

        // Create the Formateurs with an existing ID
        formateurs.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormateursMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurs)))
            .andExpect(status().isBadRequest());

        // Validate the Formateurs in the database
        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomCompletIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateursRepository.findAll().size();
        // set the field null
        formateurs.setNomComplet(null);

        // Create the Formateurs, which fails.


        restFormateursMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurs)))
            .andExpect(status().isBadRequest());

        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecialiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateursRepository.findAll().size();
        // set the field null
        formateurs.setSpecialite(null);

        // Create the Formateurs, which fails.


        restFormateursMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurs)))
            .andExpect(status().isBadRequest());

        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRegimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateursRepository.findAll().size();
        // set the field null
        formateurs.setRegime(null);

        // Create the Formateurs, which fails.


        restFormateursMockMvc.perform(post("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurs)))
            .andExpect(status().isBadRequest());

        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFormateurs() throws Exception {
        // Initialize the database
        formateursRepository.saveAndFlush(formateurs);

        // Get all the formateursList
        restFormateursMockMvc.perform(get("/api/formateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formateurs.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomComplet").value(hasItem(DEFAULT_NOM_COMPLET)))
            .andExpect(jsonPath("$.[*].specialite").value(hasItem(DEFAULT_SPECIALITE)))
            .andExpect(jsonPath("$.[*].regime").value(hasItem(DEFAULT_REGIME)));
    }
    
    @Test
    @Transactional
    public void getFormateurs() throws Exception {
        // Initialize the database
        formateursRepository.saveAndFlush(formateurs);

        // Get the formateurs
        restFormateursMockMvc.perform(get("/api/formateurs/{id}", formateurs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formateurs.getId().intValue()))
            .andExpect(jsonPath("$.nomComplet").value(DEFAULT_NOM_COMPLET))
            .andExpect(jsonPath("$.specialite").value(DEFAULT_SPECIALITE))
            .andExpect(jsonPath("$.regime").value(DEFAULT_REGIME));
    }
    @Test
    @Transactional
    public void getNonExistingFormateurs() throws Exception {
        // Get the formateurs
        restFormateursMockMvc.perform(get("/api/formateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormateurs() throws Exception {
        // Initialize the database
        formateursService.save(formateurs);

        int databaseSizeBeforeUpdate = formateursRepository.findAll().size();

        // Update the formateurs
        Formateurs updatedFormateurs = formateursRepository.findById(formateurs.getId()).get();
        // Disconnect from session so that the updates on updatedFormateurs are not directly saved in db
        em.detach(updatedFormateurs);
        updatedFormateurs
            .nomComplet(UPDATED_NOM_COMPLET)
            .specialite(UPDATED_SPECIALITE)
            .regime(UPDATED_REGIME);

        restFormateursMockMvc.perform(put("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormateurs)))
            .andExpect(status().isOk());

        // Validate the Formateurs in the database
        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeUpdate);
        Formateurs testFormateurs = formateursList.get(formateursList.size() - 1);
        assertThat(testFormateurs.getNomComplet()).isEqualTo(UPDATED_NOM_COMPLET);
        assertThat(testFormateurs.getSpecialite()).isEqualTo(UPDATED_SPECIALITE);
        assertThat(testFormateurs.getRegime()).isEqualTo(UPDATED_REGIME);
    }

    @Test
    @Transactional
    public void updateNonExistingFormateurs() throws Exception {
        int databaseSizeBeforeUpdate = formateursRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormateursMockMvc.perform(put("/api/formateurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurs)))
            .andExpect(status().isBadRequest());

        // Validate the Formateurs in the database
        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormateurs() throws Exception {
        // Initialize the database
        formateursService.save(formateurs);

        int databaseSizeBeforeDelete = formateursRepository.findAll().size();

        // Delete the formateurs
        restFormateursMockMvc.perform(delete("/api/formateurs/{id}", formateurs.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formateurs> formateursList = formateursRepository.findAll();
        assertThat(formateursList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
