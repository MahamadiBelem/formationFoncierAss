package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.ApprochePedagogique;
import bf.agriculture.dgfomr.repository.ApprochePedagogiqueRepository;
import bf.agriculture.dgfomr.service.ApprochePedagogiqueService;

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
 * Integration tests for the {@link ApprochePedagogiqueResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApprochePedagogiqueResourceIT {

    private static final String DEFAULT_LIBELLE_APPROCHE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_APPROCHE = "BBBBBBBBBB";

    @Autowired
    private ApprochePedagogiqueRepository approchePedagogiqueRepository;

    @Autowired
    private ApprochePedagogiqueService approchePedagogiqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApprochePedagogiqueMockMvc;

    private ApprochePedagogique approchePedagogique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApprochePedagogique createEntity(EntityManager em) {
        ApprochePedagogique approchePedagogique = new ApprochePedagogique()
            .libelleApproche(DEFAULT_LIBELLE_APPROCHE);
        return approchePedagogique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApprochePedagogique createUpdatedEntity(EntityManager em) {
        ApprochePedagogique approchePedagogique = new ApprochePedagogique()
            .libelleApproche(UPDATED_LIBELLE_APPROCHE);
        return approchePedagogique;
    }

    @BeforeEach
    public void initTest() {
        approchePedagogique = createEntity(em);
    }

    @Test
    @Transactional
    public void createApprochePedagogique() throws Exception {
        int databaseSizeBeforeCreate = approchePedagogiqueRepository.findAll().size();
        // Create the ApprochePedagogique
        restApprochePedagogiqueMockMvc.perform(post("/api/approche-pedagogiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approchePedagogique)))
            .andExpect(status().isCreated());

        // Validate the ApprochePedagogique in the database
        List<ApprochePedagogique> approchePedagogiqueList = approchePedagogiqueRepository.findAll();
        assertThat(approchePedagogiqueList).hasSize(databaseSizeBeforeCreate + 1);
        ApprochePedagogique testApprochePedagogique = approchePedagogiqueList.get(approchePedagogiqueList.size() - 1);
        assertThat(testApprochePedagogique.getLibelleApproche()).isEqualTo(DEFAULT_LIBELLE_APPROCHE);
    }

    @Test
    @Transactional
    public void createApprochePedagogiqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = approchePedagogiqueRepository.findAll().size();

        // Create the ApprochePedagogique with an existing ID
        approchePedagogique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprochePedagogiqueMockMvc.perform(post("/api/approche-pedagogiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approchePedagogique)))
            .andExpect(status().isBadRequest());

        // Validate the ApprochePedagogique in the database
        List<ApprochePedagogique> approchePedagogiqueList = approchePedagogiqueRepository.findAll();
        assertThat(approchePedagogiqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleApprocheIsRequired() throws Exception {
        int databaseSizeBeforeTest = approchePedagogiqueRepository.findAll().size();
        // set the field null
        approchePedagogique.setLibelleApproche(null);

        // Create the ApprochePedagogique, which fails.


        restApprochePedagogiqueMockMvc.perform(post("/api/approche-pedagogiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approchePedagogique)))
            .andExpect(status().isBadRequest());

        List<ApprochePedagogique> approchePedagogiqueList = approchePedagogiqueRepository.findAll();
        assertThat(approchePedagogiqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApprochePedagogiques() throws Exception {
        // Initialize the database
        approchePedagogiqueRepository.saveAndFlush(approchePedagogique);

        // Get all the approchePedagogiqueList
        restApprochePedagogiqueMockMvc.perform(get("/api/approche-pedagogiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approchePedagogique.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleApproche").value(hasItem(DEFAULT_LIBELLE_APPROCHE)));
    }
    
    @Test
    @Transactional
    public void getApprochePedagogique() throws Exception {
        // Initialize the database
        approchePedagogiqueRepository.saveAndFlush(approchePedagogique);

        // Get the approchePedagogique
        restApprochePedagogiqueMockMvc.perform(get("/api/approche-pedagogiques/{id}", approchePedagogique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(approchePedagogique.getId().intValue()))
            .andExpect(jsonPath("$.libelleApproche").value(DEFAULT_LIBELLE_APPROCHE));
    }
    @Test
    @Transactional
    public void getNonExistingApprochePedagogique() throws Exception {
        // Get the approchePedagogique
        restApprochePedagogiqueMockMvc.perform(get("/api/approche-pedagogiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprochePedagogique() throws Exception {
        // Initialize the database
        approchePedagogiqueService.save(approchePedagogique);

        int databaseSizeBeforeUpdate = approchePedagogiqueRepository.findAll().size();

        // Update the approchePedagogique
        ApprochePedagogique updatedApprochePedagogique = approchePedagogiqueRepository.findById(approchePedagogique.getId()).get();
        // Disconnect from session so that the updates on updatedApprochePedagogique are not directly saved in db
        em.detach(updatedApprochePedagogique);
        updatedApprochePedagogique
            .libelleApproche(UPDATED_LIBELLE_APPROCHE);

        restApprochePedagogiqueMockMvc.perform(put("/api/approche-pedagogiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedApprochePedagogique)))
            .andExpect(status().isOk());

        // Validate the ApprochePedagogique in the database
        List<ApprochePedagogique> approchePedagogiqueList = approchePedagogiqueRepository.findAll();
        assertThat(approchePedagogiqueList).hasSize(databaseSizeBeforeUpdate);
        ApprochePedagogique testApprochePedagogique = approchePedagogiqueList.get(approchePedagogiqueList.size() - 1);
        assertThat(testApprochePedagogique.getLibelleApproche()).isEqualTo(UPDATED_LIBELLE_APPROCHE);
    }

    @Test
    @Transactional
    public void updateNonExistingApprochePedagogique() throws Exception {
        int databaseSizeBeforeUpdate = approchePedagogiqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApprochePedagogiqueMockMvc.perform(put("/api/approche-pedagogiques")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(approchePedagogique)))
            .andExpect(status().isBadRequest());

        // Validate the ApprochePedagogique in the database
        List<ApprochePedagogique> approchePedagogiqueList = approchePedagogiqueRepository.findAll();
        assertThat(approchePedagogiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApprochePedagogique() throws Exception {
        // Initialize the database
        approchePedagogiqueService.save(approchePedagogique);

        int databaseSizeBeforeDelete = approchePedagogiqueRepository.findAll().size();

        // Delete the approchePedagogique
        restApprochePedagogiqueMockMvc.perform(delete("/api/approche-pedagogiques/{id}", approchePedagogique.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApprochePedagogique> approchePedagogiqueList = approchePedagogiqueRepository.findAll();
        assertThat(approchePedagogiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
