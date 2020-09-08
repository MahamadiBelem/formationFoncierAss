package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.NiveauRecrutement;
import bf.agriculture.dgfomr.repository.NiveauRecrutementRepository;
import bf.agriculture.dgfomr.service.NiveauRecrutementService;

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
 * Integration tests for the {@link NiveauRecrutementResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NiveauRecrutementResourceIT {

    private static final String DEFAULT_LIBELLE_NIVEAU = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_NIVEAU = "BBBBBBBBBB";

    @Autowired
    private NiveauRecrutementRepository niveauRecrutementRepository;

    @Autowired
    private NiveauRecrutementService niveauRecrutementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNiveauRecrutementMockMvc;

    private NiveauRecrutement niveauRecrutement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NiveauRecrutement createEntity(EntityManager em) {
        NiveauRecrutement niveauRecrutement = new NiveauRecrutement()
            .libelleNiveau(DEFAULT_LIBELLE_NIVEAU);
        return niveauRecrutement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NiveauRecrutement createUpdatedEntity(EntityManager em) {
        NiveauRecrutement niveauRecrutement = new NiveauRecrutement()
            .libelleNiveau(UPDATED_LIBELLE_NIVEAU);
        return niveauRecrutement;
    }

    @BeforeEach
    public void initTest() {
        niveauRecrutement = createEntity(em);
    }

    @Test
    @Transactional
    public void createNiveauRecrutement() throws Exception {
        int databaseSizeBeforeCreate = niveauRecrutementRepository.findAll().size();
        // Create the NiveauRecrutement
        restNiveauRecrutementMockMvc.perform(post("/api/niveau-recrutements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauRecrutement)))
            .andExpect(status().isCreated());

        // Validate the NiveauRecrutement in the database
        List<NiveauRecrutement> niveauRecrutementList = niveauRecrutementRepository.findAll();
        assertThat(niveauRecrutementList).hasSize(databaseSizeBeforeCreate + 1);
        NiveauRecrutement testNiveauRecrutement = niveauRecrutementList.get(niveauRecrutementList.size() - 1);
        assertThat(testNiveauRecrutement.getLibelleNiveau()).isEqualTo(DEFAULT_LIBELLE_NIVEAU);
    }

    @Test
    @Transactional
    public void createNiveauRecrutementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = niveauRecrutementRepository.findAll().size();

        // Create the NiveauRecrutement with an existing ID
        niveauRecrutement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNiveauRecrutementMockMvc.perform(post("/api/niveau-recrutements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauRecrutement)))
            .andExpect(status().isBadRequest());

        // Validate the NiveauRecrutement in the database
        List<NiveauRecrutement> niveauRecrutementList = niveauRecrutementRepository.findAll();
        assertThat(niveauRecrutementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleNiveauIsRequired() throws Exception {
        int databaseSizeBeforeTest = niveauRecrutementRepository.findAll().size();
        // set the field null
        niveauRecrutement.setLibelleNiveau(null);

        // Create the NiveauRecrutement, which fails.


        restNiveauRecrutementMockMvc.perform(post("/api/niveau-recrutements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauRecrutement)))
            .andExpect(status().isBadRequest());

        List<NiveauRecrutement> niveauRecrutementList = niveauRecrutementRepository.findAll();
        assertThat(niveauRecrutementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNiveauRecrutements() throws Exception {
        // Initialize the database
        niveauRecrutementRepository.saveAndFlush(niveauRecrutement);

        // Get all the niveauRecrutementList
        restNiveauRecrutementMockMvc.perform(get("/api/niveau-recrutements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(niveauRecrutement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleNiveau").value(hasItem(DEFAULT_LIBELLE_NIVEAU)));
    }
    
    @Test
    @Transactional
    public void getNiveauRecrutement() throws Exception {
        // Initialize the database
        niveauRecrutementRepository.saveAndFlush(niveauRecrutement);

        // Get the niveauRecrutement
        restNiveauRecrutementMockMvc.perform(get("/api/niveau-recrutements/{id}", niveauRecrutement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(niveauRecrutement.getId().intValue()))
            .andExpect(jsonPath("$.libelleNiveau").value(DEFAULT_LIBELLE_NIVEAU));
    }
    @Test
    @Transactional
    public void getNonExistingNiveauRecrutement() throws Exception {
        // Get the niveauRecrutement
        restNiveauRecrutementMockMvc.perform(get("/api/niveau-recrutements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNiveauRecrutement() throws Exception {
        // Initialize the database
        niveauRecrutementService.save(niveauRecrutement);

        int databaseSizeBeforeUpdate = niveauRecrutementRepository.findAll().size();

        // Update the niveauRecrutement
        NiveauRecrutement updatedNiveauRecrutement = niveauRecrutementRepository.findById(niveauRecrutement.getId()).get();
        // Disconnect from session so that the updates on updatedNiveauRecrutement are not directly saved in db
        em.detach(updatedNiveauRecrutement);
        updatedNiveauRecrutement
            .libelleNiveau(UPDATED_LIBELLE_NIVEAU);

        restNiveauRecrutementMockMvc.perform(put("/api/niveau-recrutements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNiveauRecrutement)))
            .andExpect(status().isOk());

        // Validate the NiveauRecrutement in the database
        List<NiveauRecrutement> niveauRecrutementList = niveauRecrutementRepository.findAll();
        assertThat(niveauRecrutementList).hasSize(databaseSizeBeforeUpdate);
        NiveauRecrutement testNiveauRecrutement = niveauRecrutementList.get(niveauRecrutementList.size() - 1);
        assertThat(testNiveauRecrutement.getLibelleNiveau()).isEqualTo(UPDATED_LIBELLE_NIVEAU);
    }

    @Test
    @Transactional
    public void updateNonExistingNiveauRecrutement() throws Exception {
        int databaseSizeBeforeUpdate = niveauRecrutementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNiveauRecrutementMockMvc.perform(put("/api/niveau-recrutements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauRecrutement)))
            .andExpect(status().isBadRequest());

        // Validate the NiveauRecrutement in the database
        List<NiveauRecrutement> niveauRecrutementList = niveauRecrutementRepository.findAll();
        assertThat(niveauRecrutementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNiveauRecrutement() throws Exception {
        // Initialize the database
        niveauRecrutementService.save(niveauRecrutement);

        int databaseSizeBeforeDelete = niveauRecrutementRepository.findAll().size();

        // Delete the niveauRecrutement
        restNiveauRecrutementMockMvc.perform(delete("/api/niveau-recrutements/{id}", niveauRecrutement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NiveauRecrutement> niveauRecrutementList = niveauRecrutementRepository.findAll();
        assertThat(niveauRecrutementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
