package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Gestionnaire;
import bf.agriculture.dgfomr.repository.GestionnaireRepository;
import bf.agriculture.dgfomr.service.GestionnaireService;

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
 * Integration tests for the {@link GestionnaireResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GestionnaireResourceIT {

    private static final String DEFAULT_NOM_COMPLET = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COMPLET = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTS_GESTIONNAIRES = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTS_GESTIONNAIRES = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOI = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOI = "BBBBBBBBBB";

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private GestionnaireService gestionnaireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGestionnaireMockMvc;

    private Gestionnaire gestionnaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestionnaire createEntity(EntityManager em) {
        Gestionnaire gestionnaire = new Gestionnaire()
            .nomComplet(DEFAULT_NOM_COMPLET)
            .contactsGestionnaires(DEFAULT_CONTACTS_GESTIONNAIRES)
            .emploi(DEFAULT_EMPLOI);
        return gestionnaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gestionnaire createUpdatedEntity(EntityManager em) {
        Gestionnaire gestionnaire = new Gestionnaire()
            .nomComplet(UPDATED_NOM_COMPLET)
            .contactsGestionnaires(UPDATED_CONTACTS_GESTIONNAIRES)
            .emploi(UPDATED_EMPLOI);
        return gestionnaire;
    }

    @BeforeEach
    public void initTest() {
        gestionnaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createGestionnaire() throws Exception {
        int databaseSizeBeforeCreate = gestionnaireRepository.findAll().size();
        // Create the Gestionnaire
        restGestionnaireMockMvc.perform(post("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isCreated());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeCreate + 1);
        Gestionnaire testGestionnaire = gestionnaireList.get(gestionnaireList.size() - 1);
        assertThat(testGestionnaire.getNomComplet()).isEqualTo(DEFAULT_NOM_COMPLET);
        assertThat(testGestionnaire.getContactsGestionnaires()).isEqualTo(DEFAULT_CONTACTS_GESTIONNAIRES);
        assertThat(testGestionnaire.getEmploi()).isEqualTo(DEFAULT_EMPLOI);
    }

    @Test
    @Transactional
    public void createGestionnaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gestionnaireRepository.findAll().size();

        // Create the Gestionnaire with an existing ID
        gestionnaire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGestionnaireMockMvc.perform(post("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isBadRequest());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomCompletIsRequired() throws Exception {
        int databaseSizeBeforeTest = gestionnaireRepository.findAll().size();
        // set the field null
        gestionnaire.setNomComplet(null);

        // Create the Gestionnaire, which fails.


        restGestionnaireMockMvc.perform(post("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isBadRequest());

        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmploiIsRequired() throws Exception {
        int databaseSizeBeforeTest = gestionnaireRepository.findAll().size();
        // set the field null
        gestionnaire.setEmploi(null);

        // Create the Gestionnaire, which fails.


        restGestionnaireMockMvc.perform(post("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isBadRequest());

        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGestionnaires() throws Exception {
        // Initialize the database
        gestionnaireRepository.saveAndFlush(gestionnaire);

        // Get all the gestionnaireList
        restGestionnaireMockMvc.perform(get("/api/gestionnaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gestionnaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomComplet").value(hasItem(DEFAULT_NOM_COMPLET)))
            .andExpect(jsonPath("$.[*].contactsGestionnaires").value(hasItem(DEFAULT_CONTACTS_GESTIONNAIRES)))
            .andExpect(jsonPath("$.[*].emploi").value(hasItem(DEFAULT_EMPLOI)));
    }
    
    @Test
    @Transactional
    public void getGestionnaire() throws Exception {
        // Initialize the database
        gestionnaireRepository.saveAndFlush(gestionnaire);

        // Get the gestionnaire
        restGestionnaireMockMvc.perform(get("/api/gestionnaires/{id}", gestionnaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gestionnaire.getId().intValue()))
            .andExpect(jsonPath("$.nomComplet").value(DEFAULT_NOM_COMPLET))
            .andExpect(jsonPath("$.contactsGestionnaires").value(DEFAULT_CONTACTS_GESTIONNAIRES))
            .andExpect(jsonPath("$.emploi").value(DEFAULT_EMPLOI));
    }
    @Test
    @Transactional
    public void getNonExistingGestionnaire() throws Exception {
        // Get the gestionnaire
        restGestionnaireMockMvc.perform(get("/api/gestionnaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGestionnaire() throws Exception {
        // Initialize the database
        gestionnaireService.save(gestionnaire);

        int databaseSizeBeforeUpdate = gestionnaireRepository.findAll().size();

        // Update the gestionnaire
        Gestionnaire updatedGestionnaire = gestionnaireRepository.findById(gestionnaire.getId()).get();
        // Disconnect from session so that the updates on updatedGestionnaire are not directly saved in db
        em.detach(updatedGestionnaire);
        updatedGestionnaire
            .nomComplet(UPDATED_NOM_COMPLET)
            .contactsGestionnaires(UPDATED_CONTACTS_GESTIONNAIRES)
            .emploi(UPDATED_EMPLOI);

        restGestionnaireMockMvc.perform(put("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGestionnaire)))
            .andExpect(status().isOk());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeUpdate);
        Gestionnaire testGestionnaire = gestionnaireList.get(gestionnaireList.size() - 1);
        assertThat(testGestionnaire.getNomComplet()).isEqualTo(UPDATED_NOM_COMPLET);
        assertThat(testGestionnaire.getContactsGestionnaires()).isEqualTo(UPDATED_CONTACTS_GESTIONNAIRES);
        assertThat(testGestionnaire.getEmploi()).isEqualTo(UPDATED_EMPLOI);
    }

    @Test
    @Transactional
    public void updateNonExistingGestionnaire() throws Exception {
        int databaseSizeBeforeUpdate = gestionnaireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGestionnaireMockMvc.perform(put("/api/gestionnaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gestionnaire)))
            .andExpect(status().isBadRequest());

        // Validate the Gestionnaire in the database
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGestionnaire() throws Exception {
        // Initialize the database
        gestionnaireService.save(gestionnaire);

        int databaseSizeBeforeDelete = gestionnaireRepository.findAll().size();

        // Delete the gestionnaire
        restGestionnaireMockMvc.perform(delete("/api/gestionnaires/{id}", gestionnaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        assertThat(gestionnaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
