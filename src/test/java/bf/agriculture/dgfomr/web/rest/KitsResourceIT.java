package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Kits;
import bf.agriculture.dgfomr.repository.KitsRepository;
import bf.agriculture.dgfomr.service.KitsService;

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
 * Integration tests for the {@link KitsResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KitsResourceIT {

    private static final Boolean DEFAULT_ISOBTENU = false;
    private static final Boolean UPDATED_ISOBTENU = true;

    @Autowired
    private KitsRepository kitsRepository;

    @Autowired
    private KitsService kitsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKitsMockMvc;

    private Kits kits;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kits createEntity(EntityManager em) {
        Kits kits = new Kits()
            .isobtenu(DEFAULT_ISOBTENU);
        return kits;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kits createUpdatedEntity(EntityManager em) {
        Kits kits = new Kits()
            .isobtenu(UPDATED_ISOBTENU);
        return kits;
    }

    @BeforeEach
    public void initTest() {
        kits = createEntity(em);
    }

    @Test
    @Transactional
    public void createKits() throws Exception {
        int databaseSizeBeforeCreate = kitsRepository.findAll().size();
        // Create the Kits
        restKitsMockMvc.perform(post("/api/kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kits)))
            .andExpect(status().isCreated());

        // Validate the Kits in the database
        List<Kits> kitsList = kitsRepository.findAll();
        assertThat(kitsList).hasSize(databaseSizeBeforeCreate + 1);
        Kits testKits = kitsList.get(kitsList.size() - 1);
        assertThat(testKits.isIsobtenu()).isEqualTo(DEFAULT_ISOBTENU);
    }

    @Test
    @Transactional
    public void createKitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kitsRepository.findAll().size();

        // Create the Kits with an existing ID
        kits.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKitsMockMvc.perform(post("/api/kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kits)))
            .andExpect(status().isBadRequest());

        // Validate the Kits in the database
        List<Kits> kitsList = kitsRepository.findAll();
        assertThat(kitsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllKits() throws Exception {
        // Initialize the database
        kitsRepository.saveAndFlush(kits);

        // Get all the kitsList
        restKitsMockMvc.perform(get("/api/kits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kits.getId().intValue())))
            .andExpect(jsonPath("$.[*].isobtenu").value(hasItem(DEFAULT_ISOBTENU.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKits() throws Exception {
        // Initialize the database
        kitsRepository.saveAndFlush(kits);

        // Get the kits
        restKitsMockMvc.perform(get("/api/kits/{id}", kits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kits.getId().intValue()))
            .andExpect(jsonPath("$.isobtenu").value(DEFAULT_ISOBTENU.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingKits() throws Exception {
        // Get the kits
        restKitsMockMvc.perform(get("/api/kits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKits() throws Exception {
        // Initialize the database
        kitsService.save(kits);

        int databaseSizeBeforeUpdate = kitsRepository.findAll().size();

        // Update the kits
        Kits updatedKits = kitsRepository.findById(kits.getId()).get();
        // Disconnect from session so that the updates on updatedKits are not directly saved in db
        em.detach(updatedKits);
        updatedKits
            .isobtenu(UPDATED_ISOBTENU);

        restKitsMockMvc.perform(put("/api/kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedKits)))
            .andExpect(status().isOk());

        // Validate the Kits in the database
        List<Kits> kitsList = kitsRepository.findAll();
        assertThat(kitsList).hasSize(databaseSizeBeforeUpdate);
        Kits testKits = kitsList.get(kitsList.size() - 1);
        assertThat(testKits.isIsobtenu()).isEqualTo(UPDATED_ISOBTENU);
    }

    @Test
    @Transactional
    public void updateNonExistingKits() throws Exception {
        int databaseSizeBeforeUpdate = kitsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKitsMockMvc.perform(put("/api/kits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kits)))
            .andExpect(status().isBadRequest());

        // Validate the Kits in the database
        List<Kits> kitsList = kitsRepository.findAll();
        assertThat(kitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKits() throws Exception {
        // Initialize the database
        kitsService.save(kits);

        int databaseSizeBeforeDelete = kitsRepository.findAll().size();

        // Delete the kits
        restKitsMockMvc.perform(delete("/api/kits/{id}", kits.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Kits> kitsList = kitsRepository.findAll();
        assertThat(kitsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
