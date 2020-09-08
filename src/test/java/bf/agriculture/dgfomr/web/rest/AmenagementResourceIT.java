package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Amenagement;
import bf.agriculture.dgfomr.repository.AmenagementRepository;
import bf.agriculture.dgfomr.service.AmenagementService;

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
 * Integration tests for the {@link AmenagementResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AmenagementResourceIT {

    private static final Long DEFAULT_SUPERFICIE_TOTAL = 1L;
    private static final Long UPDATED_SUPERFICIE_TOTAL = 2L;

    private static final Long DEFAULT_SUPERFICIE_EMBAVE = 1L;
    private static final Long UPDATED_SUPERFICIE_EMBAVE = 2L;

    private static final String DEFAULT_ETATS = "AAAAAAAAAA";
    private static final String UPDATED_ETATS = "BBBBBBBBBB";

    @Autowired
    private AmenagementRepository amenagementRepository;

    @Autowired
    private AmenagementService amenagementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAmenagementMockMvc;

    private Amenagement amenagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Amenagement createEntity(EntityManager em) {
        Amenagement amenagement = new Amenagement()
            .superficieTotal(DEFAULT_SUPERFICIE_TOTAL)
            .superficieEmbave(DEFAULT_SUPERFICIE_EMBAVE)
            .etats(DEFAULT_ETATS);
        return amenagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Amenagement createUpdatedEntity(EntityManager em) {
        Amenagement amenagement = new Amenagement()
            .superficieTotal(UPDATED_SUPERFICIE_TOTAL)
            .superficieEmbave(UPDATED_SUPERFICIE_EMBAVE)
            .etats(UPDATED_ETATS);
        return amenagement;
    }

    @BeforeEach
    public void initTest() {
        amenagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAmenagement() throws Exception {
        int databaseSizeBeforeCreate = amenagementRepository.findAll().size();
        // Create the Amenagement
        restAmenagementMockMvc.perform(post("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(amenagement)))
            .andExpect(status().isCreated());

        // Validate the Amenagement in the database
        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeCreate + 1);
        Amenagement testAmenagement = amenagementList.get(amenagementList.size() - 1);
        assertThat(testAmenagement.getSuperficieTotal()).isEqualTo(DEFAULT_SUPERFICIE_TOTAL);
        assertThat(testAmenagement.getSuperficieEmbave()).isEqualTo(DEFAULT_SUPERFICIE_EMBAVE);
        assertThat(testAmenagement.getEtats()).isEqualTo(DEFAULT_ETATS);
    }

    @Test
    @Transactional
    public void createAmenagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = amenagementRepository.findAll().size();

        // Create the Amenagement with an existing ID
        amenagement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAmenagementMockMvc.perform(post("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(amenagement)))
            .andExpect(status().isBadRequest());

        // Validate the Amenagement in the database
        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSuperficieTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = amenagementRepository.findAll().size();
        // set the field null
        amenagement.setSuperficieTotal(null);

        // Create the Amenagement, which fails.


        restAmenagementMockMvc.perform(post("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(amenagement)))
            .andExpect(status().isBadRequest());

        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSuperficieEmbaveIsRequired() throws Exception {
        int databaseSizeBeforeTest = amenagementRepository.findAll().size();
        // set the field null
        amenagement.setSuperficieEmbave(null);

        // Create the Amenagement, which fails.


        restAmenagementMockMvc.perform(post("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(amenagement)))
            .andExpect(status().isBadRequest());

        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEtatsIsRequired() throws Exception {
        int databaseSizeBeforeTest = amenagementRepository.findAll().size();
        // set the field null
        amenagement.setEtats(null);

        // Create the Amenagement, which fails.


        restAmenagementMockMvc.perform(post("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(amenagement)))
            .andExpect(status().isBadRequest());

        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAmenagements() throws Exception {
        // Initialize the database
        amenagementRepository.saveAndFlush(amenagement);

        // Get all the amenagementList
        restAmenagementMockMvc.perform(get("/api/amenagements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(amenagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].superficieTotal").value(hasItem(DEFAULT_SUPERFICIE_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].superficieEmbave").value(hasItem(DEFAULT_SUPERFICIE_EMBAVE.intValue())))
            .andExpect(jsonPath("$.[*].etats").value(hasItem(DEFAULT_ETATS)));
    }
    
    @Test
    @Transactional
    public void getAmenagement() throws Exception {
        // Initialize the database
        amenagementRepository.saveAndFlush(amenagement);

        // Get the amenagement
        restAmenagementMockMvc.perform(get("/api/amenagements/{id}", amenagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(amenagement.getId().intValue()))
            .andExpect(jsonPath("$.superficieTotal").value(DEFAULT_SUPERFICIE_TOTAL.intValue()))
            .andExpect(jsonPath("$.superficieEmbave").value(DEFAULT_SUPERFICIE_EMBAVE.intValue()))
            .andExpect(jsonPath("$.etats").value(DEFAULT_ETATS));
    }
    @Test
    @Transactional
    public void getNonExistingAmenagement() throws Exception {
        // Get the amenagement
        restAmenagementMockMvc.perform(get("/api/amenagements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAmenagement() throws Exception {
        // Initialize the database
        amenagementService.save(amenagement);

        int databaseSizeBeforeUpdate = amenagementRepository.findAll().size();

        // Update the amenagement
        Amenagement updatedAmenagement = amenagementRepository.findById(amenagement.getId()).get();
        // Disconnect from session so that the updates on updatedAmenagement are not directly saved in db
        em.detach(updatedAmenagement);
        updatedAmenagement
            .superficieTotal(UPDATED_SUPERFICIE_TOTAL)
            .superficieEmbave(UPDATED_SUPERFICIE_EMBAVE)
            .etats(UPDATED_ETATS);

        restAmenagementMockMvc.perform(put("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAmenagement)))
            .andExpect(status().isOk());

        // Validate the Amenagement in the database
        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeUpdate);
        Amenagement testAmenagement = amenagementList.get(amenagementList.size() - 1);
        assertThat(testAmenagement.getSuperficieTotal()).isEqualTo(UPDATED_SUPERFICIE_TOTAL);
        assertThat(testAmenagement.getSuperficieEmbave()).isEqualTo(UPDATED_SUPERFICIE_EMBAVE);
        assertThat(testAmenagement.getEtats()).isEqualTo(UPDATED_ETATS);
    }

    @Test
    @Transactional
    public void updateNonExistingAmenagement() throws Exception {
        int databaseSizeBeforeUpdate = amenagementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAmenagementMockMvc.perform(put("/api/amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(amenagement)))
            .andExpect(status().isBadRequest());

        // Validate the Amenagement in the database
        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAmenagement() throws Exception {
        // Initialize the database
        amenagementService.save(amenagement);

        int databaseSizeBeforeDelete = amenagementRepository.findAll().size();

        // Delete the amenagement
        restAmenagementMockMvc.perform(delete("/api/amenagements/{id}", amenagement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Amenagement> amenagementList = amenagementRepository.findAll();
        assertThat(amenagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
