package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.CompositionKits;
import bf.agriculture.dgfomr.repository.CompositionKitsRepository;
import bf.agriculture.dgfomr.service.CompositionKitsService;

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
 * Integration tests for the {@link CompositionKitsResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CompositionKitsResourceIT {

    private static final String DEFAULT_LIBELLE_KITS = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_KITS = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITE_KITS = 1L;
    private static final Long UPDATED_QUANTITE_KITS = 2L;

    @Autowired
    private CompositionKitsRepository compositionKitsRepository;

    @Autowired
    private CompositionKitsService compositionKitsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompositionKitsMockMvc;

    private CompositionKits compositionKits;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompositionKits createEntity(EntityManager em) {
        CompositionKits compositionKits = new CompositionKits()
            .libelleKits(DEFAULT_LIBELLE_KITS)
            .quantiteKits(DEFAULT_QUANTITE_KITS);
        return compositionKits;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompositionKits createUpdatedEntity(EntityManager em) {
        CompositionKits compositionKits = new CompositionKits()
            .libelleKits(UPDATED_LIBELLE_KITS)
            .quantiteKits(UPDATED_QUANTITE_KITS);
        return compositionKits;
    }

    @BeforeEach
    public void initTest() {
        compositionKits = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompositionKits() throws Exception {
        int databaseSizeBeforeCreate = compositionKitsRepository.findAll().size();
        // Create the CompositionKits
        restCompositionKitsMockMvc.perform(post("/api/composition-kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compositionKits)))
            .andExpect(status().isCreated());

        // Validate the CompositionKits in the database
        List<CompositionKits> compositionKitsList = compositionKitsRepository.findAll();
        assertThat(compositionKitsList).hasSize(databaseSizeBeforeCreate + 1);
        CompositionKits testCompositionKits = compositionKitsList.get(compositionKitsList.size() - 1);
        assertThat(testCompositionKits.getLibelleKits()).isEqualTo(DEFAULT_LIBELLE_KITS);
        assertThat(testCompositionKits.getQuantiteKits()).isEqualTo(DEFAULT_QUANTITE_KITS);
    }

    @Test
    @Transactional
    public void createCompositionKitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compositionKitsRepository.findAll().size();

        // Create the CompositionKits with an existing ID
        compositionKits.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompositionKitsMockMvc.perform(post("/api/composition-kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compositionKits)))
            .andExpect(status().isBadRequest());

        // Validate the CompositionKits in the database
        List<CompositionKits> compositionKitsList = compositionKitsRepository.findAll();
        assertThat(compositionKitsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCompositionKits() throws Exception {
        // Initialize the database
        compositionKitsRepository.saveAndFlush(compositionKits);

        // Get all the compositionKitsList
        restCompositionKitsMockMvc.perform(get("/api/composition-kits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compositionKits.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleKits").value(hasItem(DEFAULT_LIBELLE_KITS)))
            .andExpect(jsonPath("$.[*].quantiteKits").value(hasItem(DEFAULT_QUANTITE_KITS.intValue())));
    }
    
    @Test
    @Transactional
    public void getCompositionKits() throws Exception {
        // Initialize the database
        compositionKitsRepository.saveAndFlush(compositionKits);

        // Get the compositionKits
        restCompositionKitsMockMvc.perform(get("/api/composition-kits/{id}", compositionKits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(compositionKits.getId().intValue()))
            .andExpect(jsonPath("$.libelleKits").value(DEFAULT_LIBELLE_KITS))
            .andExpect(jsonPath("$.quantiteKits").value(DEFAULT_QUANTITE_KITS.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCompositionKits() throws Exception {
        // Get the compositionKits
        restCompositionKitsMockMvc.perform(get("/api/composition-kits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompositionKits() throws Exception {
        // Initialize the database
        compositionKitsService.save(compositionKits);

        int databaseSizeBeforeUpdate = compositionKitsRepository.findAll().size();

        // Update the compositionKits
        CompositionKits updatedCompositionKits = compositionKitsRepository.findById(compositionKits.getId()).get();
        // Disconnect from session so that the updates on updatedCompositionKits are not directly saved in db
        em.detach(updatedCompositionKits);
        updatedCompositionKits
            .libelleKits(UPDATED_LIBELLE_KITS)
            .quantiteKits(UPDATED_QUANTITE_KITS);

        restCompositionKitsMockMvc.perform(put("/api/composition-kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCompositionKits)))
            .andExpect(status().isOk());

        // Validate the CompositionKits in the database
        List<CompositionKits> compositionKitsList = compositionKitsRepository.findAll();
        assertThat(compositionKitsList).hasSize(databaseSizeBeforeUpdate);
        CompositionKits testCompositionKits = compositionKitsList.get(compositionKitsList.size() - 1);
        assertThat(testCompositionKits.getLibelleKits()).isEqualTo(UPDATED_LIBELLE_KITS);
        assertThat(testCompositionKits.getQuantiteKits()).isEqualTo(UPDATED_QUANTITE_KITS);
    }

    @Test
    @Transactional
    public void updateNonExistingCompositionKits() throws Exception {
        int databaseSizeBeforeUpdate = compositionKitsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompositionKitsMockMvc.perform(put("/api/composition-kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compositionKits)))
            .andExpect(status().isBadRequest());

        // Validate the CompositionKits in the database
        List<CompositionKits> compositionKitsList = compositionKitsRepository.findAll();
        assertThat(compositionKitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompositionKits() throws Exception {
        // Initialize the database
        compositionKitsService.save(compositionKits);

        int databaseSizeBeforeDelete = compositionKitsRepository.findAll().size();

        // Delete the compositionKits
        restCompositionKitsMockMvc.perform(delete("/api/composition-kits/{id}", compositionKits.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompositionKits> compositionKitsList = compositionKitsRepository.findAll();
        assertThat(compositionKitsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
