package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.SourceFiancement;
import bf.agriculture.dgfomr.repository.SourceFiancementRepository;
import bf.agriculture.dgfomr.service.SourceFiancementService;

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

import bf.agriculture.dgfomr.domain.enumeration.Partenaire;
/**
 * Integration tests for the {@link SourceFiancementResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SourceFiancementResourceIT {

    private static final String DEFAULT_LIBELLE_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_SOURCE = "BBBBBBBBBB";

    private static final Partenaire DEFAULT_PARTENAIRE = Partenaire.Etat;
    private static final Partenaire UPDATED_PARTENAIRE = Partenaire.Partenaire;

    @Autowired
    private SourceFiancementRepository sourceFiancementRepository;

    @Autowired
    private SourceFiancementService sourceFiancementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSourceFiancementMockMvc;

    private SourceFiancement sourceFiancement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SourceFiancement createEntity(EntityManager em) {
        SourceFiancement sourceFiancement = new SourceFiancement()
            .libelleSource(DEFAULT_LIBELLE_SOURCE)
            .partenaire(DEFAULT_PARTENAIRE);
        return sourceFiancement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SourceFiancement createUpdatedEntity(EntityManager em) {
        SourceFiancement sourceFiancement = new SourceFiancement()
            .libelleSource(UPDATED_LIBELLE_SOURCE)
            .partenaire(UPDATED_PARTENAIRE);
        return sourceFiancement;
    }

    @BeforeEach
    public void initTest() {
        sourceFiancement = createEntity(em);
    }

    @Test
    @Transactional
    public void createSourceFiancement() throws Exception {
        int databaseSizeBeforeCreate = sourceFiancementRepository.findAll().size();
        // Create the SourceFiancement
        restSourceFiancementMockMvc.perform(post("/api/source-fiancements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceFiancement)))
            .andExpect(status().isCreated());

        // Validate the SourceFiancement in the database
        List<SourceFiancement> sourceFiancementList = sourceFiancementRepository.findAll();
        assertThat(sourceFiancementList).hasSize(databaseSizeBeforeCreate + 1);
        SourceFiancement testSourceFiancement = sourceFiancementList.get(sourceFiancementList.size() - 1);
        assertThat(testSourceFiancement.getLibelleSource()).isEqualTo(DEFAULT_LIBELLE_SOURCE);
        assertThat(testSourceFiancement.getPartenaire()).isEqualTo(DEFAULT_PARTENAIRE);
    }

    @Test
    @Transactional
    public void createSourceFiancementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sourceFiancementRepository.findAll().size();

        // Create the SourceFiancement with an existing ID
        sourceFiancement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSourceFiancementMockMvc.perform(post("/api/source-fiancements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceFiancement)))
            .andExpect(status().isBadRequest());

        // Validate the SourceFiancement in the database
        List<SourceFiancement> sourceFiancementList = sourceFiancementRepository.findAll();
        assertThat(sourceFiancementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = sourceFiancementRepository.findAll().size();
        // set the field null
        sourceFiancement.setLibelleSource(null);

        // Create the SourceFiancement, which fails.


        restSourceFiancementMockMvc.perform(post("/api/source-fiancements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceFiancement)))
            .andExpect(status().isBadRequest());

        List<SourceFiancement> sourceFiancementList = sourceFiancementRepository.findAll();
        assertThat(sourceFiancementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSourceFiancements() throws Exception {
        // Initialize the database
        sourceFiancementRepository.saveAndFlush(sourceFiancement);

        // Get all the sourceFiancementList
        restSourceFiancementMockMvc.perform(get("/api/source-fiancements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sourceFiancement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleSource").value(hasItem(DEFAULT_LIBELLE_SOURCE)))
            .andExpect(jsonPath("$.[*].partenaire").value(hasItem(DEFAULT_PARTENAIRE.toString())));
    }
    
    @Test
    @Transactional
    public void getSourceFiancement() throws Exception {
        // Initialize the database
        sourceFiancementRepository.saveAndFlush(sourceFiancement);

        // Get the sourceFiancement
        restSourceFiancementMockMvc.perform(get("/api/source-fiancements/{id}", sourceFiancement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sourceFiancement.getId().intValue()))
            .andExpect(jsonPath("$.libelleSource").value(DEFAULT_LIBELLE_SOURCE))
            .andExpect(jsonPath("$.partenaire").value(DEFAULT_PARTENAIRE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSourceFiancement() throws Exception {
        // Get the sourceFiancement
        restSourceFiancementMockMvc.perform(get("/api/source-fiancements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSourceFiancement() throws Exception {
        // Initialize the database
        sourceFiancementService.save(sourceFiancement);

        int databaseSizeBeforeUpdate = sourceFiancementRepository.findAll().size();

        // Update the sourceFiancement
        SourceFiancement updatedSourceFiancement = sourceFiancementRepository.findById(sourceFiancement.getId()).get();
        // Disconnect from session so that the updates on updatedSourceFiancement are not directly saved in db
        em.detach(updatedSourceFiancement);
        updatedSourceFiancement
            .libelleSource(UPDATED_LIBELLE_SOURCE)
            .partenaire(UPDATED_PARTENAIRE);

        restSourceFiancementMockMvc.perform(put("/api/source-fiancements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSourceFiancement)))
            .andExpect(status().isOk());

        // Validate the SourceFiancement in the database
        List<SourceFiancement> sourceFiancementList = sourceFiancementRepository.findAll();
        assertThat(sourceFiancementList).hasSize(databaseSizeBeforeUpdate);
        SourceFiancement testSourceFiancement = sourceFiancementList.get(sourceFiancementList.size() - 1);
        assertThat(testSourceFiancement.getLibelleSource()).isEqualTo(UPDATED_LIBELLE_SOURCE);
        assertThat(testSourceFiancement.getPartenaire()).isEqualTo(UPDATED_PARTENAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingSourceFiancement() throws Exception {
        int databaseSizeBeforeUpdate = sourceFiancementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSourceFiancementMockMvc.perform(put("/api/source-fiancements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sourceFiancement)))
            .andExpect(status().isBadRequest());

        // Validate the SourceFiancement in the database
        List<SourceFiancement> sourceFiancementList = sourceFiancementRepository.findAll();
        assertThat(sourceFiancementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSourceFiancement() throws Exception {
        // Initialize the database
        sourceFiancementService.save(sourceFiancement);

        int databaseSizeBeforeDelete = sourceFiancementRepository.findAll().size();

        // Delete the sourceFiancement
        restSourceFiancementMockMvc.perform(delete("/api/source-fiancements/{id}", sourceFiancement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SourceFiancement> sourceFiancementList = sourceFiancementRepository.findAll();
        assertThat(sourceFiancementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
