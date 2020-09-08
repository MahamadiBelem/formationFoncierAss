package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.ConditionAccess;
import bf.agriculture.dgfomr.repository.ConditionAccessRepository;
import bf.agriculture.dgfomr.service.ConditionAccessService;

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
 * Integration tests for the {@link ConditionAccessResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConditionAccessResourceIT {

    private static final String DEFAULT_LIBELLE_CONDITON = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_CONDITON = "BBBBBBBBBB";

    @Autowired
    private ConditionAccessRepository conditionAccessRepository;

    @Autowired
    private ConditionAccessService conditionAccessService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConditionAccessMockMvc;

    private ConditionAccess conditionAccess;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConditionAccess createEntity(EntityManager em) {
        ConditionAccess conditionAccess = new ConditionAccess()
            .libelleConditon(DEFAULT_LIBELLE_CONDITON);
        return conditionAccess;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConditionAccess createUpdatedEntity(EntityManager em) {
        ConditionAccess conditionAccess = new ConditionAccess()
            .libelleConditon(UPDATED_LIBELLE_CONDITON);
        return conditionAccess;
    }

    @BeforeEach
    public void initTest() {
        conditionAccess = createEntity(em);
    }

    @Test
    @Transactional
    public void createConditionAccess() throws Exception {
        int databaseSizeBeforeCreate = conditionAccessRepository.findAll().size();
        // Create the ConditionAccess
        restConditionAccessMockMvc.perform(post("/api/condition-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conditionAccess)))
            .andExpect(status().isCreated());

        // Validate the ConditionAccess in the database
        List<ConditionAccess> conditionAccessList = conditionAccessRepository.findAll();
        assertThat(conditionAccessList).hasSize(databaseSizeBeforeCreate + 1);
        ConditionAccess testConditionAccess = conditionAccessList.get(conditionAccessList.size() - 1);
        assertThat(testConditionAccess.getLibelleConditon()).isEqualTo(DEFAULT_LIBELLE_CONDITON);
    }

    @Test
    @Transactional
    public void createConditionAccessWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conditionAccessRepository.findAll().size();

        // Create the ConditionAccess with an existing ID
        conditionAccess.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConditionAccessMockMvc.perform(post("/api/condition-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conditionAccess)))
            .andExpect(status().isBadRequest());

        // Validate the ConditionAccess in the database
        List<ConditionAccess> conditionAccessList = conditionAccessRepository.findAll();
        assertThat(conditionAccessList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleConditonIsRequired() throws Exception {
        int databaseSizeBeforeTest = conditionAccessRepository.findAll().size();
        // set the field null
        conditionAccess.setLibelleConditon(null);

        // Create the ConditionAccess, which fails.


        restConditionAccessMockMvc.perform(post("/api/condition-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conditionAccess)))
            .andExpect(status().isBadRequest());

        List<ConditionAccess> conditionAccessList = conditionAccessRepository.findAll();
        assertThat(conditionAccessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConditionAccesses() throws Exception {
        // Initialize the database
        conditionAccessRepository.saveAndFlush(conditionAccess);

        // Get all the conditionAccessList
        restConditionAccessMockMvc.perform(get("/api/condition-accesses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conditionAccess.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleConditon").value(hasItem(DEFAULT_LIBELLE_CONDITON)));
    }
    
    @Test
    @Transactional
    public void getConditionAccess() throws Exception {
        // Initialize the database
        conditionAccessRepository.saveAndFlush(conditionAccess);

        // Get the conditionAccess
        restConditionAccessMockMvc.perform(get("/api/condition-accesses/{id}", conditionAccess.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(conditionAccess.getId().intValue()))
            .andExpect(jsonPath("$.libelleConditon").value(DEFAULT_LIBELLE_CONDITON));
    }
    @Test
    @Transactional
    public void getNonExistingConditionAccess() throws Exception {
        // Get the conditionAccess
        restConditionAccessMockMvc.perform(get("/api/condition-accesses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConditionAccess() throws Exception {
        // Initialize the database
        conditionAccessService.save(conditionAccess);

        int databaseSizeBeforeUpdate = conditionAccessRepository.findAll().size();

        // Update the conditionAccess
        ConditionAccess updatedConditionAccess = conditionAccessRepository.findById(conditionAccess.getId()).get();
        // Disconnect from session so that the updates on updatedConditionAccess are not directly saved in db
        em.detach(updatedConditionAccess);
        updatedConditionAccess
            .libelleConditon(UPDATED_LIBELLE_CONDITON);

        restConditionAccessMockMvc.perform(put("/api/condition-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedConditionAccess)))
            .andExpect(status().isOk());

        // Validate the ConditionAccess in the database
        List<ConditionAccess> conditionAccessList = conditionAccessRepository.findAll();
        assertThat(conditionAccessList).hasSize(databaseSizeBeforeUpdate);
        ConditionAccess testConditionAccess = conditionAccessList.get(conditionAccessList.size() - 1);
        assertThat(testConditionAccess.getLibelleConditon()).isEqualTo(UPDATED_LIBELLE_CONDITON);
    }

    @Test
    @Transactional
    public void updateNonExistingConditionAccess() throws Exception {
        int databaseSizeBeforeUpdate = conditionAccessRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConditionAccessMockMvc.perform(put("/api/condition-accesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conditionAccess)))
            .andExpect(status().isBadRequest());

        // Validate the ConditionAccess in the database
        List<ConditionAccess> conditionAccessList = conditionAccessRepository.findAll();
        assertThat(conditionAccessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConditionAccess() throws Exception {
        // Initialize the database
        conditionAccessService.save(conditionAccess);

        int databaseSizeBeforeDelete = conditionAccessRepository.findAll().size();

        // Delete the conditionAccess
        restConditionAccessMockMvc.perform(delete("/api/condition-accesses/{id}", conditionAccess.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConditionAccess> conditionAccessList = conditionAccessRepository.findAll();
        assertThat(conditionAccessList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
