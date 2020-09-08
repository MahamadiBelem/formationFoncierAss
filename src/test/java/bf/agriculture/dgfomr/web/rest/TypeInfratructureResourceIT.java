package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.TypeInfratructure;
import bf.agriculture.dgfomr.repository.TypeInfratructureRepository;
import bf.agriculture.dgfomr.service.TypeInfratructureService;

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
 * Integration tests for the {@link TypeInfratructureResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeInfratructureResourceIT {

    private static final String DEFAULT_LIBELLE_TYPE_INFRACTURE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_INFRACTURE = "BBBBBBBBBB";

    @Autowired
    private TypeInfratructureRepository typeInfratructureRepository;

    @Autowired
    private TypeInfratructureService typeInfratructureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeInfratructureMockMvc;

    private TypeInfratructure typeInfratructure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeInfratructure createEntity(EntityManager em) {
        TypeInfratructure typeInfratructure = new TypeInfratructure()
            .libelleTypeInfracture(DEFAULT_LIBELLE_TYPE_INFRACTURE);
        return typeInfratructure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeInfratructure createUpdatedEntity(EntityManager em) {
        TypeInfratructure typeInfratructure = new TypeInfratructure()
            .libelleTypeInfracture(UPDATED_LIBELLE_TYPE_INFRACTURE);
        return typeInfratructure;
    }

    @BeforeEach
    public void initTest() {
        typeInfratructure = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeInfratructure() throws Exception {
        int databaseSizeBeforeCreate = typeInfratructureRepository.findAll().size();
        // Create the TypeInfratructure
        restTypeInfratructureMockMvc.perform(post("/api/type-infratructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInfratructure)))
            .andExpect(status().isCreated());

        // Validate the TypeInfratructure in the database
        List<TypeInfratructure> typeInfratructureList = typeInfratructureRepository.findAll();
        assertThat(typeInfratructureList).hasSize(databaseSizeBeforeCreate + 1);
        TypeInfratructure testTypeInfratructure = typeInfratructureList.get(typeInfratructureList.size() - 1);
        assertThat(testTypeInfratructure.getLibelleTypeInfracture()).isEqualTo(DEFAULT_LIBELLE_TYPE_INFRACTURE);
    }

    @Test
    @Transactional
    public void createTypeInfratructureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeInfratructureRepository.findAll().size();

        // Create the TypeInfratructure with an existing ID
        typeInfratructure.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeInfratructureMockMvc.perform(post("/api/type-infratructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInfratructure)))
            .andExpect(status().isBadRequest());

        // Validate the TypeInfratructure in the database
        List<TypeInfratructure> typeInfratructureList = typeInfratructureRepository.findAll();
        assertThat(typeInfratructureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeInfratructures() throws Exception {
        // Initialize the database
        typeInfratructureRepository.saveAndFlush(typeInfratructure);

        // Get all the typeInfratructureList
        restTypeInfratructureMockMvc.perform(get("/api/type-infratructures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeInfratructure.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleTypeInfracture").value(hasItem(DEFAULT_LIBELLE_TYPE_INFRACTURE)));
    }
    
    @Test
    @Transactional
    public void getTypeInfratructure() throws Exception {
        // Initialize the database
        typeInfratructureRepository.saveAndFlush(typeInfratructure);

        // Get the typeInfratructure
        restTypeInfratructureMockMvc.perform(get("/api/type-infratructures/{id}", typeInfratructure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeInfratructure.getId().intValue()))
            .andExpect(jsonPath("$.libelleTypeInfracture").value(DEFAULT_LIBELLE_TYPE_INFRACTURE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeInfratructure() throws Exception {
        // Get the typeInfratructure
        restTypeInfratructureMockMvc.perform(get("/api/type-infratructures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeInfratructure() throws Exception {
        // Initialize the database
        typeInfratructureService.save(typeInfratructure);

        int databaseSizeBeforeUpdate = typeInfratructureRepository.findAll().size();

        // Update the typeInfratructure
        TypeInfratructure updatedTypeInfratructure = typeInfratructureRepository.findById(typeInfratructure.getId()).get();
        // Disconnect from session so that the updates on updatedTypeInfratructure are not directly saved in db
        em.detach(updatedTypeInfratructure);
        updatedTypeInfratructure
            .libelleTypeInfracture(UPDATED_LIBELLE_TYPE_INFRACTURE);

        restTypeInfratructureMockMvc.perform(put("/api/type-infratructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeInfratructure)))
            .andExpect(status().isOk());

        // Validate the TypeInfratructure in the database
        List<TypeInfratructure> typeInfratructureList = typeInfratructureRepository.findAll();
        assertThat(typeInfratructureList).hasSize(databaseSizeBeforeUpdate);
        TypeInfratructure testTypeInfratructure = typeInfratructureList.get(typeInfratructureList.size() - 1);
        assertThat(testTypeInfratructure.getLibelleTypeInfracture()).isEqualTo(UPDATED_LIBELLE_TYPE_INFRACTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeInfratructure() throws Exception {
        int databaseSizeBeforeUpdate = typeInfratructureRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeInfratructureMockMvc.perform(put("/api/type-infratructures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeInfratructure)))
            .andExpect(status().isBadRequest());

        // Validate the TypeInfratructure in the database
        List<TypeInfratructure> typeInfratructureList = typeInfratructureRepository.findAll();
        assertThat(typeInfratructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeInfratructure() throws Exception {
        // Initialize the database
        typeInfratructureService.save(typeInfratructure);

        int databaseSizeBeforeDelete = typeInfratructureRepository.findAll().size();

        // Delete the typeInfratructure
        restTypeInfratructureMockMvc.perform(delete("/api/type-infratructures/{id}", typeInfratructure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeInfratructure> typeInfratructureList = typeInfratructureRepository.findAll();
        assertThat(typeInfratructureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
