package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.FormationSFR;
import bf.agriculture.dgfomr.repository.FormationSFRRepository;
import bf.agriculture.dgfomr.service.FormationSFRService;

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
 * Integration tests for the {@link FormationSFRResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormationSFRResourceIT {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Integer DEFAULT_NBR_AGENT_FORME = 1;
    private static final Integer UPDATED_NBR_AGENT_FORME = 2;

    private static final String DEFAULT_THEME_FORMATION = "AAAAAAAAAA";
    private static final String UPDATED_THEME_FORMATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DUREE_TOT_FORMATION = 1;
    private static final Integer UPDATED_DUREE_TOT_FORMATION = 2;

    @Autowired
    private FormationSFRRepository formationSFRRepository;

    @Autowired
    private FormationSFRService formationSFRService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormationSFRMockMvc;

    private FormationSFR formationSFR;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormationSFR createEntity(EntityManager em) {
        FormationSFR formationSFR = new FormationSFR()
            .annee(DEFAULT_ANNEE)
            .nbrAgentForme(DEFAULT_NBR_AGENT_FORME)
            .themeFormation(DEFAULT_THEME_FORMATION)
            .dureeTotFormation(DEFAULT_DUREE_TOT_FORMATION);
        return formationSFR;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormationSFR createUpdatedEntity(EntityManager em) {
        FormationSFR formationSFR = new FormationSFR()
            .annee(UPDATED_ANNEE)
            .nbrAgentForme(UPDATED_NBR_AGENT_FORME)
            .themeFormation(UPDATED_THEME_FORMATION)
            .dureeTotFormation(UPDATED_DUREE_TOT_FORMATION);
        return formationSFR;
    }

    @BeforeEach
    public void initTest() {
        formationSFR = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormationSFR() throws Exception {
        int databaseSizeBeforeCreate = formationSFRRepository.findAll().size();
        // Create the FormationSFR
        restFormationSFRMockMvc.perform(post("/api/formation-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formationSFR)))
            .andExpect(status().isCreated());

        // Validate the FormationSFR in the database
        List<FormationSFR> formationSFRList = formationSFRRepository.findAll();
        assertThat(formationSFRList).hasSize(databaseSizeBeforeCreate + 1);
        FormationSFR testFormationSFR = formationSFRList.get(formationSFRList.size() - 1);
        assertThat(testFormationSFR.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testFormationSFR.getNbrAgentForme()).isEqualTo(DEFAULT_NBR_AGENT_FORME);
        assertThat(testFormationSFR.getThemeFormation()).isEqualTo(DEFAULT_THEME_FORMATION);
        assertThat(testFormationSFR.getDureeTotFormation()).isEqualTo(DEFAULT_DUREE_TOT_FORMATION);
    }

    @Test
    @Transactional
    public void createFormationSFRWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formationSFRRepository.findAll().size();

        // Create the FormationSFR with an existing ID
        formationSFR.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormationSFRMockMvc.perform(post("/api/formation-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formationSFR)))
            .andExpect(status().isBadRequest());

        // Validate the FormationSFR in the database
        List<FormationSFR> formationSFRList = formationSFRRepository.findAll();
        assertThat(formationSFRList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormationSFRS() throws Exception {
        // Initialize the database
        formationSFRRepository.saveAndFlush(formationSFR);

        // Get all the formationSFRList
        restFormationSFRMockMvc.perform(get("/api/formation-sfrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formationSFR.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].nbrAgentForme").value(hasItem(DEFAULT_NBR_AGENT_FORME)))
            .andExpect(jsonPath("$.[*].themeFormation").value(hasItem(DEFAULT_THEME_FORMATION)))
            .andExpect(jsonPath("$.[*].dureeTotFormation").value(hasItem(DEFAULT_DUREE_TOT_FORMATION)));
    }
    
    @Test
    @Transactional
    public void getFormationSFR() throws Exception {
        // Initialize the database
        formationSFRRepository.saveAndFlush(formationSFR);

        // Get the formationSFR
        restFormationSFRMockMvc.perform(get("/api/formation-sfrs/{id}", formationSFR.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formationSFR.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.nbrAgentForme").value(DEFAULT_NBR_AGENT_FORME))
            .andExpect(jsonPath("$.themeFormation").value(DEFAULT_THEME_FORMATION))
            .andExpect(jsonPath("$.dureeTotFormation").value(DEFAULT_DUREE_TOT_FORMATION));
    }
    @Test
    @Transactional
    public void getNonExistingFormationSFR() throws Exception {
        // Get the formationSFR
        restFormationSFRMockMvc.perform(get("/api/formation-sfrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormationSFR() throws Exception {
        // Initialize the database
        formationSFRService.save(formationSFR);

        int databaseSizeBeforeUpdate = formationSFRRepository.findAll().size();

        // Update the formationSFR
        FormationSFR updatedFormationSFR = formationSFRRepository.findById(formationSFR.getId()).get();
        // Disconnect from session so that the updates on updatedFormationSFR are not directly saved in db
        em.detach(updatedFormationSFR);
        updatedFormationSFR
            .annee(UPDATED_ANNEE)
            .nbrAgentForme(UPDATED_NBR_AGENT_FORME)
            .themeFormation(UPDATED_THEME_FORMATION)
            .dureeTotFormation(UPDATED_DUREE_TOT_FORMATION);

        restFormationSFRMockMvc.perform(put("/api/formation-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormationSFR)))
            .andExpect(status().isOk());

        // Validate the FormationSFR in the database
        List<FormationSFR> formationSFRList = formationSFRRepository.findAll();
        assertThat(formationSFRList).hasSize(databaseSizeBeforeUpdate);
        FormationSFR testFormationSFR = formationSFRList.get(formationSFRList.size() - 1);
        assertThat(testFormationSFR.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testFormationSFR.getNbrAgentForme()).isEqualTo(UPDATED_NBR_AGENT_FORME);
        assertThat(testFormationSFR.getThemeFormation()).isEqualTo(UPDATED_THEME_FORMATION);
        assertThat(testFormationSFR.getDureeTotFormation()).isEqualTo(UPDATED_DUREE_TOT_FORMATION);
    }

    @Test
    @Transactional
    public void updateNonExistingFormationSFR() throws Exception {
        int databaseSizeBeforeUpdate = formationSFRRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormationSFRMockMvc.perform(put("/api/formation-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formationSFR)))
            .andExpect(status().isBadRequest());

        // Validate the FormationSFR in the database
        List<FormationSFR> formationSFRList = formationSFRRepository.findAll();
        assertThat(formationSFRList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormationSFR() throws Exception {
        // Initialize the database
        formationSFRService.save(formationSFR);

        int databaseSizeBeforeDelete = formationSFRRepository.findAll().size();

        // Delete the formationSFR
        restFormationSFRMockMvc.perform(delete("/api/formation-sfrs/{id}", formationSFR.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormationSFR> formationSFRList = formationSFRRepository.findAll();
        assertThat(formationSFRList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
