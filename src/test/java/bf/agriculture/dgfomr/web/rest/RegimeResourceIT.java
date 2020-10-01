package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Regime;
import bf.agriculture.dgfomr.repository.RegimeRepository;
import bf.agriculture.dgfomr.service.RegimeService;

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
 * Integration tests for the {@link RegimeResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RegimeResourceIT {

    private static final String DEFAULT_LIBELLE_REGIME = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_REGIME = "BBBBBBBBBB";

    @Autowired
    private RegimeRepository regimeRepository;

    @Autowired
    private RegimeService regimeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegimeMockMvc;

    private Regime regime;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Regime createEntity(EntityManager em) {
        Regime regime = new Regime()
            .libelleRegime(DEFAULT_LIBELLE_REGIME);
        return regime;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Regime createUpdatedEntity(EntityManager em) {
        Regime regime = new Regime()
            .libelleRegime(UPDATED_LIBELLE_REGIME);
        return regime;
    }

    @BeforeEach
    public void initTest() {
        regime = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegime() throws Exception {
        int databaseSizeBeforeCreate = regimeRepository.findAll().size();
        // Create the Regime
        restRegimeMockMvc.perform(post("/api/regimes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regime)))
            .andExpect(status().isCreated());

        // Validate the Regime in the database
        List<Regime> regimeList = regimeRepository.findAll();
        assertThat(regimeList).hasSize(databaseSizeBeforeCreate + 1);
        Regime testRegime = regimeList.get(regimeList.size() - 1);
        assertThat(testRegime.getLibelleRegime()).isEqualTo(DEFAULT_LIBELLE_REGIME);
    }

    @Test
    @Transactional
    public void createRegimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regimeRepository.findAll().size();

        // Create the Regime with an existing ID
        regime.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegimeMockMvc.perform(post("/api/regimes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regime)))
            .andExpect(status().isBadRequest());

        // Validate the Regime in the database
        List<Regime> regimeList = regimeRepository.findAll();
        assertThat(regimeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleRegimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = regimeRepository.findAll().size();
        // set the field null
        regime.setLibelleRegime(null);

        // Create the Regime, which fails.


        restRegimeMockMvc.perform(post("/api/regimes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regime)))
            .andExpect(status().isBadRequest());

        List<Regime> regimeList = regimeRepository.findAll();
        assertThat(regimeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRegimes() throws Exception {
        // Initialize the database
        regimeRepository.saveAndFlush(regime);

        // Get all the regimeList
        restRegimeMockMvc.perform(get("/api/regimes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regime.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleRegime").value(hasItem(DEFAULT_LIBELLE_REGIME)));
    }
    
    @Test
    @Transactional
    public void getRegime() throws Exception {
        // Initialize the database
        regimeRepository.saveAndFlush(regime);

        // Get the regime
        restRegimeMockMvc.perform(get("/api/regimes/{id}", regime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(regime.getId().intValue()))
            .andExpect(jsonPath("$.libelleRegime").value(DEFAULT_LIBELLE_REGIME));
    }
    @Test
    @Transactional
    public void getNonExistingRegime() throws Exception {
        // Get the regime
        restRegimeMockMvc.perform(get("/api/regimes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegime() throws Exception {
        // Initialize the database
        regimeService.save(regime);

        int databaseSizeBeforeUpdate = regimeRepository.findAll().size();

        // Update the regime
        Regime updatedRegime = regimeRepository.findById(regime.getId()).get();
        // Disconnect from session so that the updates on updatedRegime are not directly saved in db
        em.detach(updatedRegime);
        updatedRegime
            .libelleRegime(UPDATED_LIBELLE_REGIME);

        restRegimeMockMvc.perform(put("/api/regimes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegime)))
            .andExpect(status().isOk());

        // Validate the Regime in the database
        List<Regime> regimeList = regimeRepository.findAll();
        assertThat(regimeList).hasSize(databaseSizeBeforeUpdate);
        Regime testRegime = regimeList.get(regimeList.size() - 1);
        assertThat(testRegime.getLibelleRegime()).isEqualTo(UPDATED_LIBELLE_REGIME);
    }

    @Test
    @Transactional
    public void updateNonExistingRegime() throws Exception {
        int databaseSizeBeforeUpdate = regimeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegimeMockMvc.perform(put("/api/regimes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regime)))
            .andExpect(status().isBadRequest());

        // Validate the Regime in the database
        List<Regime> regimeList = regimeRepository.findAll();
        assertThat(regimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegime() throws Exception {
        // Initialize the database
        regimeService.save(regime);

        int databaseSizeBeforeDelete = regimeRepository.findAll().size();

        // Delete the regime
        restRegimeMockMvc.perform(delete("/api/regimes/{id}", regime.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Regime> regimeList = regimeRepository.findAll();
        assertThat(regimeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
