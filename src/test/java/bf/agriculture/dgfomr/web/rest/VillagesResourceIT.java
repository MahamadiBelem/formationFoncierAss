package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Villages;
import bf.agriculture.dgfomr.repository.VillagesRepository;
import bf.agriculture.dgfomr.service.VillagesService;

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
 * Integration tests for the {@link VillagesResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VillagesResourceIT {

    private static final String DEFAULT_LIBELLE_VILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_VILLAGE = "BBBBBBBBBB";

    @Autowired
    private VillagesRepository villagesRepository;

    @Autowired
    private VillagesService villagesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVillagesMockMvc;

    private Villages villages;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Villages createEntity(EntityManager em) {
        Villages villages = new Villages()
            .libelleVillage(DEFAULT_LIBELLE_VILLAGE);
        return villages;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Villages createUpdatedEntity(EntityManager em) {
        Villages villages = new Villages()
            .libelleVillage(UPDATED_LIBELLE_VILLAGE);
        return villages;
    }

    @BeforeEach
    public void initTest() {
        villages = createEntity(em);
    }

    @Test
    @Transactional
    public void createVillages() throws Exception {
        int databaseSizeBeforeCreate = villagesRepository.findAll().size();
        // Create the Villages
        restVillagesMockMvc.perform(post("/api/villages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(villages)))
            .andExpect(status().isCreated());

        // Validate the Villages in the database
        List<Villages> villagesList = villagesRepository.findAll();
        assertThat(villagesList).hasSize(databaseSizeBeforeCreate + 1);
        Villages testVillages = villagesList.get(villagesList.size() - 1);
        assertThat(testVillages.getLibelleVillage()).isEqualTo(DEFAULT_LIBELLE_VILLAGE);
    }

    @Test
    @Transactional
    public void createVillagesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = villagesRepository.findAll().size();

        // Create the Villages with an existing ID
        villages.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVillagesMockMvc.perform(post("/api/villages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(villages)))
            .andExpect(status().isBadRequest());

        // Validate the Villages in the database
        List<Villages> villagesList = villagesRepository.findAll();
        assertThat(villagesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleVillageIsRequired() throws Exception {
        int databaseSizeBeforeTest = villagesRepository.findAll().size();
        // set the field null
        villages.setLibelleVillage(null);

        // Create the Villages, which fails.


        restVillagesMockMvc.perform(post("/api/villages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(villages)))
            .andExpect(status().isBadRequest());

        List<Villages> villagesList = villagesRepository.findAll();
        assertThat(villagesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVillages() throws Exception {
        // Initialize the database
        villagesRepository.saveAndFlush(villages);

        // Get all the villagesList
        restVillagesMockMvc.perform(get("/api/villages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(villages.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleVillage").value(hasItem(DEFAULT_LIBELLE_VILLAGE)));
    }
    
    @Test
    @Transactional
    public void getVillages() throws Exception {
        // Initialize the database
        villagesRepository.saveAndFlush(villages);

        // Get the villages
        restVillagesMockMvc.perform(get("/api/villages/{id}", villages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(villages.getId().intValue()))
            .andExpect(jsonPath("$.libelleVillage").value(DEFAULT_LIBELLE_VILLAGE));
    }
    @Test
    @Transactional
    public void getNonExistingVillages() throws Exception {
        // Get the villages
        restVillagesMockMvc.perform(get("/api/villages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVillages() throws Exception {
        // Initialize the database
        villagesService.save(villages);

        int databaseSizeBeforeUpdate = villagesRepository.findAll().size();

        // Update the villages
        Villages updatedVillages = villagesRepository.findById(villages.getId()).get();
        // Disconnect from session so that the updates on updatedVillages are not directly saved in db
        em.detach(updatedVillages);
        updatedVillages
            .libelleVillage(UPDATED_LIBELLE_VILLAGE);

        restVillagesMockMvc.perform(put("/api/villages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVillages)))
            .andExpect(status().isOk());

        // Validate the Villages in the database
        List<Villages> villagesList = villagesRepository.findAll();
        assertThat(villagesList).hasSize(databaseSizeBeforeUpdate);
        Villages testVillages = villagesList.get(villagesList.size() - 1);
        assertThat(testVillages.getLibelleVillage()).isEqualTo(UPDATED_LIBELLE_VILLAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingVillages() throws Exception {
        int databaseSizeBeforeUpdate = villagesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVillagesMockMvc.perform(put("/api/villages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(villages)))
            .andExpect(status().isBadRequest());

        // Validate the Villages in the database
        List<Villages> villagesList = villagesRepository.findAll();
        assertThat(villagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVillages() throws Exception {
        // Initialize the database
        villagesService.save(villages);

        int databaseSizeBeforeDelete = villagesRepository.findAll().size();

        // Delete the villages
        restVillagesMockMvc.perform(delete("/api/villages/{id}", villages.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Villages> villagesList = villagesRepository.findAll();
        assertThat(villagesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
