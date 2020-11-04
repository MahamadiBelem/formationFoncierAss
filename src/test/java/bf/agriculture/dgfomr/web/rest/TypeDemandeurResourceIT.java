package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.TypeDemandeur;
import bf.agriculture.dgfomr.repository.TypeDemandeurRepository;
import bf.agriculture.dgfomr.service.TypeDemandeurService;

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
 * Integration tests for the {@link TypeDemandeurResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeDemandeurResourceIT {

    private static final String DEFAULT_LIBELLE_TYPE_DEMANDEUR = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_TYPE_DEMANDEUR = "BBBBBBBBBB";

    @Autowired
    private TypeDemandeurRepository typeDemandeurRepository;

    @Autowired
    private TypeDemandeurService typeDemandeurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeDemandeurMockMvc;

    private TypeDemandeur typeDemandeur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDemandeur createEntity(EntityManager em) {
        TypeDemandeur typeDemandeur = new TypeDemandeur()
            .libelleTypeDemandeur(DEFAULT_LIBELLE_TYPE_DEMANDEUR);
        return typeDemandeur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDemandeur createUpdatedEntity(EntityManager em) {
        TypeDemandeur typeDemandeur = new TypeDemandeur()
            .libelleTypeDemandeur(UPDATED_LIBELLE_TYPE_DEMANDEUR);
        return typeDemandeur;
    }

    @BeforeEach
    public void initTest() {
        typeDemandeur = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeDemandeur() throws Exception {
        int databaseSizeBeforeCreate = typeDemandeurRepository.findAll().size();
        // Create the TypeDemandeur
        restTypeDemandeurMockMvc.perform(post("/api/type-demandeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDemandeur)))
            .andExpect(status().isCreated());

        // Validate the TypeDemandeur in the database
        List<TypeDemandeur> typeDemandeurList = typeDemandeurRepository.findAll();
        assertThat(typeDemandeurList).hasSize(databaseSizeBeforeCreate + 1);
        TypeDemandeur testTypeDemandeur = typeDemandeurList.get(typeDemandeurList.size() - 1);
        assertThat(testTypeDemandeur.getLibelleTypeDemandeur()).isEqualTo(DEFAULT_LIBELLE_TYPE_DEMANDEUR);
    }

    @Test
    @Transactional
    public void createTypeDemandeurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeDemandeurRepository.findAll().size();

        // Create the TypeDemandeur with an existing ID
        typeDemandeur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeDemandeurMockMvc.perform(post("/api/type-demandeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDemandeur)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDemandeur in the database
        List<TypeDemandeur> typeDemandeurList = typeDemandeurRepository.findAll();
        assertThat(typeDemandeurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeDemandeurs() throws Exception {
        // Initialize the database
        typeDemandeurRepository.saveAndFlush(typeDemandeur);

        // Get all the typeDemandeurList
        restTypeDemandeurMockMvc.perform(get("/api/type-demandeurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeDemandeur.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleTypeDemandeur").value(hasItem(DEFAULT_LIBELLE_TYPE_DEMANDEUR)));
    }
    
    @Test
    @Transactional
    public void getTypeDemandeur() throws Exception {
        // Initialize the database
        typeDemandeurRepository.saveAndFlush(typeDemandeur);

        // Get the typeDemandeur
        restTypeDemandeurMockMvc.perform(get("/api/type-demandeurs/{id}", typeDemandeur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeDemandeur.getId().intValue()))
            .andExpect(jsonPath("$.libelleTypeDemandeur").value(DEFAULT_LIBELLE_TYPE_DEMANDEUR));
    }
    @Test
    @Transactional
    public void getNonExistingTypeDemandeur() throws Exception {
        // Get the typeDemandeur
        restTypeDemandeurMockMvc.perform(get("/api/type-demandeurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeDemandeur() throws Exception {
        // Initialize the database
        typeDemandeurService.save(typeDemandeur);

        int databaseSizeBeforeUpdate = typeDemandeurRepository.findAll().size();

        // Update the typeDemandeur
        TypeDemandeur updatedTypeDemandeur = typeDemandeurRepository.findById(typeDemandeur.getId()).get();
        // Disconnect from session so that the updates on updatedTypeDemandeur are not directly saved in db
        em.detach(updatedTypeDemandeur);
        updatedTypeDemandeur
            .libelleTypeDemandeur(UPDATED_LIBELLE_TYPE_DEMANDEUR);

        restTypeDemandeurMockMvc.perform(put("/api/type-demandeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeDemandeur)))
            .andExpect(status().isOk());

        // Validate the TypeDemandeur in the database
        List<TypeDemandeur> typeDemandeurList = typeDemandeurRepository.findAll();
        assertThat(typeDemandeurList).hasSize(databaseSizeBeforeUpdate);
        TypeDemandeur testTypeDemandeur = typeDemandeurList.get(typeDemandeurList.size() - 1);
        assertThat(testTypeDemandeur.getLibelleTypeDemandeur()).isEqualTo(UPDATED_LIBELLE_TYPE_DEMANDEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeDemandeur() throws Exception {
        int databaseSizeBeforeUpdate = typeDemandeurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeDemandeurMockMvc.perform(put("/api/type-demandeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDemandeur)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDemandeur in the database
        List<TypeDemandeur> typeDemandeurList = typeDemandeurRepository.findAll();
        assertThat(typeDemandeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeDemandeur() throws Exception {
        // Initialize the database
        typeDemandeurService.save(typeDemandeur);

        int databaseSizeBeforeDelete = typeDemandeurRepository.findAll().size();

        // Delete the typeDemandeur
        restTypeDemandeurMockMvc.perform(delete("/api/type-demandeurs/{id}", typeDemandeur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeDemandeur> typeDemandeurList = typeDemandeurRepository.findAll();
        assertThat(typeDemandeurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
