package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.FormateurCentre;
import bf.agriculture.dgfomr.repository.FormateurCentreRepository;
import bf.agriculture.dgfomr.service.FormateurCentreService;

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

import bf.agriculture.dgfomr.domain.enumeration.RegimeFormateur;
/**
 * Integration tests for the {@link FormateurCentreResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FormateurCentreResourceIT {

    private static final LocalDate DEFAULT_DATE_ETABLISSEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ETABLISSEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final RegimeFormateur DEFAULT_REGIME_FORMATEUR = RegimeFormateur.Vacataire;
    private static final RegimeFormateur UPDATED_REGIME_FORMATEUR = RegimeFormateur.Permanent;

    @Autowired
    private FormateurCentreRepository formateurCentreRepository;

    @Autowired
    private FormateurCentreService formateurCentreService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormateurCentreMockMvc;

    private FormateurCentre formateurCentre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormateurCentre createEntity(EntityManager em) {
        FormateurCentre formateurCentre = new FormateurCentre()
            .dateEtablissement(DEFAULT_DATE_ETABLISSEMENT)
            .regimeFormateur(DEFAULT_REGIME_FORMATEUR);
        return formateurCentre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormateurCentre createUpdatedEntity(EntityManager em) {
        FormateurCentre formateurCentre = new FormateurCentre()
            .dateEtablissement(UPDATED_DATE_ETABLISSEMENT)
            .regimeFormateur(UPDATED_REGIME_FORMATEUR);
        return formateurCentre;
    }

    @BeforeEach
    public void initTest() {
        formateurCentre = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormateurCentre() throws Exception {
        int databaseSizeBeforeCreate = formateurCentreRepository.findAll().size();
        // Create the FormateurCentre
        restFormateurCentreMockMvc.perform(post("/api/formateur-centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurCentre)))
            .andExpect(status().isCreated());

        // Validate the FormateurCentre in the database
        List<FormateurCentre> formateurCentreList = formateurCentreRepository.findAll();
        assertThat(formateurCentreList).hasSize(databaseSizeBeforeCreate + 1);
        FormateurCentre testFormateurCentre = formateurCentreList.get(formateurCentreList.size() - 1);
        assertThat(testFormateurCentre.getDateEtablissement()).isEqualTo(DEFAULT_DATE_ETABLISSEMENT);
        assertThat(testFormateurCentre.getRegimeFormateur()).isEqualTo(DEFAULT_REGIME_FORMATEUR);
    }

    @Test
    @Transactional
    public void createFormateurCentreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formateurCentreRepository.findAll().size();

        // Create the FormateurCentre with an existing ID
        formateurCentre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormateurCentreMockMvc.perform(post("/api/formateur-centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurCentre)))
            .andExpect(status().isBadRequest());

        // Validate the FormateurCentre in the database
        List<FormateurCentre> formateurCentreList = formateurCentreRepository.findAll();
        assertThat(formateurCentreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRegimeFormateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = formateurCentreRepository.findAll().size();
        // set the field null
        formateurCentre.setRegimeFormateur(null);

        // Create the FormateurCentre, which fails.


        restFormateurCentreMockMvc.perform(post("/api/formateur-centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurCentre)))
            .andExpect(status().isBadRequest());

        List<FormateurCentre> formateurCentreList = formateurCentreRepository.findAll();
        assertThat(formateurCentreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFormateurCentres() throws Exception {
        // Initialize the database
        formateurCentreRepository.saveAndFlush(formateurCentre);

        // Get all the formateurCentreList
        restFormateurCentreMockMvc.perform(get("/api/formateur-centres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formateurCentre.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateEtablissement").value(hasItem(DEFAULT_DATE_ETABLISSEMENT.toString())))
            .andExpect(jsonPath("$.[*].regimeFormateur").value(hasItem(DEFAULT_REGIME_FORMATEUR.toString())));
    }
    
    @Test
    @Transactional
    public void getFormateurCentre() throws Exception {
        // Initialize the database
        formateurCentreRepository.saveAndFlush(formateurCentre);

        // Get the formateurCentre
        restFormateurCentreMockMvc.perform(get("/api/formateur-centres/{id}", formateurCentre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formateurCentre.getId().intValue()))
            .andExpect(jsonPath("$.dateEtablissement").value(DEFAULT_DATE_ETABLISSEMENT.toString()))
            .andExpect(jsonPath("$.regimeFormateur").value(DEFAULT_REGIME_FORMATEUR.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFormateurCentre() throws Exception {
        // Get the formateurCentre
        restFormateurCentreMockMvc.perform(get("/api/formateur-centres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormateurCentre() throws Exception {
        // Initialize the database
        formateurCentreService.save(formateurCentre);

        int databaseSizeBeforeUpdate = formateurCentreRepository.findAll().size();

        // Update the formateurCentre
        FormateurCentre updatedFormateurCentre = formateurCentreRepository.findById(formateurCentre.getId()).get();
        // Disconnect from session so that the updates on updatedFormateurCentre are not directly saved in db
        em.detach(updatedFormateurCentre);
        updatedFormateurCentre
            .dateEtablissement(UPDATED_DATE_ETABLISSEMENT)
            .regimeFormateur(UPDATED_REGIME_FORMATEUR);

        restFormateurCentreMockMvc.perform(put("/api/formateur-centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormateurCentre)))
            .andExpect(status().isOk());

        // Validate the FormateurCentre in the database
        List<FormateurCentre> formateurCentreList = formateurCentreRepository.findAll();
        assertThat(formateurCentreList).hasSize(databaseSizeBeforeUpdate);
        FormateurCentre testFormateurCentre = formateurCentreList.get(formateurCentreList.size() - 1);
        assertThat(testFormateurCentre.getDateEtablissement()).isEqualTo(UPDATED_DATE_ETABLISSEMENT);
        assertThat(testFormateurCentre.getRegimeFormateur()).isEqualTo(UPDATED_REGIME_FORMATEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingFormateurCentre() throws Exception {
        int databaseSizeBeforeUpdate = formateurCentreRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormateurCentreMockMvc.perform(put("/api/formateur-centres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formateurCentre)))
            .andExpect(status().isBadRequest());

        // Validate the FormateurCentre in the database
        List<FormateurCentre> formateurCentreList = formateurCentreRepository.findAll();
        assertThat(formateurCentreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormateurCentre() throws Exception {
        // Initialize the database
        formateurCentreService.save(formateurCentre);

        int databaseSizeBeforeDelete = formateurCentreRepository.findAll().size();

        // Delete the formateurCentre
        restFormateurCentreMockMvc.perform(delete("/api/formateur-centres/{id}", formateurCentre.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormateurCentre> formateurCentreList = formateurCentreRepository.findAll();
        assertThat(formateurCentreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
