package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Communes;
import bf.agriculture.dgfomr.repository.CommunesRepository;
import bf.agriculture.dgfomr.service.CommunesService;

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
 * Integration tests for the {@link CommunesResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommunesResourceIT {

    private static final String DEFAULT_LIBELLE_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_COMMUNE = "BBBBBBBBBB";

    @Autowired
    private CommunesRepository communesRepository;

    @Autowired
    private CommunesService communesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunesMockMvc;

    private Communes communes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Communes createEntity(EntityManager em) {
        Communes communes = new Communes()
            .libelleCommune(DEFAULT_LIBELLE_COMMUNE);
        return communes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Communes createUpdatedEntity(EntityManager em) {
        Communes communes = new Communes()
            .libelleCommune(UPDATED_LIBELLE_COMMUNE);
        return communes;
    }

    @BeforeEach
    public void initTest() {
        communes = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommunes() throws Exception {
        int databaseSizeBeforeCreate = communesRepository.findAll().size();
        // Create the Communes
        restCommunesMockMvc.perform(post("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communes)))
            .andExpect(status().isCreated());

        // Validate the Communes in the database
        List<Communes> communesList = communesRepository.findAll();
        assertThat(communesList).hasSize(databaseSizeBeforeCreate + 1);
        Communes testCommunes = communesList.get(communesList.size() - 1);
        assertThat(testCommunes.getLibelleCommune()).isEqualTo(DEFAULT_LIBELLE_COMMUNE);
    }

    @Test
    @Transactional
    public void createCommunesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = communesRepository.findAll().size();

        // Create the Communes with an existing ID
        communes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunesMockMvc.perform(post("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communes)))
            .andExpect(status().isBadRequest());

        // Validate the Communes in the database
        List<Communes> communesList = communesRepository.findAll();
        assertThat(communesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleCommuneIsRequired() throws Exception {
        int databaseSizeBeforeTest = communesRepository.findAll().size();
        // set the field null
        communes.setLibelleCommune(null);

        // Create the Communes, which fails.


        restCommunesMockMvc.perform(post("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communes)))
            .andExpect(status().isBadRequest());

        List<Communes> communesList = communesRepository.findAll();
        assertThat(communesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCommunes() throws Exception {
        // Initialize the database
        communesRepository.saveAndFlush(communes);

        // Get all the communesList
        restCommunesMockMvc.perform(get("/api/communes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communes.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleCommune").value(hasItem(DEFAULT_LIBELLE_COMMUNE)));
    }
    
    @Test
    @Transactional
    public void getCommunes() throws Exception {
        // Initialize the database
        communesRepository.saveAndFlush(communes);

        // Get the communes
        restCommunesMockMvc.perform(get("/api/communes/{id}", communes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communes.getId().intValue()))
            .andExpect(jsonPath("$.libelleCommune").value(DEFAULT_LIBELLE_COMMUNE));
    }
    @Test
    @Transactional
    public void getNonExistingCommunes() throws Exception {
        // Get the communes
        restCommunesMockMvc.perform(get("/api/communes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommunes() throws Exception {
        // Initialize the database
        communesService.save(communes);

        int databaseSizeBeforeUpdate = communesRepository.findAll().size();

        // Update the communes
        Communes updatedCommunes = communesRepository.findById(communes.getId()).get();
        // Disconnect from session so that the updates on updatedCommunes are not directly saved in db
        em.detach(updatedCommunes);
        updatedCommunes
            .libelleCommune(UPDATED_LIBELLE_COMMUNE);

        restCommunesMockMvc.perform(put("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommunes)))
            .andExpect(status().isOk());

        // Validate the Communes in the database
        List<Communes> communesList = communesRepository.findAll();
        assertThat(communesList).hasSize(databaseSizeBeforeUpdate);
        Communes testCommunes = communesList.get(communesList.size() - 1);
        assertThat(testCommunes.getLibelleCommune()).isEqualTo(UPDATED_LIBELLE_COMMUNE);
    }

    @Test
    @Transactional
    public void updateNonExistingCommunes() throws Exception {
        int databaseSizeBeforeUpdate = communesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunesMockMvc.perform(put("/api/communes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communes)))
            .andExpect(status().isBadRequest());

        // Validate the Communes in the database
        List<Communes> communesList = communesRepository.findAll();
        assertThat(communesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommunes() throws Exception {
        // Initialize the database
        communesService.save(communes);

        int databaseSizeBeforeDelete = communesRepository.findAll().size();

        // Delete the communes
        restCommunesMockMvc.perform(delete("/api/communes/{id}", communes.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Communes> communesList = communesRepository.findAll();
        assertThat(communesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
