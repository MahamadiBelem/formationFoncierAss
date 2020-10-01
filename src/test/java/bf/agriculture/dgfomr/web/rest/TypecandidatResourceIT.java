package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Typecandidat;
import bf.agriculture.dgfomr.repository.TypecandidatRepository;
import bf.agriculture.dgfomr.service.TypecandidatService;

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
 * Integration tests for the {@link TypecandidatResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypecandidatResourceIT {

    private static final String DEFAULT_LIBELLE_TYPE_CANDIDAT = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_CANDIDAT = "BBBBBBBBBB";

    @Autowired
    private TypecandidatRepository typecandidatRepository;

    @Autowired
    private TypecandidatService typecandidatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypecandidatMockMvc;

    private Typecandidat typecandidat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typecandidat createEntity(EntityManager em) {
        Typecandidat typecandidat = new Typecandidat()
            .libelleTypeCandidat(DEFAULT_LIBELLE_TYPE_CANDIDAT);
        return typecandidat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Typecandidat createUpdatedEntity(EntityManager em) {
        Typecandidat typecandidat = new Typecandidat()
            .libelleTypeCandidat(UPDATED_LIBELLE_TYPE_CANDIDAT);
        return typecandidat;
    }

    @BeforeEach
    public void initTest() {
        typecandidat = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypecandidat() throws Exception {
        int databaseSizeBeforeCreate = typecandidatRepository.findAll().size();
        // Create the Typecandidat
        restTypecandidatMockMvc.perform(post("/api/typecandidats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecandidat)))
            .andExpect(status().isCreated());

        // Validate the Typecandidat in the database
        List<Typecandidat> typecandidatList = typecandidatRepository.findAll();
        assertThat(typecandidatList).hasSize(databaseSizeBeforeCreate + 1);
        Typecandidat testTypecandidat = typecandidatList.get(typecandidatList.size() - 1);
        assertThat(testTypecandidat.getLibelleTypeCandidat()).isEqualTo(DEFAULT_LIBELLE_TYPE_CANDIDAT);
    }

    @Test
    @Transactional
    public void createTypecandidatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typecandidatRepository.findAll().size();

        // Create the Typecandidat with an existing ID
        typecandidat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypecandidatMockMvc.perform(post("/api/typecandidats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecandidat)))
            .andExpect(status().isBadRequest());

        // Validate the Typecandidat in the database
        List<Typecandidat> typecandidatList = typecandidatRepository.findAll();
        assertThat(typecandidatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleTypeCandidatIsRequired() throws Exception {
        int databaseSizeBeforeTest = typecandidatRepository.findAll().size();
        // set the field null
        typecandidat.setLibelleTypeCandidat(null);

        // Create the Typecandidat, which fails.


        restTypecandidatMockMvc.perform(post("/api/typecandidats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecandidat)))
            .andExpect(status().isBadRequest());

        List<Typecandidat> typecandidatList = typecandidatRepository.findAll();
        assertThat(typecandidatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypecandidats() throws Exception {
        // Initialize the database
        typecandidatRepository.saveAndFlush(typecandidat);

        // Get all the typecandidatList
        restTypecandidatMockMvc.perform(get("/api/typecandidats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typecandidat.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleTypeCandidat").value(hasItem(DEFAULT_LIBELLE_TYPE_CANDIDAT)));
    }
    
    @Test
    @Transactional
    public void getTypecandidat() throws Exception {
        // Initialize the database
        typecandidatRepository.saveAndFlush(typecandidat);

        // Get the typecandidat
        restTypecandidatMockMvc.perform(get("/api/typecandidats/{id}", typecandidat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typecandidat.getId().intValue()))
            .andExpect(jsonPath("$.libelleTypeCandidat").value(DEFAULT_LIBELLE_TYPE_CANDIDAT));
    }
    @Test
    @Transactional
    public void getNonExistingTypecandidat() throws Exception {
        // Get the typecandidat
        restTypecandidatMockMvc.perform(get("/api/typecandidats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypecandidat() throws Exception {
        // Initialize the database
        typecandidatService.save(typecandidat);

        int databaseSizeBeforeUpdate = typecandidatRepository.findAll().size();

        // Update the typecandidat
        Typecandidat updatedTypecandidat = typecandidatRepository.findById(typecandidat.getId()).get();
        // Disconnect from session so that the updates on updatedTypecandidat are not directly saved in db
        em.detach(updatedTypecandidat);
        updatedTypecandidat
            .libelleTypeCandidat(UPDATED_LIBELLE_TYPE_CANDIDAT);

        restTypecandidatMockMvc.perform(put("/api/typecandidats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypecandidat)))
            .andExpect(status().isOk());

        // Validate the Typecandidat in the database
        List<Typecandidat> typecandidatList = typecandidatRepository.findAll();
        assertThat(typecandidatList).hasSize(databaseSizeBeforeUpdate);
        Typecandidat testTypecandidat = typecandidatList.get(typecandidatList.size() - 1);
        assertThat(testTypecandidat.getLibelleTypeCandidat()).isEqualTo(UPDATED_LIBELLE_TYPE_CANDIDAT);
    }

    @Test
    @Transactional
    public void updateNonExistingTypecandidat() throws Exception {
        int databaseSizeBeforeUpdate = typecandidatRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypecandidatMockMvc.perform(put("/api/typecandidats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typecandidat)))
            .andExpect(status().isBadRequest());

        // Validate the Typecandidat in the database
        List<Typecandidat> typecandidatList = typecandidatRepository.findAll();
        assertThat(typecandidatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypecandidat() throws Exception {
        // Initialize the database
        typecandidatService.save(typecandidat);

        int databaseSizeBeforeDelete = typecandidatRepository.findAll().size();

        // Delete the typecandidat
        restTypecandidatMockMvc.perform(delete("/api/typecandidats/{id}", typecandidat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Typecandidat> typecandidatList = typecandidatRepository.findAll();
        assertThat(typecandidatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
