package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.TypeFormation;
import bf.agriculture.dgfomr.repository.TypeFormationRepository;
import bf.agriculture.dgfomr.service.TypeFormationService;

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
 * Integration tests for the {@link TypeFormationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeFormationResourceIT {

    private static final String DEFAULT_LIBELLE_TYPE_FORMATION = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_FORMATION = "BBBBBBBBBB";

    @Autowired
    private TypeFormationRepository typeFormationRepository;

    @Autowired
    private TypeFormationService typeFormationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeFormationMockMvc;

    private TypeFormation typeFormation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeFormation createEntity(EntityManager em) {
        TypeFormation typeFormation = new TypeFormation()
            .libelleTypeFormation(DEFAULT_LIBELLE_TYPE_FORMATION);
        return typeFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeFormation createUpdatedEntity(EntityManager em) {
        TypeFormation typeFormation = new TypeFormation()
            .libelleTypeFormation(UPDATED_LIBELLE_TYPE_FORMATION);
        return typeFormation;
    }

    @BeforeEach
    public void initTest() {
        typeFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeFormation() throws Exception {
        int databaseSizeBeforeCreate = typeFormationRepository.findAll().size();
        // Create the TypeFormation
        restTypeFormationMockMvc.perform(post("/api/type-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeFormation)))
            .andExpect(status().isCreated());

        // Validate the TypeFormation in the database
        List<TypeFormation> typeFormationList = typeFormationRepository.findAll();
        assertThat(typeFormationList).hasSize(databaseSizeBeforeCreate + 1);
        TypeFormation testTypeFormation = typeFormationList.get(typeFormationList.size() - 1);
        assertThat(testTypeFormation.getLibelleTypeFormation()).isEqualTo(DEFAULT_LIBELLE_TYPE_FORMATION);
    }

    @Test
    @Transactional
    public void createTypeFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeFormationRepository.findAll().size();

        // Create the TypeFormation with an existing ID
        typeFormation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeFormationMockMvc.perform(post("/api/type-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeFormation)))
            .andExpect(status().isBadRequest());

        // Validate the TypeFormation in the database
        List<TypeFormation> typeFormationList = typeFormationRepository.findAll();
        assertThat(typeFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeFormations() throws Exception {
        // Initialize the database
        typeFormationRepository.saveAndFlush(typeFormation);

        // Get all the typeFormationList
        restTypeFormationMockMvc.perform(get("/api/type-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleTypeFormation").value(hasItem(DEFAULT_LIBELLE_TYPE_FORMATION)));
    }
    
    @Test
    @Transactional
    public void getTypeFormation() throws Exception {
        // Initialize the database
        typeFormationRepository.saveAndFlush(typeFormation);

        // Get the typeFormation
        restTypeFormationMockMvc.perform(get("/api/type-formations/{id}", typeFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeFormation.getId().intValue()))
            .andExpect(jsonPath("$.libelleTypeFormation").value(DEFAULT_LIBELLE_TYPE_FORMATION));
    }
    @Test
    @Transactional
    public void getNonExistingTypeFormation() throws Exception {
        // Get the typeFormation
        restTypeFormationMockMvc.perform(get("/api/type-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeFormation() throws Exception {
        // Initialize the database
        typeFormationService.save(typeFormation);

        int databaseSizeBeforeUpdate = typeFormationRepository.findAll().size();

        // Update the typeFormation
        TypeFormation updatedTypeFormation = typeFormationRepository.findById(typeFormation.getId()).get();
        // Disconnect from session so that the updates on updatedTypeFormation are not directly saved in db
        em.detach(updatedTypeFormation);
        updatedTypeFormation
            .libelleTypeFormation(UPDATED_LIBELLE_TYPE_FORMATION);

        restTypeFormationMockMvc.perform(put("/api/type-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeFormation)))
            .andExpect(status().isOk());

        // Validate the TypeFormation in the database
        List<TypeFormation> typeFormationList = typeFormationRepository.findAll();
        assertThat(typeFormationList).hasSize(databaseSizeBeforeUpdate);
        TypeFormation testTypeFormation = typeFormationList.get(typeFormationList.size() - 1);
        assertThat(testTypeFormation.getLibelleTypeFormation()).isEqualTo(UPDATED_LIBELLE_TYPE_FORMATION);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeFormation() throws Exception {
        int databaseSizeBeforeUpdate = typeFormationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeFormationMockMvc.perform(put("/api/type-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeFormation)))
            .andExpect(status().isBadRequest());

        // Validate the TypeFormation in the database
        List<TypeFormation> typeFormationList = typeFormationRepository.findAll();
        assertThat(typeFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeFormation() throws Exception {
        // Initialize the database
        typeFormationService.save(typeFormation);

        int databaseSizeBeforeDelete = typeFormationRepository.findAll().size();

        // Delete the typeFormation
        restTypeFormationMockMvc.perform(delete("/api/type-formations/{id}", typeFormation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeFormation> typeFormationList = typeFormationRepository.findAll();
        assertThat(typeFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
