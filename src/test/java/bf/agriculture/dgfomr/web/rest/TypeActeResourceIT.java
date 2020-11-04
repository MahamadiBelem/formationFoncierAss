package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.TypeActe;
import bf.agriculture.dgfomr.repository.TypeActeRepository;
import bf.agriculture.dgfomr.service.TypeActeService;

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
 * Integration tests for the {@link TypeActeResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeActeResourceIT {

    private static final String DEFAULT_LIBELLE_TYPE_ACTE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_ACTE = "BBBBBBBBBB";

    @Autowired
    private TypeActeRepository typeActeRepository;

    @Autowired
    private TypeActeService typeActeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeActeMockMvc;

    private TypeActe typeActe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeActe createEntity(EntityManager em) {
        TypeActe typeActe = new TypeActe()
            .libelleTypeActe(DEFAULT_LIBELLE_TYPE_ACTE);
        return typeActe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeActe createUpdatedEntity(EntityManager em) {
        TypeActe typeActe = new TypeActe()
            .libelleTypeActe(UPDATED_LIBELLE_TYPE_ACTE);
        return typeActe;
    }

    @BeforeEach
    public void initTest() {
        typeActe = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeActe() throws Exception {
        int databaseSizeBeforeCreate = typeActeRepository.findAll().size();
        // Create the TypeActe
        restTypeActeMockMvc.perform(post("/api/type-actes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeActe)))
            .andExpect(status().isCreated());

        // Validate the TypeActe in the database
        List<TypeActe> typeActeList = typeActeRepository.findAll();
        assertThat(typeActeList).hasSize(databaseSizeBeforeCreate + 1);
        TypeActe testTypeActe = typeActeList.get(typeActeList.size() - 1);
        assertThat(testTypeActe.getLibelleTypeActe()).isEqualTo(DEFAULT_LIBELLE_TYPE_ACTE);
    }

    @Test
    @Transactional
    public void createTypeActeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeActeRepository.findAll().size();

        // Create the TypeActe with an existing ID
        typeActe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeActeMockMvc.perform(post("/api/type-actes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeActe)))
            .andExpect(status().isBadRequest());

        // Validate the TypeActe in the database
        List<TypeActe> typeActeList = typeActeRepository.findAll();
        assertThat(typeActeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeActes() throws Exception {
        // Initialize the database
        typeActeRepository.saveAndFlush(typeActe);

        // Get all the typeActeList
        restTypeActeMockMvc.perform(get("/api/type-actes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeActe.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleTypeActe").value(hasItem(DEFAULT_LIBELLE_TYPE_ACTE)));
    }
    
    @Test
    @Transactional
    public void getTypeActe() throws Exception {
        // Initialize the database
        typeActeRepository.saveAndFlush(typeActe);

        // Get the typeActe
        restTypeActeMockMvc.perform(get("/api/type-actes/{id}", typeActe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeActe.getId().intValue()))
            .andExpect(jsonPath("$.libelleTypeActe").value(DEFAULT_LIBELLE_TYPE_ACTE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeActe() throws Exception {
        // Get the typeActe
        restTypeActeMockMvc.perform(get("/api/type-actes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeActe() throws Exception {
        // Initialize the database
        typeActeService.save(typeActe);

        int databaseSizeBeforeUpdate = typeActeRepository.findAll().size();

        // Update the typeActe
        TypeActe updatedTypeActe = typeActeRepository.findById(typeActe.getId()).get();
        // Disconnect from session so that the updates on updatedTypeActe are not directly saved in db
        em.detach(updatedTypeActe);
        updatedTypeActe
            .libelleTypeActe(UPDATED_LIBELLE_TYPE_ACTE);

        restTypeActeMockMvc.perform(put("/api/type-actes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeActe)))
            .andExpect(status().isOk());

        // Validate the TypeActe in the database
        List<TypeActe> typeActeList = typeActeRepository.findAll();
        assertThat(typeActeList).hasSize(databaseSizeBeforeUpdate);
        TypeActe testTypeActe = typeActeList.get(typeActeList.size() - 1);
        assertThat(testTypeActe.getLibelleTypeActe()).isEqualTo(UPDATED_LIBELLE_TYPE_ACTE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeActe() throws Exception {
        int databaseSizeBeforeUpdate = typeActeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeActeMockMvc.perform(put("/api/type-actes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeActe)))
            .andExpect(status().isBadRequest());

        // Validate the TypeActe in the database
        List<TypeActe> typeActeList = typeActeRepository.findAll();
        assertThat(typeActeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeActe() throws Exception {
        // Initialize the database
        typeActeService.save(typeActe);

        int databaseSizeBeforeDelete = typeActeRepository.findAll().size();

        // Delete the typeActe
        restTypeActeMockMvc.perform(delete("/api/type-actes/{id}", typeActe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeActe> typeActeList = typeActeRepository.findAll();
        assertThat(typeActeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
