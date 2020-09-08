package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.TypeAmenagement;
import bf.agriculture.dgfomr.repository.TypeAmenagementRepository;
import bf.agriculture.dgfomr.service.TypeAmenagementService;

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
 * Integration tests for the {@link TypeAmenagementResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeAmenagementResourceIT {

    private static final String DEFAULT_LIBELLE_TYPE_AMENAGEMENT = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_AMENAGEMENT = "BBBBBBBBBB";

    @Autowired
    private TypeAmenagementRepository typeAmenagementRepository;

    @Autowired
    private TypeAmenagementService typeAmenagementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeAmenagementMockMvc;

    private TypeAmenagement typeAmenagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAmenagement createEntity(EntityManager em) {
        TypeAmenagement typeAmenagement = new TypeAmenagement()
            .libelleTypeAmenagement(DEFAULT_LIBELLE_TYPE_AMENAGEMENT);
        return typeAmenagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAmenagement createUpdatedEntity(EntityManager em) {
        TypeAmenagement typeAmenagement = new TypeAmenagement()
            .libelleTypeAmenagement(UPDATED_LIBELLE_TYPE_AMENAGEMENT);
        return typeAmenagement;
    }

    @BeforeEach
    public void initTest() {
        typeAmenagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeAmenagement() throws Exception {
        int databaseSizeBeforeCreate = typeAmenagementRepository.findAll().size();
        // Create the TypeAmenagement
        restTypeAmenagementMockMvc.perform(post("/api/type-amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAmenagement)))
            .andExpect(status().isCreated());

        // Validate the TypeAmenagement in the database
        List<TypeAmenagement> typeAmenagementList = typeAmenagementRepository.findAll();
        assertThat(typeAmenagementList).hasSize(databaseSizeBeforeCreate + 1);
        TypeAmenagement testTypeAmenagement = typeAmenagementList.get(typeAmenagementList.size() - 1);
        assertThat(testTypeAmenagement.getLibelleTypeAmenagement()).isEqualTo(DEFAULT_LIBELLE_TYPE_AMENAGEMENT);
    }

    @Test
    @Transactional
    public void createTypeAmenagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeAmenagementRepository.findAll().size();

        // Create the TypeAmenagement with an existing ID
        typeAmenagement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeAmenagementMockMvc.perform(post("/api/type-amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAmenagement)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAmenagement in the database
        List<TypeAmenagement> typeAmenagementList = typeAmenagementRepository.findAll();
        assertThat(typeAmenagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeAmenagements() throws Exception {
        // Initialize the database
        typeAmenagementRepository.saveAndFlush(typeAmenagement);

        // Get all the typeAmenagementList
        restTypeAmenagementMockMvc.perform(get("/api/type-amenagements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeAmenagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleTypeAmenagement").value(hasItem(DEFAULT_LIBELLE_TYPE_AMENAGEMENT)));
    }
    
    @Test
    @Transactional
    public void getTypeAmenagement() throws Exception {
        // Initialize the database
        typeAmenagementRepository.saveAndFlush(typeAmenagement);

        // Get the typeAmenagement
        restTypeAmenagementMockMvc.perform(get("/api/type-amenagements/{id}", typeAmenagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeAmenagement.getId().intValue()))
            .andExpect(jsonPath("$.libelleTypeAmenagement").value(DEFAULT_LIBELLE_TYPE_AMENAGEMENT));
    }
    @Test
    @Transactional
    public void getNonExistingTypeAmenagement() throws Exception {
        // Get the typeAmenagement
        restTypeAmenagementMockMvc.perform(get("/api/type-amenagements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeAmenagement() throws Exception {
        // Initialize the database
        typeAmenagementService.save(typeAmenagement);

        int databaseSizeBeforeUpdate = typeAmenagementRepository.findAll().size();

        // Update the typeAmenagement
        TypeAmenagement updatedTypeAmenagement = typeAmenagementRepository.findById(typeAmenagement.getId()).get();
        // Disconnect from session so that the updates on updatedTypeAmenagement are not directly saved in db
        em.detach(updatedTypeAmenagement);
        updatedTypeAmenagement
            .libelleTypeAmenagement(UPDATED_LIBELLE_TYPE_AMENAGEMENT);

        restTypeAmenagementMockMvc.perform(put("/api/type-amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeAmenagement)))
            .andExpect(status().isOk());

        // Validate the TypeAmenagement in the database
        List<TypeAmenagement> typeAmenagementList = typeAmenagementRepository.findAll();
        assertThat(typeAmenagementList).hasSize(databaseSizeBeforeUpdate);
        TypeAmenagement testTypeAmenagement = typeAmenagementList.get(typeAmenagementList.size() - 1);
        assertThat(testTypeAmenagement.getLibelleTypeAmenagement()).isEqualTo(UPDATED_LIBELLE_TYPE_AMENAGEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeAmenagement() throws Exception {
        int databaseSizeBeforeUpdate = typeAmenagementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAmenagementMockMvc.perform(put("/api/type-amenagements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeAmenagement)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAmenagement in the database
        List<TypeAmenagement> typeAmenagementList = typeAmenagementRepository.findAll();
        assertThat(typeAmenagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeAmenagement() throws Exception {
        // Initialize the database
        typeAmenagementService.save(typeAmenagement);

        int databaseSizeBeforeDelete = typeAmenagementRepository.findAll().size();

        // Delete the typeAmenagement
        restTypeAmenagementMockMvc.perform(delete("/api/type-amenagements/{id}", typeAmenagement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeAmenagement> typeAmenagementList = typeAmenagementRepository.findAll();
        assertThat(typeAmenagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
