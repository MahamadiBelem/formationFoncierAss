package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Provinces;
import bf.agriculture.dgfomr.repository.ProvincesRepository;
import bf.agriculture.dgfomr.service.ProvincesService;

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
 * Integration tests for the {@link ProvincesResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProvincesResourceIT {

    private static final String DEFAULT_LIBELLE_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_PROVINCE = "BBBBBBBBBB";

    @Autowired
    private ProvincesRepository provincesRepository;

    @Autowired
    private ProvincesService provincesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProvincesMockMvc;

    private Provinces provinces;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provinces createEntity(EntityManager em) {
        Provinces provinces = new Provinces()
            .libelleProvince(DEFAULT_LIBELLE_PROVINCE);
        return provinces;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provinces createUpdatedEntity(EntityManager em) {
        Provinces provinces = new Provinces()
            .libelleProvince(UPDATED_LIBELLE_PROVINCE);
        return provinces;
    }

    @BeforeEach
    public void initTest() {
        provinces = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvinces() throws Exception {
        int databaseSizeBeforeCreate = provincesRepository.findAll().size();
        // Create the Provinces
        restProvincesMockMvc.perform(post("/api/provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(provinces)))
            .andExpect(status().isCreated());

        // Validate the Provinces in the database
        List<Provinces> provincesList = provincesRepository.findAll();
        assertThat(provincesList).hasSize(databaseSizeBeforeCreate + 1);
        Provinces testProvinces = provincesList.get(provincesList.size() - 1);
        assertThat(testProvinces.getLibelleProvince()).isEqualTo(DEFAULT_LIBELLE_PROVINCE);
    }

    @Test
    @Transactional
    public void createProvincesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provincesRepository.findAll().size();

        // Create the Provinces with an existing ID
        provinces.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvincesMockMvc.perform(post("/api/provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(provinces)))
            .andExpect(status().isBadRequest());

        // Validate the Provinces in the database
        List<Provinces> provincesList = provincesRepository.findAll();
        assertThat(provincesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleProvinceIsRequired() throws Exception {
        int databaseSizeBeforeTest = provincesRepository.findAll().size();
        // set the field null
        provinces.setLibelleProvince(null);

        // Create the Provinces, which fails.


        restProvincesMockMvc.perform(post("/api/provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(provinces)))
            .andExpect(status().isBadRequest());

        List<Provinces> provincesList = provincesRepository.findAll();
        assertThat(provincesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProvinces() throws Exception {
        // Initialize the database
        provincesRepository.saveAndFlush(provinces);

        // Get all the provincesList
        restProvincesMockMvc.perform(get("/api/provinces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provinces.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleProvince").value(hasItem(DEFAULT_LIBELLE_PROVINCE)));
    }
    
    @Test
    @Transactional
    public void getProvinces() throws Exception {
        // Initialize the database
        provincesRepository.saveAndFlush(provinces);

        // Get the provinces
        restProvincesMockMvc.perform(get("/api/provinces/{id}", provinces.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(provinces.getId().intValue()))
            .andExpect(jsonPath("$.libelleProvince").value(DEFAULT_LIBELLE_PROVINCE));
    }
    @Test
    @Transactional
    public void getNonExistingProvinces() throws Exception {
        // Get the provinces
        restProvincesMockMvc.perform(get("/api/provinces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvinces() throws Exception {
        // Initialize the database
        provincesService.save(provinces);

        int databaseSizeBeforeUpdate = provincesRepository.findAll().size();

        // Update the provinces
        Provinces updatedProvinces = provincesRepository.findById(provinces.getId()).get();
        // Disconnect from session so that the updates on updatedProvinces are not directly saved in db
        em.detach(updatedProvinces);
        updatedProvinces
            .libelleProvince(UPDATED_LIBELLE_PROVINCE);

        restProvincesMockMvc.perform(put("/api/provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProvinces)))
            .andExpect(status().isOk());

        // Validate the Provinces in the database
        List<Provinces> provincesList = provincesRepository.findAll();
        assertThat(provincesList).hasSize(databaseSizeBeforeUpdate);
        Provinces testProvinces = provincesList.get(provincesList.size() - 1);
        assertThat(testProvinces.getLibelleProvince()).isEqualTo(UPDATED_LIBELLE_PROVINCE);
    }

    @Test
    @Transactional
    public void updateNonExistingProvinces() throws Exception {
        int databaseSizeBeforeUpdate = provincesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvincesMockMvc.perform(put("/api/provinces")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(provinces)))
            .andExpect(status().isBadRequest());

        // Validate the Provinces in the database
        List<Provinces> provincesList = provincesRepository.findAll();
        assertThat(provincesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvinces() throws Exception {
        // Initialize the database
        provincesService.save(provinces);

        int databaseSizeBeforeDelete = provincesRepository.findAll().size();

        // Delete the provinces
        restProvincesMockMvc.perform(delete("/api/provinces/{id}", provinces.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Provinces> provincesList = provincesRepository.findAll();
        assertThat(provincesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
