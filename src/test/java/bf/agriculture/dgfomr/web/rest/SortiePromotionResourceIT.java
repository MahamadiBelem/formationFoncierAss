package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.SortiePromotion;
import bf.agriculture.dgfomr.repository.SortiePromotionRepository;
import bf.agriculture.dgfomr.service.SortiePromotionService;

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
 * Integration tests for the {@link SortiePromotionResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SortiePromotionResourceIT {

    private static final LocalDate DEFAULT_DATE_SORTIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_SORTIE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ANNEES_SORTIE = 1;
    private static final Integer UPDATED_ANNEES_SORTIE = 2;

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    @Autowired
    private SortiePromotionRepository sortiePromotionRepository;

    @Autowired
    private SortiePromotionService sortiePromotionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSortiePromotionMockMvc;

    private SortiePromotion sortiePromotion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SortiePromotion createEntity(EntityManager em) {
        SortiePromotion sortiePromotion = new SortiePromotion()
            .dateSortie(DEFAULT_DATE_SORTIE)
            .anneesSortie(DEFAULT_ANNEES_SORTIE)
            .motif(DEFAULT_MOTIF);
        return sortiePromotion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SortiePromotion createUpdatedEntity(EntityManager em) {
        SortiePromotion sortiePromotion = new SortiePromotion()
            .dateSortie(UPDATED_DATE_SORTIE)
            .anneesSortie(UPDATED_ANNEES_SORTIE)
            .motif(UPDATED_MOTIF);
        return sortiePromotion;
    }

    @BeforeEach
    public void initTest() {
        sortiePromotion = createEntity(em);
    }

    @Test
    @Transactional
    public void createSortiePromotion() throws Exception {
        int databaseSizeBeforeCreate = sortiePromotionRepository.findAll().size();
        // Create the SortiePromotion
        restSortiePromotionMockMvc.perform(post("/api/sortie-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortiePromotion)))
            .andExpect(status().isCreated());

        // Validate the SortiePromotion in the database
        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeCreate + 1);
        SortiePromotion testSortiePromotion = sortiePromotionList.get(sortiePromotionList.size() - 1);
        assertThat(testSortiePromotion.getDateSortie()).isEqualTo(DEFAULT_DATE_SORTIE);
        assertThat(testSortiePromotion.getAnneesSortie()).isEqualTo(DEFAULT_ANNEES_SORTIE);
        assertThat(testSortiePromotion.getMotif()).isEqualTo(DEFAULT_MOTIF);
    }

    @Test
    @Transactional
    public void createSortiePromotionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sortiePromotionRepository.findAll().size();

        // Create the SortiePromotion with an existing ID
        sortiePromotion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSortiePromotionMockMvc.perform(post("/api/sortie-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortiePromotion)))
            .andExpect(status().isBadRequest());

        // Validate the SortiePromotion in the database
        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateSortieIsRequired() throws Exception {
        int databaseSizeBeforeTest = sortiePromotionRepository.findAll().size();
        // set the field null
        sortiePromotion.setDateSortie(null);

        // Create the SortiePromotion, which fails.


        restSortiePromotionMockMvc.perform(post("/api/sortie-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortiePromotion)))
            .andExpect(status().isBadRequest());

        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneesSortieIsRequired() throws Exception {
        int databaseSizeBeforeTest = sortiePromotionRepository.findAll().size();
        // set the field null
        sortiePromotion.setAnneesSortie(null);

        // Create the SortiePromotion, which fails.


        restSortiePromotionMockMvc.perform(post("/api/sortie-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortiePromotion)))
            .andExpect(status().isBadRequest());

        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSortiePromotions() throws Exception {
        // Initialize the database
        sortiePromotionRepository.saveAndFlush(sortiePromotion);

        // Get all the sortiePromotionList
        restSortiePromotionMockMvc.perform(get("/api/sortie-promotions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sortiePromotion.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateSortie").value(hasItem(DEFAULT_DATE_SORTIE.toString())))
            .andExpect(jsonPath("$.[*].anneesSortie").value(hasItem(DEFAULT_ANNEES_SORTIE)))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF)));
    }
    
    @Test
    @Transactional
    public void getSortiePromotion() throws Exception {
        // Initialize the database
        sortiePromotionRepository.saveAndFlush(sortiePromotion);

        // Get the sortiePromotion
        restSortiePromotionMockMvc.perform(get("/api/sortie-promotions/{id}", sortiePromotion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sortiePromotion.getId().intValue()))
            .andExpect(jsonPath("$.dateSortie").value(DEFAULT_DATE_SORTIE.toString()))
            .andExpect(jsonPath("$.anneesSortie").value(DEFAULT_ANNEES_SORTIE))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF));
    }
    @Test
    @Transactional
    public void getNonExistingSortiePromotion() throws Exception {
        // Get the sortiePromotion
        restSortiePromotionMockMvc.perform(get("/api/sortie-promotions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSortiePromotion() throws Exception {
        // Initialize the database
        sortiePromotionService.save(sortiePromotion);

        int databaseSizeBeforeUpdate = sortiePromotionRepository.findAll().size();

        // Update the sortiePromotion
        SortiePromotion updatedSortiePromotion = sortiePromotionRepository.findById(sortiePromotion.getId()).get();
        // Disconnect from session so that the updates on updatedSortiePromotion are not directly saved in db
        em.detach(updatedSortiePromotion);
        updatedSortiePromotion
            .dateSortie(UPDATED_DATE_SORTIE)
            .anneesSortie(UPDATED_ANNEES_SORTIE)
            .motif(UPDATED_MOTIF);

        restSortiePromotionMockMvc.perform(put("/api/sortie-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSortiePromotion)))
            .andExpect(status().isOk());

        // Validate the SortiePromotion in the database
        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeUpdate);
        SortiePromotion testSortiePromotion = sortiePromotionList.get(sortiePromotionList.size() - 1);
        assertThat(testSortiePromotion.getDateSortie()).isEqualTo(UPDATED_DATE_SORTIE);
        assertThat(testSortiePromotion.getAnneesSortie()).isEqualTo(UPDATED_ANNEES_SORTIE);
        assertThat(testSortiePromotion.getMotif()).isEqualTo(UPDATED_MOTIF);
    }

    @Test
    @Transactional
    public void updateNonExistingSortiePromotion() throws Exception {
        int databaseSizeBeforeUpdate = sortiePromotionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSortiePromotionMockMvc.perform(put("/api/sortie-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortiePromotion)))
            .andExpect(status().isBadRequest());

        // Validate the SortiePromotion in the database
        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSortiePromotion() throws Exception {
        // Initialize the database
        sortiePromotionService.save(sortiePromotion);

        int databaseSizeBeforeDelete = sortiePromotionRepository.findAll().size();

        // Delete the sortiePromotion
        restSortiePromotionMockMvc.perform(delete("/api/sortie-promotions/{id}", sortiePromotion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SortiePromotion> sortiePromotionList = sortiePromotionRepository.findAll();
        assertThat(sortiePromotionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
