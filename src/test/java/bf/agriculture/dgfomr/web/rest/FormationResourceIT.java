package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Formation;
import bf.agriculture.dgfomr.repository.FormationRepository;
import bf.agriculture.dgfomr.service.FormationService;

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
 * Integration tests for the {@link FormationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormationResourceIT {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Integer DEFAULT_NBR_AGENT_FORME = 1;
    private static final Integer UPDATED_NBR_AGENT_FORME = 2;

    private static final String DEFAULT_THEME_FORMATION = "AAAAAAAAAA";
    private static final String UPDATED_THEME_FORMATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DUREE_TOT_FORMATION = 1;
    private static final Integer UPDATED_DUREE_TOT_FORMATION = 2;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private FormationService formationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormationMockMvc;

    private Formation formation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formation createEntity(EntityManager em) {
        Formation formation = new Formation()
            .annee(DEFAULT_ANNEE)
            .nbrAgentForme(DEFAULT_NBR_AGENT_FORME)
            .themeFormation(DEFAULT_THEME_FORMATION)
            .dureeTotFormation(DEFAULT_DUREE_TOT_FORMATION);
        return formation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Formation createUpdatedEntity(EntityManager em) {
        Formation formation = new Formation()
            .annee(UPDATED_ANNEE)
            .nbrAgentForme(UPDATED_NBR_AGENT_FORME)
            .themeFormation(UPDATED_THEME_FORMATION)
            .dureeTotFormation(UPDATED_DUREE_TOT_FORMATION);
        return formation;
    }

    @BeforeEach
    public void initTest() {
        formation = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormation() throws Exception {
        int databaseSizeBeforeCreate = formationRepository.findAll().size();
        // Create the Formation
        restFormationMockMvc.perform(post("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formation)))
            .andExpect(status().isCreated());

        // Validate the Formation in the database
        List<Formation> formationList = formationRepository.findAll();
        assertThat(formationList).hasSize(databaseSizeBeforeCreate + 1);
        Formation testFormation = formationList.get(formationList.size() - 1);
        assertThat(testFormation.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testFormation.getNbrAgentForme()).isEqualTo(DEFAULT_NBR_AGENT_FORME);
        assertThat(testFormation.getThemeFormation()).isEqualTo(DEFAULT_THEME_FORMATION);
        assertThat(testFormation.getDureeTotFormation()).isEqualTo(DEFAULT_DUREE_TOT_FORMATION);
    }

    @Test
    @Transactional
    public void createFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formationRepository.findAll().size();

        // Create the Formation with an existing ID
        formation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormationMockMvc.perform(post("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formation)))
            .andExpect(status().isBadRequest());

        // Validate the Formation in the database
        List<Formation> formationList = formationRepository.findAll();
        assertThat(formationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormations() throws Exception {
        // Initialize the database
        formationRepository.saveAndFlush(formation);

        // Get all the formationList
        restFormationMockMvc.perform(get("/api/formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formation.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].nbrAgentForme").value(hasItem(DEFAULT_NBR_AGENT_FORME)))
            .andExpect(jsonPath("$.[*].themeFormation").value(hasItem(DEFAULT_THEME_FORMATION)))
            .andExpect(jsonPath("$.[*].dureeTotFormation").value(hasItem(DEFAULT_DUREE_TOT_FORMATION)));
    }
    
    @Test
    @Transactional
    public void getFormation() throws Exception {
        // Initialize the database
        formationRepository.saveAndFlush(formation);

        // Get the formation
        restFormationMockMvc.perform(get("/api/formations/{id}", formation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formation.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.nbrAgentForme").value(DEFAULT_NBR_AGENT_FORME))
            .andExpect(jsonPath("$.themeFormation").value(DEFAULT_THEME_FORMATION))
            .andExpect(jsonPath("$.dureeTotFormation").value(DEFAULT_DUREE_TOT_FORMATION));
    }
    @Test
    @Transactional
    public void getNonExistingFormation() throws Exception {
        // Get the formation
        restFormationMockMvc.perform(get("/api/formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormation() throws Exception {
        // Initialize the database
        formationService.save(formation);

        int databaseSizeBeforeUpdate = formationRepository.findAll().size();

        // Update the formation
        Formation updatedFormation = formationRepository.findById(formation.getId()).get();
        // Disconnect from session so that the updates on updatedFormation are not directly saved in db
        em.detach(updatedFormation);
        updatedFormation
            .annee(UPDATED_ANNEE)
            .nbrAgentForme(UPDATED_NBR_AGENT_FORME)
            .themeFormation(UPDATED_THEME_FORMATION)
            .dureeTotFormation(UPDATED_DUREE_TOT_FORMATION);

        restFormationMockMvc.perform(put("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormation)))
            .andExpect(status().isOk());

        // Validate the Formation in the database
        List<Formation> formationList = formationRepository.findAll();
        assertThat(formationList).hasSize(databaseSizeBeforeUpdate);
        Formation testFormation = formationList.get(formationList.size() - 1);
        assertThat(testFormation.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testFormation.getNbrAgentForme()).isEqualTo(UPDATED_NBR_AGENT_FORME);
        assertThat(testFormation.getThemeFormation()).isEqualTo(UPDATED_THEME_FORMATION);
        assertThat(testFormation.getDureeTotFormation()).isEqualTo(UPDATED_DUREE_TOT_FORMATION);
    }

    @Test
    @Transactional
    public void updateNonExistingFormation() throws Exception {
        int databaseSizeBeforeUpdate = formationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormationMockMvc.perform(put("/api/formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formation)))
            .andExpect(status().isBadRequest());

        // Validate the Formation in the database
        List<Formation> formationList = formationRepository.findAll();
        assertThat(formationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormation() throws Exception {
        // Initialize the database
        formationService.save(formation);

        int databaseSizeBeforeDelete = formationRepository.findAll().size();

        // Delete the formation
        restFormationMockMvc.perform(delete("/api/formations/{id}", formation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Formation> formationList = formationRepository.findAll();
        assertThat(formationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
