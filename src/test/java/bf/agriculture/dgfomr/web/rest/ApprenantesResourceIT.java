package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Apprenantes;
import bf.agriculture.dgfomr.repository.ApprenantesRepository;
import bf.agriculture.dgfomr.service.ApprenantesService;

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

/**
 * Integration tests for the {@link ApprenantesResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ApprenantesResourceIT {

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEXE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISCANDIDAT = false;
    private static final Boolean UPDATED_ISCANDIDAT = true;

    private static final String DEFAULT_SITUATION_MATRIMONIAL = "AAAAAAAAAA";
    private static final String UPDATED_SITUATION_MATRIMONIAL = "BBBBBBBBBB";

    private static final String DEFAULT_CHARGER = "AAAAAAAAAA";
    private static final String UPDATED_CHARGER = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALITE = "AAAAAAAAAA";
    private static final String UPDATED_LOCALITE = "BBBBBBBBBB";

    @Autowired
    private ApprenantesRepository apprenantesRepository;

    @Autowired
    private ApprenantesService apprenantesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApprenantesMockMvc;

    private Apprenantes apprenantes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apprenantes createEntity(EntityManager em) {
        Apprenantes apprenantes = new Apprenantes()
            .matricule(DEFAULT_MATRICULE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .sexe(DEFAULT_SEXE)
            .contact(DEFAULT_CONTACT)
            .iscandidat(DEFAULT_ISCANDIDAT)
            .situationMatrimonial(DEFAULT_SITUATION_MATRIMONIAL)
            .charger(DEFAULT_CHARGER)
            .localite(DEFAULT_LOCALITE);
        return apprenantes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apprenantes createUpdatedEntity(EntityManager em) {
        Apprenantes apprenantes = new Apprenantes()
            .matricule(UPDATED_MATRICULE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .sexe(UPDATED_SEXE)
            .contact(UPDATED_CONTACT)
            .iscandidat(UPDATED_ISCANDIDAT)
            .situationMatrimonial(UPDATED_SITUATION_MATRIMONIAL)
            .charger(UPDATED_CHARGER)
            .localite(UPDATED_LOCALITE);
        return apprenantes;
    }

    @BeforeEach
    public void initTest() {
        apprenantes = createEntity(em);
    }

    @Test
    @Transactional
    public void createApprenantes() throws Exception {
        int databaseSizeBeforeCreate = apprenantesRepository.findAll().size();
        // Create the Apprenantes
        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isCreated());

        // Validate the Apprenantes in the database
        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeCreate + 1);
        Apprenantes testApprenantes = apprenantesList.get(apprenantesList.size() - 1);
        assertThat(testApprenantes.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testApprenantes.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testApprenantes.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testApprenantes.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testApprenantes.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testApprenantes.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testApprenantes.isIscandidat()).isEqualTo(DEFAULT_ISCANDIDAT);
        assertThat(testApprenantes.getSituationMatrimonial()).isEqualTo(DEFAULT_SITUATION_MATRIMONIAL);
        assertThat(testApprenantes.getCharger()).isEqualTo(DEFAULT_CHARGER);
        assertThat(testApprenantes.getLocalite()).isEqualTo(DEFAULT_LOCALITE);
    }

    @Test
    @Transactional
    public void createApprenantesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apprenantesRepository.findAll().size();

        // Create the Apprenantes with an existing ID
        apprenantes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        // Validate the Apprenantes in the database
        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMatriculeIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantesRepository.findAll().size();
        // set the field null
        apprenantes.setMatricule(null);

        // Create the Apprenantes, which fails.


        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantesRepository.findAll().size();
        // set the field null
        apprenantes.setNom(null);

        // Create the Apprenantes, which fails.


        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantesRepository.findAll().size();
        // set the field null
        apprenantes.setPrenom(null);

        // Create the Apprenantes, which fails.


        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateNaissanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantesRepository.findAll().size();
        // set the field null
        apprenantes.setDateNaissance(null);

        // Create the Apprenantes, which fails.


        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexeIsRequired() throws Exception {
        int databaseSizeBeforeTest = apprenantesRepository.findAll().size();
        // set the field null
        apprenantes.setSexe(null);

        // Create the Apprenantes, which fails.


        restApprenantesMockMvc.perform(post("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApprenantes() throws Exception {
        // Initialize the database
        apprenantesRepository.saveAndFlush(apprenantes);

        // Get all the apprenantesList
        restApprenantesMockMvc.perform(get("/api/apprenantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apprenantes.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].iscandidat").value(hasItem(DEFAULT_ISCANDIDAT.booleanValue())))
            .andExpect(jsonPath("$.[*].situationMatrimonial").value(hasItem(DEFAULT_SITUATION_MATRIMONIAL)))
            .andExpect(jsonPath("$.[*].charger").value(hasItem(DEFAULT_CHARGER)))
            .andExpect(jsonPath("$.[*].localite").value(hasItem(DEFAULT_LOCALITE)));
    }
    
    @Test
    @Transactional
    public void getApprenantes() throws Exception {
        // Initialize the database
        apprenantesRepository.saveAndFlush(apprenantes);

        // Get the apprenantes
        restApprenantesMockMvc.perform(get("/api/apprenantes/{id}", apprenantes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apprenantes.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.iscandidat").value(DEFAULT_ISCANDIDAT.booleanValue()))
            .andExpect(jsonPath("$.situationMatrimonial").value(DEFAULT_SITUATION_MATRIMONIAL))
            .andExpect(jsonPath("$.charger").value(DEFAULT_CHARGER))
            .andExpect(jsonPath("$.localite").value(DEFAULT_LOCALITE));
    }
    @Test
    @Transactional
    public void getNonExistingApprenantes() throws Exception {
        // Get the apprenantes
        restApprenantesMockMvc.perform(get("/api/apprenantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprenantes() throws Exception {
        // Initialize the database
        apprenantesService.save(apprenantes);

        int databaseSizeBeforeUpdate = apprenantesRepository.findAll().size();

        // Update the apprenantes
        Apprenantes updatedApprenantes = apprenantesRepository.findById(apprenantes.getId()).get();
        // Disconnect from session so that the updates on updatedApprenantes are not directly saved in db
        em.detach(updatedApprenantes);
        updatedApprenantes
            .matricule(UPDATED_MATRICULE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .sexe(UPDATED_SEXE)
            .contact(UPDATED_CONTACT)
            .iscandidat(UPDATED_ISCANDIDAT)
            .situationMatrimonial(UPDATED_SITUATION_MATRIMONIAL)
            .charger(UPDATED_CHARGER)
            .localite(UPDATED_LOCALITE);

        restApprenantesMockMvc.perform(put("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedApprenantes)))
            .andExpect(status().isOk());

        // Validate the Apprenantes in the database
        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeUpdate);
        Apprenantes testApprenantes = apprenantesList.get(apprenantesList.size() - 1);
        assertThat(testApprenantes.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testApprenantes.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testApprenantes.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testApprenantes.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testApprenantes.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testApprenantes.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testApprenantes.isIscandidat()).isEqualTo(UPDATED_ISCANDIDAT);
        assertThat(testApprenantes.getSituationMatrimonial()).isEqualTo(UPDATED_SITUATION_MATRIMONIAL);
        assertThat(testApprenantes.getCharger()).isEqualTo(UPDATED_CHARGER);
        assertThat(testApprenantes.getLocalite()).isEqualTo(UPDATED_LOCALITE);
    }

    @Test
    @Transactional
    public void updateNonExistingApprenantes() throws Exception {
        int databaseSizeBeforeUpdate = apprenantesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApprenantesMockMvc.perform(put("/api/apprenantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(apprenantes)))
            .andExpect(status().isBadRequest());

        // Validate the Apprenantes in the database
        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApprenantes() throws Exception {
        // Initialize the database
        apprenantesService.save(apprenantes);

        int databaseSizeBeforeDelete = apprenantesRepository.findAll().size();

        // Delete the apprenantes
        restApprenantesMockMvc.perform(delete("/api/apprenantes/{id}", apprenantes.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Apprenantes> apprenantesList = apprenantesRepository.findAll();
        assertThat(apprenantesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
