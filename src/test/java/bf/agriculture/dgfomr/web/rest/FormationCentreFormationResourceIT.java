package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.FormationCentreFormation;
import bf.agriculture.dgfomr.repository.FormationCentreFormationRepository;
import bf.agriculture.dgfomr.service.FormationCentreFormationService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FormationCentreFormationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormationCentreFormationResourceIT {

    private static final LocalDate DEFAULT_DATEDEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEDEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_CLORE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CLORE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FormationCentreFormationRepository formationCentreFormationRepository;

    @Autowired
    private FormationCentreFormationService formationCentreFormationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormationCentreFormationMockMvc;

    private FormationCentreFormation formationCentreFormation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormationCentreFormation createEntity(EntityManager em) {
        FormationCentreFormation formationCentreFormation = new FormationCentreFormation()
            .datedebut(DEFAULT_DATEDEBUT)
            .dateClore(DEFAULT_DATE_CLORE);
        return formationCentreFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormationCentreFormation createUpdatedEntity(EntityManager em) {
        FormationCentreFormation formationCentreFormation = new FormationCentreFormation()
            .datedebut(UPDATED_DATEDEBUT)
            .dateClore(UPDATED_DATE_CLORE);
        return formationCentreFormation;
    }

    @BeforeEach
    public void initTest() {
        formationCentreFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormationCentreFormation() throws Exception {
        int databaseSizeBeforeCreate = formationCentreFormationRepository.findAll().size();
        // Create the FormationCentreFormation
        restFormationCentreFormationMockMvc.perform(post("/api/formation-centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formationCentreFormation)))
            .andExpect(status().isCreated());

        // Validate the FormationCentreFormation in the database
        List<FormationCentreFormation> formationCentreFormationList = formationCentreFormationRepository.findAll();
        assertThat(formationCentreFormationList).hasSize(databaseSizeBeforeCreate + 1);
        FormationCentreFormation testFormationCentreFormation = formationCentreFormationList.get(formationCentreFormationList.size() - 1);
        assertThat(testFormationCentreFormation.getDatedebut()).isEqualTo(DEFAULT_DATEDEBUT);
        assertThat(testFormationCentreFormation.getDateClore()).isEqualTo(DEFAULT_DATE_CLORE);
    }

    @Test
    @Transactional
    public void createFormationCentreFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formationCentreFormationRepository.findAll().size();

        // Create the FormationCentreFormation with an existing ID
        formationCentreFormation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormationCentreFormationMockMvc.perform(post("/api/formation-centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formationCentreFormation)))
            .andExpect(status().isBadRequest());

        // Validate the FormationCentreFormation in the database
        List<FormationCentreFormation> formationCentreFormationList = formationCentreFormationRepository.findAll();
        assertThat(formationCentreFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormationCentreFormations() throws Exception {
        // Initialize the database
        formationCentreFormationRepository.saveAndFlush(formationCentreFormation);

        // Get all the formationCentreFormationList
        restFormationCentreFormationMockMvc.perform(get("/api/formation-centre-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formationCentreFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].datedebut").value(hasItem(DEFAULT_DATEDEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateClore").value(hasItem(DEFAULT_DATE_CLORE.toString())));
    }
    
    @Test
    @Transactional
    public void getFormationCentreFormation() throws Exception {
        // Initialize the database
        formationCentreFormationRepository.saveAndFlush(formationCentreFormation);

        // Get the formationCentreFormation
        restFormationCentreFormationMockMvc.perform(get("/api/formation-centre-formations/{id}", formationCentreFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formationCentreFormation.getId().intValue()))
            .andExpect(jsonPath("$.datedebut").value(DEFAULT_DATEDEBUT.toString()))
            .andExpect(jsonPath("$.dateClore").value(DEFAULT_DATE_CLORE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFormationCentreFormation() throws Exception {
        // Get the formationCentreFormation
        restFormationCentreFormationMockMvc.perform(get("/api/formation-centre-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormationCentreFormation() throws Exception {
        // Initialize the database
        formationCentreFormationService.save(formationCentreFormation);

        int databaseSizeBeforeUpdate = formationCentreFormationRepository.findAll().size();

        // Update the formationCentreFormation
        FormationCentreFormation updatedFormationCentreFormation = formationCentreFormationRepository.findById(formationCentreFormation.getId()).get();
        // Disconnect from session so that the updates on updatedFormationCentreFormation are not directly saved in db
        em.detach(updatedFormationCentreFormation);
        updatedFormationCentreFormation
            .datedebut(UPDATED_DATEDEBUT)
            .dateClore(UPDATED_DATE_CLORE);

        restFormationCentreFormationMockMvc.perform(put("/api/formation-centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormationCentreFormation)))
            .andExpect(status().isOk());

        // Validate the FormationCentreFormation in the database
        List<FormationCentreFormation> formationCentreFormationList = formationCentreFormationRepository.findAll();
        assertThat(formationCentreFormationList).hasSize(databaseSizeBeforeUpdate);
        FormationCentreFormation testFormationCentreFormation = formationCentreFormationList.get(formationCentreFormationList.size() - 1);
        assertThat(testFormationCentreFormation.getDatedebut()).isEqualTo(UPDATED_DATEDEBUT);
        assertThat(testFormationCentreFormation.getDateClore()).isEqualTo(UPDATED_DATE_CLORE);
    }

    @Test
    @Transactional
    public void updateNonExistingFormationCentreFormation() throws Exception {
        int databaseSizeBeforeUpdate = formationCentreFormationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormationCentreFormationMockMvc.perform(put("/api/formation-centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formationCentreFormation)))
            .andExpect(status().isBadRequest());

        // Validate the FormationCentreFormation in the database
        List<FormationCentreFormation> formationCentreFormationList = formationCentreFormationRepository.findAll();
        assertThat(formationCentreFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormationCentreFormation() throws Exception {
        // Initialize the database
        formationCentreFormationService.save(formationCentreFormation);

        int databaseSizeBeforeDelete = formationCentreFormationRepository.findAll().size();

        // Delete the formationCentreFormation
        restFormationCentreFormationMockMvc.perform(delete("/api/formation-centre-formations/{id}", formationCentreFormation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormationCentreFormation> formationCentreFormationList = formationCentreFormationRepository.findAll();
        assertThat(formationCentreFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
