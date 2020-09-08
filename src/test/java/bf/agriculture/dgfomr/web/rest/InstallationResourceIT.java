package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Installation;
import bf.agriculture.dgfomr.repository.InstallationRepository;
import bf.agriculture.dgfomr.service.InstallationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InstallationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InstallationResourceIT {

    private static final String DEFAULT_ANNEES_INSTALLATION = "AAAAAAAAAA";
    private static final String UPDATED_ANNEES_INSTALLATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_INTALLATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_INTALLATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LIEU_INSTALLATION = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_INSTALLATION = "BBBBBBBBBB";

    @Autowired
    private InstallationRepository installationRepository;

    @Mock
    private InstallationRepository installationRepositoryMock;

    @Mock
    private InstallationService installationServiceMock;

    @Autowired
    private InstallationService installationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstallationMockMvc;

    private Installation installation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Installation createEntity(EntityManager em) {
        Installation installation = new Installation()
            .anneesInstallation(DEFAULT_ANNEES_INSTALLATION)
            .dateIntallation(DEFAULT_DATE_INTALLATION)
            .lieuInstallation(DEFAULT_LIEU_INSTALLATION);
        return installation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Installation createUpdatedEntity(EntityManager em) {
        Installation installation = new Installation()
            .anneesInstallation(UPDATED_ANNEES_INSTALLATION)
            .dateIntallation(UPDATED_DATE_INTALLATION)
            .lieuInstallation(UPDATED_LIEU_INSTALLATION);
        return installation;
    }

    @BeforeEach
    public void initTest() {
        installation = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstallation() throws Exception {
        int databaseSizeBeforeCreate = installationRepository.findAll().size();
        // Create the Installation
        restInstallationMockMvc.perform(post("/api/installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installation)))
            .andExpect(status().isCreated());

        // Validate the Installation in the database
        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeCreate + 1);
        Installation testInstallation = installationList.get(installationList.size() - 1);
        assertThat(testInstallation.getAnneesInstallation()).isEqualTo(DEFAULT_ANNEES_INSTALLATION);
        assertThat(testInstallation.getDateIntallation()).isEqualTo(DEFAULT_DATE_INTALLATION);
        assertThat(testInstallation.getLieuInstallation()).isEqualTo(DEFAULT_LIEU_INSTALLATION);
    }

    @Test
    @Transactional
    public void createInstallationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = installationRepository.findAll().size();

        // Create the Installation with an existing ID
        installation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstallationMockMvc.perform(post("/api/installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installation)))
            .andExpect(status().isBadRequest());

        // Validate the Installation in the database
        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAnneesInstallationIsRequired() throws Exception {
        int databaseSizeBeforeTest = installationRepository.findAll().size();
        // set the field null
        installation.setAnneesInstallation(null);

        // Create the Installation, which fails.


        restInstallationMockMvc.perform(post("/api/installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installation)))
            .andExpect(status().isBadRequest());

        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIntallationIsRequired() throws Exception {
        int databaseSizeBeforeTest = installationRepository.findAll().size();
        // set the field null
        installation.setDateIntallation(null);

        // Create the Installation, which fails.


        restInstallationMockMvc.perform(post("/api/installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installation)))
            .andExpect(status().isBadRequest());

        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstallations() throws Exception {
        // Initialize the database
        installationRepository.saveAndFlush(installation);

        // Get all the installationList
        restInstallationMockMvc.perform(get("/api/installations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(installation.getId().intValue())))
            .andExpect(jsonPath("$.[*].anneesInstallation").value(hasItem(DEFAULT_ANNEES_INSTALLATION)))
            .andExpect(jsonPath("$.[*].dateIntallation").value(hasItem(DEFAULT_DATE_INTALLATION.toString())))
            .andExpect(jsonPath("$.[*].lieuInstallation").value(hasItem(DEFAULT_LIEU_INSTALLATION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllInstallationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(installationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInstallationMockMvc.perform(get("/api/installations?eagerload=true"))
            .andExpect(status().isOk());

        verify(installationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllInstallationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(installationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInstallationMockMvc.perform(get("/api/installations?eagerload=true"))
            .andExpect(status().isOk());

        verify(installationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getInstallation() throws Exception {
        // Initialize the database
        installationRepository.saveAndFlush(installation);

        // Get the installation
        restInstallationMockMvc.perform(get("/api/installations/{id}", installation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(installation.getId().intValue()))
            .andExpect(jsonPath("$.anneesInstallation").value(DEFAULT_ANNEES_INSTALLATION))
            .andExpect(jsonPath("$.dateIntallation").value(DEFAULT_DATE_INTALLATION.toString()))
            .andExpect(jsonPath("$.lieuInstallation").value(DEFAULT_LIEU_INSTALLATION));
    }
    @Test
    @Transactional
    public void getNonExistingInstallation() throws Exception {
        // Get the installation
        restInstallationMockMvc.perform(get("/api/installations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstallation() throws Exception {
        // Initialize the database
        installationService.save(installation);

        int databaseSizeBeforeUpdate = installationRepository.findAll().size();

        // Update the installation
        Installation updatedInstallation = installationRepository.findById(installation.getId()).get();
        // Disconnect from session so that the updates on updatedInstallation are not directly saved in db
        em.detach(updatedInstallation);
        updatedInstallation
            .anneesInstallation(UPDATED_ANNEES_INSTALLATION)
            .dateIntallation(UPDATED_DATE_INTALLATION)
            .lieuInstallation(UPDATED_LIEU_INSTALLATION);

        restInstallationMockMvc.perform(put("/api/installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstallation)))
            .andExpect(status().isOk());

        // Validate the Installation in the database
        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeUpdate);
        Installation testInstallation = installationList.get(installationList.size() - 1);
        assertThat(testInstallation.getAnneesInstallation()).isEqualTo(UPDATED_ANNEES_INSTALLATION);
        assertThat(testInstallation.getDateIntallation()).isEqualTo(UPDATED_DATE_INTALLATION);
        assertThat(testInstallation.getLieuInstallation()).isEqualTo(UPDATED_LIEU_INSTALLATION);
    }

    @Test
    @Transactional
    public void updateNonExistingInstallation() throws Exception {
        int databaseSizeBeforeUpdate = installationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstallationMockMvc.perform(put("/api/installations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installation)))
            .andExpect(status().isBadRequest());

        // Validate the Installation in the database
        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstallation() throws Exception {
        // Initialize the database
        installationService.save(installation);

        int databaseSizeBeforeDelete = installationRepository.findAll().size();

        // Delete the installation
        restInstallationMockMvc.perform(delete("/api/installations/{id}", installation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Installation> installationList = installationRepository.findAll();
        assertThat(installationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
