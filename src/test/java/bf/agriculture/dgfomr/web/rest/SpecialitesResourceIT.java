package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Specialites;
import bf.agriculture.dgfomr.repository.SpecialitesRepository;
import bf.agriculture.dgfomr.service.SpecialitesService;

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
 * Integration tests for the {@link SpecialitesResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SpecialitesResourceIT {

    private static final String DEFAULT_LIBELLE_SPECIALITE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_SPECIALITE = "BBBBBBBBBB";

    @Autowired
    private SpecialitesRepository specialitesRepository;

    @Autowired
    private SpecialitesService specialitesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecialitesMockMvc;

    private Specialites specialites;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specialites createEntity(EntityManager em) {
        Specialites specialites = new Specialites()
            .libelleSpecialite(DEFAULT_LIBELLE_SPECIALITE);
        return specialites;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specialites createUpdatedEntity(EntityManager em) {
        Specialites specialites = new Specialites()
            .libelleSpecialite(UPDATED_LIBELLE_SPECIALITE);
        return specialites;
    }

    @BeforeEach
    public void initTest() {
        specialites = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecialites() throws Exception {
        int databaseSizeBeforeCreate = specialitesRepository.findAll().size();
        // Create the Specialites
        restSpecialitesMockMvc.perform(post("/api/specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialites)))
            .andExpect(status().isCreated());

        // Validate the Specialites in the database
        List<Specialites> specialitesList = specialitesRepository.findAll();
        assertThat(specialitesList).hasSize(databaseSizeBeforeCreate + 1);
        Specialites testSpecialites = specialitesList.get(specialitesList.size() - 1);
        assertThat(testSpecialites.getLibelleSpecialite()).isEqualTo(DEFAULT_LIBELLE_SPECIALITE);
    }

    @Test
    @Transactional
    public void createSpecialitesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specialitesRepository.findAll().size();

        // Create the Specialites with an existing ID
        specialites.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecialitesMockMvc.perform(post("/api/specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialites)))
            .andExpect(status().isBadRequest());

        // Validate the Specialites in the database
        List<Specialites> specialitesList = specialitesRepository.findAll();
        assertThat(specialitesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleSpecialiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = specialitesRepository.findAll().size();
        // set the field null
        specialites.setLibelleSpecialite(null);

        // Create the Specialites, which fails.


        restSpecialitesMockMvc.perform(post("/api/specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialites)))
            .andExpect(status().isBadRequest());

        List<Specialites> specialitesList = specialitesRepository.findAll();
        assertThat(specialitesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpecialites() throws Exception {
        // Initialize the database
        specialitesRepository.saveAndFlush(specialites);

        // Get all the specialitesList
        restSpecialitesMockMvc.perform(get("/api/specialites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specialites.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleSpecialite").value(hasItem(DEFAULT_LIBELLE_SPECIALITE)));
    }
    
    @Test
    @Transactional
    public void getSpecialites() throws Exception {
        // Initialize the database
        specialitesRepository.saveAndFlush(specialites);

        // Get the specialites
        restSpecialitesMockMvc.perform(get("/api/specialites/{id}", specialites.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specialites.getId().intValue()))
            .andExpect(jsonPath("$.libelleSpecialite").value(DEFAULT_LIBELLE_SPECIALITE));
    }
    @Test
    @Transactional
    public void getNonExistingSpecialites() throws Exception {
        // Get the specialites
        restSpecialitesMockMvc.perform(get("/api/specialites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecialites() throws Exception {
        // Initialize the database
        specialitesService.save(specialites);

        int databaseSizeBeforeUpdate = specialitesRepository.findAll().size();

        // Update the specialites
        Specialites updatedSpecialites = specialitesRepository.findById(specialites.getId()).get();
        // Disconnect from session so that the updates on updatedSpecialites are not directly saved in db
        em.detach(updatedSpecialites);
        updatedSpecialites
            .libelleSpecialite(UPDATED_LIBELLE_SPECIALITE);

        restSpecialitesMockMvc.perform(put("/api/specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSpecialites)))
            .andExpect(status().isOk());

        // Validate the Specialites in the database
        List<Specialites> specialitesList = specialitesRepository.findAll();
        assertThat(specialitesList).hasSize(databaseSizeBeforeUpdate);
        Specialites testSpecialites = specialitesList.get(specialitesList.size() - 1);
        assertThat(testSpecialites.getLibelleSpecialite()).isEqualTo(UPDATED_LIBELLE_SPECIALITE);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecialites() throws Exception {
        int databaseSizeBeforeUpdate = specialitesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecialitesMockMvc.perform(put("/api/specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialites)))
            .andExpect(status().isBadRequest());

        // Validate the Specialites in the database
        List<Specialites> specialitesList = specialitesRepository.findAll();
        assertThat(specialitesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecialites() throws Exception {
        // Initialize the database
        specialitesService.save(specialites);

        int databaseSizeBeforeDelete = specialitesRepository.findAll().size();

        // Delete the specialites
        restSpecialitesMockMvc.perform(delete("/api/specialites/{id}", specialites.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Specialites> specialitesList = specialitesRepository.findAll();
        assertThat(specialitesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
