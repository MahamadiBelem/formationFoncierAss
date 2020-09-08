package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Promoteurs;
import bf.agriculture.dgfomr.repository.PromoteursRepository;
import bf.agriculture.dgfomr.service.PromoteursService;

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
 * Integration tests for the {@link PromoteursResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PromoteursResourceIT {

    private static final String DEFAULT_LIBELLE_PROMOTEUR = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_PROMOTEUR = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PROMOTEUR = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PROMOTEUR = "BBBBBBBBBB";

    @Autowired
    private PromoteursRepository promoteursRepository;

    @Autowired
    private PromoteursService promoteursService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromoteursMockMvc;

    private Promoteurs promoteurs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promoteurs createEntity(EntityManager em) {
        Promoteurs promoteurs = new Promoteurs()
            .libellePromoteur(DEFAULT_LIBELLE_PROMOTEUR)
            .contactPromoteur(DEFAULT_CONTACT_PROMOTEUR);
        return promoteurs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promoteurs createUpdatedEntity(EntityManager em) {
        Promoteurs promoteurs = new Promoteurs()
            .libellePromoteur(UPDATED_LIBELLE_PROMOTEUR)
            .contactPromoteur(UPDATED_CONTACT_PROMOTEUR);
        return promoteurs;
    }

    @BeforeEach
    public void initTest() {
        promoteurs = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromoteurs() throws Exception {
        int databaseSizeBeforeCreate = promoteursRepository.findAll().size();
        // Create the Promoteurs
        restPromoteursMockMvc.perform(post("/api/promoteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoteurs)))
            .andExpect(status().isCreated());

        // Validate the Promoteurs in the database
        List<Promoteurs> promoteursList = promoteursRepository.findAll();
        assertThat(promoteursList).hasSize(databaseSizeBeforeCreate + 1);
        Promoteurs testPromoteurs = promoteursList.get(promoteursList.size() - 1);
        assertThat(testPromoteurs.getLibellePromoteur()).isEqualTo(DEFAULT_LIBELLE_PROMOTEUR);
        assertThat(testPromoteurs.getContactPromoteur()).isEqualTo(DEFAULT_CONTACT_PROMOTEUR);
    }

    @Test
    @Transactional
    public void createPromoteursWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promoteursRepository.findAll().size();

        // Create the Promoteurs with an existing ID
        promoteurs.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromoteursMockMvc.perform(post("/api/promoteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoteurs)))
            .andExpect(status().isBadRequest());

        // Validate the Promoteurs in the database
        List<Promoteurs> promoteursList = promoteursRepository.findAll();
        assertThat(promoteursList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibellePromoteurIsRequired() throws Exception {
        int databaseSizeBeforeTest = promoteursRepository.findAll().size();
        // set the field null
        promoteurs.setLibellePromoteur(null);

        // Create the Promoteurs, which fails.


        restPromoteursMockMvc.perform(post("/api/promoteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoteurs)))
            .andExpect(status().isBadRequest());

        List<Promoteurs> promoteursList = promoteursRepository.findAll();
        assertThat(promoteursList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPromoteurs() throws Exception {
        // Initialize the database
        promoteursRepository.saveAndFlush(promoteurs);

        // Get all the promoteursList
        restPromoteursMockMvc.perform(get("/api/promoteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promoteurs.getId().intValue())))
            .andExpect(jsonPath("$.[*].libellePromoteur").value(hasItem(DEFAULT_LIBELLE_PROMOTEUR)))
            .andExpect(jsonPath("$.[*].contactPromoteur").value(hasItem(DEFAULT_CONTACT_PROMOTEUR)));
    }
    
    @Test
    @Transactional
    public void getPromoteurs() throws Exception {
        // Initialize the database
        promoteursRepository.saveAndFlush(promoteurs);

        // Get the promoteurs
        restPromoteursMockMvc.perform(get("/api/promoteurs/{id}", promoteurs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promoteurs.getId().intValue()))
            .andExpect(jsonPath("$.libellePromoteur").value(DEFAULT_LIBELLE_PROMOTEUR))
            .andExpect(jsonPath("$.contactPromoteur").value(DEFAULT_CONTACT_PROMOTEUR));
    }
    @Test
    @Transactional
    public void getNonExistingPromoteurs() throws Exception {
        // Get the promoteurs
        restPromoteursMockMvc.perform(get("/api/promoteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromoteurs() throws Exception {
        // Initialize the database
        promoteursService.save(promoteurs);

        int databaseSizeBeforeUpdate = promoteursRepository.findAll().size();

        // Update the promoteurs
        Promoteurs updatedPromoteurs = promoteursRepository.findById(promoteurs.getId()).get();
        // Disconnect from session so that the updates on updatedPromoteurs are not directly saved in db
        em.detach(updatedPromoteurs);
        updatedPromoteurs
            .libellePromoteur(UPDATED_LIBELLE_PROMOTEUR)
            .contactPromoteur(UPDATED_CONTACT_PROMOTEUR);

        restPromoteursMockMvc.perform(put("/api/promoteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPromoteurs)))
            .andExpect(status().isOk());

        // Validate the Promoteurs in the database
        List<Promoteurs> promoteursList = promoteursRepository.findAll();
        assertThat(promoteursList).hasSize(databaseSizeBeforeUpdate);
        Promoteurs testPromoteurs = promoteursList.get(promoteursList.size() - 1);
        assertThat(testPromoteurs.getLibellePromoteur()).isEqualTo(UPDATED_LIBELLE_PROMOTEUR);
        assertThat(testPromoteurs.getContactPromoteur()).isEqualTo(UPDATED_CONTACT_PROMOTEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingPromoteurs() throws Exception {
        int databaseSizeBeforeUpdate = promoteursRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromoteursMockMvc.perform(put("/api/promoteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoteurs)))
            .andExpect(status().isBadRequest());

        // Validate the Promoteurs in the database
        List<Promoteurs> promoteursList = promoteursRepository.findAll();
        assertThat(promoteursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromoteurs() throws Exception {
        // Initialize the database
        promoteursService.save(promoteurs);

        int databaseSizeBeforeDelete = promoteursRepository.findAll().size();

        // Delete the promoteurs
        restPromoteursMockMvc.perform(delete("/api/promoteurs/{id}", promoteurs.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Promoteurs> promoteursList = promoteursRepository.findAll();
        assertThat(promoteursList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
