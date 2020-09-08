package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Infrastructure;
import bf.agriculture.dgfomr.repository.InfrastructureRepository;
import bf.agriculture.dgfomr.service.InfrastructureService;

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
 * Integration tests for the {@link InfrastructureResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfrastructureResourceIT {

    private static final LocalDate DEFAULT_DATE_ELABORATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ELABORATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FONCTIONNALITE = "AAAAAAAAAA";
    private static final String UPDATED_FONCTIONNALITE = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT = "AAAAAAAAAA";
    private static final String UPDATED_ETAT = "BBBBBBBBBB";

    @Autowired
    private InfrastructureRepository infrastructureRepository;

    @Autowired
    private InfrastructureService infrastructureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfrastructureMockMvc;

    private Infrastructure infrastructure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infrastructure createEntity(EntityManager em) {
        Infrastructure infrastructure = new Infrastructure()
            .dateElaboration(DEFAULT_DATE_ELABORATION)
            .fonctionnalite(DEFAULT_FONCTIONNALITE)
            .etat(DEFAULT_ETAT);
        return infrastructure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Infrastructure createUpdatedEntity(EntityManager em) {
        Infrastructure infrastructure = new Infrastructure()
            .dateElaboration(UPDATED_DATE_ELABORATION)
            .fonctionnalite(UPDATED_FONCTIONNALITE)
            .etat(UPDATED_ETAT);
        return infrastructure;
    }

    @BeforeEach
    public void initTest() {
        infrastructure = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfrastructure() throws Exception {
        int databaseSizeBeforeCreate = infrastructureRepository.findAll().size();
        // Create the Infrastructure
        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructure)))
            .andExpect(status().isCreated());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeCreate + 1);
        Infrastructure testInfrastructure = infrastructureList.get(infrastructureList.size() - 1);
        assertThat(testInfrastructure.getDateElaboration()).isEqualTo(DEFAULT_DATE_ELABORATION);
        assertThat(testInfrastructure.getFonctionnalite()).isEqualTo(DEFAULT_FONCTIONNALITE);
        assertThat(testInfrastructure.getEtat()).isEqualTo(DEFAULT_ETAT);
    }

    @Test
    @Transactional
    public void createInfrastructureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infrastructureRepository.findAll().size();

        // Create the Infrastructure with an existing ID
        infrastructure.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructure)))
            .andExpect(status().isBadRequest());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateElaborationIsRequired() throws Exception {
        int databaseSizeBeforeTest = infrastructureRepository.findAll().size();
        // set the field null
        infrastructure.setDateElaboration(null);

        // Create the Infrastructure, which fails.


        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructure)))
            .andExpect(status().isBadRequest());

        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFonctionnaliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = infrastructureRepository.findAll().size();
        // set the field null
        infrastructure.setFonctionnalite(null);

        // Create the Infrastructure, which fails.


        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructure)))
            .andExpect(status().isBadRequest());

        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEtatIsRequired() throws Exception {
        int databaseSizeBeforeTest = infrastructureRepository.findAll().size();
        // set the field null
        infrastructure.setEtat(null);

        // Create the Infrastructure, which fails.


        restInfrastructureMockMvc.perform(post("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructure)))
            .andExpect(status().isBadRequest());

        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInfrastructures() throws Exception {
        // Initialize the database
        infrastructureRepository.saveAndFlush(infrastructure);

        // Get all the infrastructureList
        restInfrastructureMockMvc.perform(get("/api/infrastructures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(infrastructure.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateElaboration").value(hasItem(DEFAULT_DATE_ELABORATION.toString())))
            .andExpect(jsonPath("$.[*].fonctionnalite").value(hasItem(DEFAULT_FONCTIONNALITE)))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT)));
    }
    
    @Test
    @Transactional
    public void getInfrastructure() throws Exception {
        // Initialize the database
        infrastructureRepository.saveAndFlush(infrastructure);

        // Get the infrastructure
        restInfrastructureMockMvc.perform(get("/api/infrastructures/{id}", infrastructure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(infrastructure.getId().intValue()))
            .andExpect(jsonPath("$.dateElaboration").value(DEFAULT_DATE_ELABORATION.toString()))
            .andExpect(jsonPath("$.fonctionnalite").value(DEFAULT_FONCTIONNALITE))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT));
    }
    @Test
    @Transactional
    public void getNonExistingInfrastructure() throws Exception {
        // Get the infrastructure
        restInfrastructureMockMvc.perform(get("/api/infrastructures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfrastructure() throws Exception {
        // Initialize the database
        infrastructureService.save(infrastructure);

        int databaseSizeBeforeUpdate = infrastructureRepository.findAll().size();

        // Update the infrastructure
        Infrastructure updatedInfrastructure = infrastructureRepository.findById(infrastructure.getId()).get();
        // Disconnect from session so that the updates on updatedInfrastructure are not directly saved in db
        em.detach(updatedInfrastructure);
        updatedInfrastructure
            .dateElaboration(UPDATED_DATE_ELABORATION)
            .fonctionnalite(UPDATED_FONCTIONNALITE)
            .etat(UPDATED_ETAT);

        restInfrastructureMockMvc.perform(put("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInfrastructure)))
            .andExpect(status().isOk());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeUpdate);
        Infrastructure testInfrastructure = infrastructureList.get(infrastructureList.size() - 1);
        assertThat(testInfrastructure.getDateElaboration()).isEqualTo(UPDATED_DATE_ELABORATION);
        assertThat(testInfrastructure.getFonctionnalite()).isEqualTo(UPDATED_FONCTIONNALITE);
        assertThat(testInfrastructure.getEtat()).isEqualTo(UPDATED_ETAT);
    }

    @Test
    @Transactional
    public void updateNonExistingInfrastructure() throws Exception {
        int databaseSizeBeforeUpdate = infrastructureRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfrastructureMockMvc.perform(put("/api/infrastructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(infrastructure)))
            .andExpect(status().isBadRequest());

        // Validate the Infrastructure in the database
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfrastructure() throws Exception {
        // Initialize the database
        infrastructureService.save(infrastructure);

        int databaseSizeBeforeDelete = infrastructureRepository.findAll().size();

        // Delete the infrastructure
        restInfrastructureMockMvc.perform(delete("/api/infrastructures/{id}", infrastructure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Infrastructure> infrastructureList = infrastructureRepository.findAll();
        assertThat(infrastructureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
