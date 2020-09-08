package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Contributions;
import bf.agriculture.dgfomr.repository.ContributionsRepository;
import bf.agriculture.dgfomr.service.ContributionsService;

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
 * Integration tests for the {@link ContributionsResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ContributionsResourceIT {

    private static final String DEFAULT_LIBELLE_CONTRIBUTION = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_CONTRIBUTION = "BBBBBBBBBB";

    @Autowired
    private ContributionsRepository contributionsRepository;

    @Autowired
    private ContributionsService contributionsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContributionsMockMvc;

    private Contributions contributions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contributions createEntity(EntityManager em) {
        Contributions contributions = new Contributions()
            .libelleContribution(DEFAULT_LIBELLE_CONTRIBUTION);
        return contributions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contributions createUpdatedEntity(EntityManager em) {
        Contributions contributions = new Contributions()
            .libelleContribution(UPDATED_LIBELLE_CONTRIBUTION);
        return contributions;
    }

    @BeforeEach
    public void initTest() {
        contributions = createEntity(em);
    }

    @Test
    @Transactional
    public void createContributions() throws Exception {
        int databaseSizeBeforeCreate = contributionsRepository.findAll().size();
        // Create the Contributions
        restContributionsMockMvc.perform(post("/api/contributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contributions)))
            .andExpect(status().isCreated());

        // Validate the Contributions in the database
        List<Contributions> contributionsList = contributionsRepository.findAll();
        assertThat(contributionsList).hasSize(databaseSizeBeforeCreate + 1);
        Contributions testContributions = contributionsList.get(contributionsList.size() - 1);
        assertThat(testContributions.getLibelleContribution()).isEqualTo(DEFAULT_LIBELLE_CONTRIBUTION);
    }

    @Test
    @Transactional
    public void createContributionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contributionsRepository.findAll().size();

        // Create the Contributions with an existing ID
        contributions.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContributionsMockMvc.perform(post("/api/contributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contributions)))
            .andExpect(status().isBadRequest());

        // Validate the Contributions in the database
        List<Contributions> contributionsList = contributionsRepository.findAll();
        assertThat(contributionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleContributionIsRequired() throws Exception {
        int databaseSizeBeforeTest = contributionsRepository.findAll().size();
        // set the field null
        contributions.setLibelleContribution(null);

        // Create the Contributions, which fails.


        restContributionsMockMvc.perform(post("/api/contributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contributions)))
            .andExpect(status().isBadRequest());

        List<Contributions> contributionsList = contributionsRepository.findAll();
        assertThat(contributionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContributions() throws Exception {
        // Initialize the database
        contributionsRepository.saveAndFlush(contributions);

        // Get all the contributionsList
        restContributionsMockMvc.perform(get("/api/contributions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contributions.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleContribution").value(hasItem(DEFAULT_LIBELLE_CONTRIBUTION)));
    }
    
    @Test
    @Transactional
    public void getContributions() throws Exception {
        // Initialize the database
        contributionsRepository.saveAndFlush(contributions);

        // Get the contributions
        restContributionsMockMvc.perform(get("/api/contributions/{id}", contributions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contributions.getId().intValue()))
            .andExpect(jsonPath("$.libelleContribution").value(DEFAULT_LIBELLE_CONTRIBUTION));
    }
    @Test
    @Transactional
    public void getNonExistingContributions() throws Exception {
        // Get the contributions
        restContributionsMockMvc.perform(get("/api/contributions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContributions() throws Exception {
        // Initialize the database
        contributionsService.save(contributions);

        int databaseSizeBeforeUpdate = contributionsRepository.findAll().size();

        // Update the contributions
        Contributions updatedContributions = contributionsRepository.findById(contributions.getId()).get();
        // Disconnect from session so that the updates on updatedContributions are not directly saved in db
        em.detach(updatedContributions);
        updatedContributions
            .libelleContribution(UPDATED_LIBELLE_CONTRIBUTION);

        restContributionsMockMvc.perform(put("/api/contributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedContributions)))
            .andExpect(status().isOk());

        // Validate the Contributions in the database
        List<Contributions> contributionsList = contributionsRepository.findAll();
        assertThat(contributionsList).hasSize(databaseSizeBeforeUpdate);
        Contributions testContributions = contributionsList.get(contributionsList.size() - 1);
        assertThat(testContributions.getLibelleContribution()).isEqualTo(UPDATED_LIBELLE_CONTRIBUTION);
    }

    @Test
    @Transactional
    public void updateNonExistingContributions() throws Exception {
        int databaseSizeBeforeUpdate = contributionsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContributionsMockMvc.perform(put("/api/contributions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contributions)))
            .andExpect(status().isBadRequest());

        // Validate the Contributions in the database
        List<Contributions> contributionsList = contributionsRepository.findAll();
        assertThat(contributionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContributions() throws Exception {
        // Initialize the database
        contributionsService.save(contributions);

        int databaseSizeBeforeDelete = contributionsRepository.findAll().size();

        // Delete the contributions
        restContributionsMockMvc.perform(delete("/api/contributions/{id}", contributions.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contributions> contributionsList = contributionsRepository.findAll();
        assertThat(contributionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
