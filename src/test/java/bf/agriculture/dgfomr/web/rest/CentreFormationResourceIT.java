package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.CentreFormation;
import bf.agriculture.dgfomr.repository.CentreFormationRepository;
import bf.agriculture.dgfomr.service.CentreFormationService;

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
 * Integration tests for the {@link CentreFormationResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CentreFormationResourceIT {

    private static final String DEFAULT_DENOMINATION = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCALISATION = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUTS = "AAAAAAAAAA";
    private static final String UPDATED_STATUTS = "BBBBBBBBBB";

    private static final String DEFAULT_CAPACITEACCEUIL = "AAAAAAAAAA";
    private static final String UPDATED_CAPACITEACCEUIL = "BBBBBBBBBB";

    private static final String DEFAULT_REF_OUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_REF_OUVERTURE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OUVERTURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OUVERTURE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REGIME = "AAAAAAAAAA";
    private static final String UPDATED_REGIME = "BBBBBBBBBB";

    @Autowired
    private CentreFormationRepository centreFormationRepository;

    @Mock
    private CentreFormationRepository centreFormationRepositoryMock;

    @Mock
    private CentreFormationService centreFormationServiceMock;

    @Autowired
    private CentreFormationService centreFormationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCentreFormationMockMvc;

    private CentreFormation centreFormation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentreFormation createEntity(EntityManager em) {
        CentreFormation centreFormation = new CentreFormation()
            .denomination(DEFAULT_DENOMINATION)
            .localisation(DEFAULT_LOCALISATION)
            .adresse(DEFAULT_ADRESSE)
            .statuts(DEFAULT_STATUTS)
            .capaciteacceuil(DEFAULT_CAPACITEACCEUIL)
            .refOuverture(DEFAULT_REF_OUVERTURE)
            .dateOuverture(DEFAULT_DATE_OUVERTURE)
            .regime(DEFAULT_REGIME);
        return centreFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentreFormation createUpdatedEntity(EntityManager em) {
        CentreFormation centreFormation = new CentreFormation()
            .denomination(UPDATED_DENOMINATION)
            .localisation(UPDATED_LOCALISATION)
            .adresse(UPDATED_ADRESSE)
            .statuts(UPDATED_STATUTS)
            .capaciteacceuil(UPDATED_CAPACITEACCEUIL)
            .refOuverture(UPDATED_REF_OUVERTURE)
            .dateOuverture(UPDATED_DATE_OUVERTURE)
            .regime(UPDATED_REGIME);
        return centreFormation;
    }

    @BeforeEach
    public void initTest() {
        centreFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createCentreFormation() throws Exception {
        int databaseSizeBeforeCreate = centreFormationRepository.findAll().size();
        // Create the CentreFormation
        restCentreFormationMockMvc.perform(post("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreFormation)))
            .andExpect(status().isCreated());

        // Validate the CentreFormation in the database
        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeCreate + 1);
        CentreFormation testCentreFormation = centreFormationList.get(centreFormationList.size() - 1);
        assertThat(testCentreFormation.getDenomination()).isEqualTo(DEFAULT_DENOMINATION);
        assertThat(testCentreFormation.getLocalisation()).isEqualTo(DEFAULT_LOCALISATION);
        assertThat(testCentreFormation.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testCentreFormation.getStatuts()).isEqualTo(DEFAULT_STATUTS);
        assertThat(testCentreFormation.getCapaciteacceuil()).isEqualTo(DEFAULT_CAPACITEACCEUIL);
        assertThat(testCentreFormation.getRefOuverture()).isEqualTo(DEFAULT_REF_OUVERTURE);
        assertThat(testCentreFormation.getDateOuverture()).isEqualTo(DEFAULT_DATE_OUVERTURE);
        assertThat(testCentreFormation.getRegime()).isEqualTo(DEFAULT_REGIME);
    }

    @Test
    @Transactional
    public void createCentreFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = centreFormationRepository.findAll().size();

        // Create the CentreFormation with an existing ID
        centreFormation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCentreFormationMockMvc.perform(post("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreFormation)))
            .andExpect(status().isBadRequest());

        // Validate the CentreFormation in the database
        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDenominationIsRequired() throws Exception {
        int databaseSizeBeforeTest = centreFormationRepository.findAll().size();
        // set the field null
        centreFormation.setDenomination(null);

        // Create the CentreFormation, which fails.


        restCentreFormationMockMvc.perform(post("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreFormation)))
            .andExpect(status().isBadRequest());

        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalisationIsRequired() throws Exception {
        int databaseSizeBeforeTest = centreFormationRepository.findAll().size();
        // set the field null
        centreFormation.setLocalisation(null);

        // Create the CentreFormation, which fails.


        restCentreFormationMockMvc.perform(post("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreFormation)))
            .andExpect(status().isBadRequest());

        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = centreFormationRepository.findAll().size();
        // set the field null
        centreFormation.setAdresse(null);

        // Create the CentreFormation, which fails.


        restCentreFormationMockMvc.perform(post("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreFormation)))
            .andExpect(status().isBadRequest());

        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCentreFormations() throws Exception {
        // Initialize the database
        centreFormationRepository.saveAndFlush(centreFormation);

        // Get all the centreFormationList
        restCentreFormationMockMvc.perform(get("/api/centre-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(centreFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].denomination").value(hasItem(DEFAULT_DENOMINATION)))
            .andExpect(jsonPath("$.[*].localisation").value(hasItem(DEFAULT_LOCALISATION)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].statuts").value(hasItem(DEFAULT_STATUTS)))
            .andExpect(jsonPath("$.[*].capaciteacceuil").value(hasItem(DEFAULT_CAPACITEACCEUIL)))
            .andExpect(jsonPath("$.[*].refOuverture").value(hasItem(DEFAULT_REF_OUVERTURE)))
            .andExpect(jsonPath("$.[*].dateOuverture").value(hasItem(DEFAULT_DATE_OUVERTURE.toString())))
            .andExpect(jsonPath("$.[*].regime").value(hasItem(DEFAULT_REGIME)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCentreFormationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(centreFormationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCentreFormationMockMvc.perform(get("/api/centre-formations?eagerload=true"))
            .andExpect(status().isOk());

        verify(centreFormationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCentreFormationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(centreFormationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCentreFormationMockMvc.perform(get("/api/centre-formations?eagerload=true"))
            .andExpect(status().isOk());

        verify(centreFormationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCentreFormation() throws Exception {
        // Initialize the database
        centreFormationRepository.saveAndFlush(centreFormation);

        // Get the centreFormation
        restCentreFormationMockMvc.perform(get("/api/centre-formations/{id}", centreFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(centreFormation.getId().intValue()))
            .andExpect(jsonPath("$.denomination").value(DEFAULT_DENOMINATION))
            .andExpect(jsonPath("$.localisation").value(DEFAULT_LOCALISATION))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.statuts").value(DEFAULT_STATUTS))
            .andExpect(jsonPath("$.capaciteacceuil").value(DEFAULT_CAPACITEACCEUIL))
            .andExpect(jsonPath("$.refOuverture").value(DEFAULT_REF_OUVERTURE))
            .andExpect(jsonPath("$.dateOuverture").value(DEFAULT_DATE_OUVERTURE.toString()))
            .andExpect(jsonPath("$.regime").value(DEFAULT_REGIME));
    }
    @Test
    @Transactional
    public void getNonExistingCentreFormation() throws Exception {
        // Get the centreFormation
        restCentreFormationMockMvc.perform(get("/api/centre-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCentreFormation() throws Exception {
        // Initialize the database
        centreFormationService.save(centreFormation);

        int databaseSizeBeforeUpdate = centreFormationRepository.findAll().size();

        // Update the centreFormation
        CentreFormation updatedCentreFormation = centreFormationRepository.findById(centreFormation.getId()).get();
        // Disconnect from session so that the updates on updatedCentreFormation are not directly saved in db
        em.detach(updatedCentreFormation);
        updatedCentreFormation
            .denomination(UPDATED_DENOMINATION)
            .localisation(UPDATED_LOCALISATION)
            .adresse(UPDATED_ADRESSE)
            .statuts(UPDATED_STATUTS)
            .capaciteacceuil(UPDATED_CAPACITEACCEUIL)
            .refOuverture(UPDATED_REF_OUVERTURE)
            .dateOuverture(UPDATED_DATE_OUVERTURE)
            .regime(UPDATED_REGIME);

        restCentreFormationMockMvc.perform(put("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCentreFormation)))
            .andExpect(status().isOk());

        // Validate the CentreFormation in the database
        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeUpdate);
        CentreFormation testCentreFormation = centreFormationList.get(centreFormationList.size() - 1);
        assertThat(testCentreFormation.getDenomination()).isEqualTo(UPDATED_DENOMINATION);
        assertThat(testCentreFormation.getLocalisation()).isEqualTo(UPDATED_LOCALISATION);
        assertThat(testCentreFormation.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testCentreFormation.getStatuts()).isEqualTo(UPDATED_STATUTS);
        assertThat(testCentreFormation.getCapaciteacceuil()).isEqualTo(UPDATED_CAPACITEACCEUIL);
        assertThat(testCentreFormation.getRefOuverture()).isEqualTo(UPDATED_REF_OUVERTURE);
        assertThat(testCentreFormation.getDateOuverture()).isEqualTo(UPDATED_DATE_OUVERTURE);
        assertThat(testCentreFormation.getRegime()).isEqualTo(UPDATED_REGIME);
    }

    @Test
    @Transactional
    public void updateNonExistingCentreFormation() throws Exception {
        int databaseSizeBeforeUpdate = centreFormationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentreFormationMockMvc.perform(put("/api/centre-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(centreFormation)))
            .andExpect(status().isBadRequest());

        // Validate the CentreFormation in the database
        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCentreFormation() throws Exception {
        // Initialize the database
        centreFormationService.save(centreFormation);

        int databaseSizeBeforeDelete = centreFormationRepository.findAll().size();

        // Delete the centreFormation
        restCentreFormationMockMvc.perform(delete("/api/centre-formations/{id}", centreFormation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CentreFormation> centreFormationList = centreFormationRepository.findAll();
        assertThat(centreFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
