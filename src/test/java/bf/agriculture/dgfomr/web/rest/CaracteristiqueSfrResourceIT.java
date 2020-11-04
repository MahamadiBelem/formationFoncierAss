package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.CaracteristiqueSfr;
import bf.agriculture.dgfomr.repository.CaracteristiqueSfrRepository;
import bf.agriculture.dgfomr.service.CaracteristiqueSfrService;

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
 * Integration tests for the {@link CaracteristiqueSfrResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaracteristiqueSfrResourceIT {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Integer DEFAULT_NBR_AGENT = 1;
    private static final Integer UPDATED_NBR_AGENT = 2;

    private static final Boolean DEFAULT_EQUIPEMENT_INFORMATIQUE = false;
    private static final Boolean UPDATED_EQUIPEMENT_INFORMATIQUE = true;

    private static final Boolean DEFAULT_EQUIPEMENT_CARTOGRAPHIQUE = false;
    private static final Boolean UPDATED_EQUIPEMENT_CARTOGRAPHIQUE = true;

    private static final Boolean DEFAULT_ACCES_INTERNET = false;
    private static final Boolean UPDATED_ACCES_INTERNET = true;

    private static final Boolean DEFAULT_EQUIPEMEMENT_VEHICULE = false;
    private static final Boolean UPDATED_EQUIPEMEMENT_VEHICULE = true;

    @Autowired
    private CaracteristiqueSfrRepository caracteristiqueSfrRepository;

    @Autowired
    private CaracteristiqueSfrService caracteristiqueSfrService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaracteristiqueSfrMockMvc;

    private CaracteristiqueSfr caracteristiqueSfr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaracteristiqueSfr createEntity(EntityManager em) {
        CaracteristiqueSfr caracteristiqueSfr = new CaracteristiqueSfr()
            .annee(DEFAULT_ANNEE)
            .nbrAgent(DEFAULT_NBR_AGENT)
            .equipementInformatique(DEFAULT_EQUIPEMENT_INFORMATIQUE)
            .equipementCartographique(DEFAULT_EQUIPEMENT_CARTOGRAPHIQUE)
            .accesInternet(DEFAULT_ACCES_INTERNET)
            .equipemementVehicule(DEFAULT_EQUIPEMEMENT_VEHICULE);
        return caracteristiqueSfr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaracteristiqueSfr createUpdatedEntity(EntityManager em) {
        CaracteristiqueSfr caracteristiqueSfr = new CaracteristiqueSfr()
            .annee(UPDATED_ANNEE)
            .nbrAgent(UPDATED_NBR_AGENT)
            .equipementInformatique(UPDATED_EQUIPEMENT_INFORMATIQUE)
            .equipementCartographique(UPDATED_EQUIPEMENT_CARTOGRAPHIQUE)
            .accesInternet(UPDATED_ACCES_INTERNET)
            .equipemementVehicule(UPDATED_EQUIPEMEMENT_VEHICULE);
        return caracteristiqueSfr;
    }

    @BeforeEach
    public void initTest() {
        caracteristiqueSfr = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaracteristiqueSfr() throws Exception {
        int databaseSizeBeforeCreate = caracteristiqueSfrRepository.findAll().size();
        // Create the CaracteristiqueSfr
        restCaracteristiqueSfrMockMvc.perform(post("/api/caracteristique-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueSfr)))
            .andExpect(status().isCreated());

        // Validate the CaracteristiqueSfr in the database
        List<CaracteristiqueSfr> caracteristiqueSfrList = caracteristiqueSfrRepository.findAll();
        assertThat(caracteristiqueSfrList).hasSize(databaseSizeBeforeCreate + 1);
        CaracteristiqueSfr testCaracteristiqueSfr = caracteristiqueSfrList.get(caracteristiqueSfrList.size() - 1);
        assertThat(testCaracteristiqueSfr.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testCaracteristiqueSfr.getNbrAgent()).isEqualTo(DEFAULT_NBR_AGENT);
        assertThat(testCaracteristiqueSfr.isEquipementInformatique()).isEqualTo(DEFAULT_EQUIPEMENT_INFORMATIQUE);
        assertThat(testCaracteristiqueSfr.isEquipementCartographique()).isEqualTo(DEFAULT_EQUIPEMENT_CARTOGRAPHIQUE);
        assertThat(testCaracteristiqueSfr.isAccesInternet()).isEqualTo(DEFAULT_ACCES_INTERNET);
        assertThat(testCaracteristiqueSfr.isEquipemementVehicule()).isEqualTo(DEFAULT_EQUIPEMEMENT_VEHICULE);
    }

    @Test
    @Transactional
    public void createCaracteristiqueSfrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caracteristiqueSfrRepository.findAll().size();

        // Create the CaracteristiqueSfr with an existing ID
        caracteristiqueSfr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaracteristiqueSfrMockMvc.perform(post("/api/caracteristique-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueSfr)))
            .andExpect(status().isBadRequest());

        // Validate the CaracteristiqueSfr in the database
        List<CaracteristiqueSfr> caracteristiqueSfrList = caracteristiqueSfrRepository.findAll();
        assertThat(caracteristiqueSfrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaracteristiqueSfrs() throws Exception {
        // Initialize the database
        caracteristiqueSfrRepository.saveAndFlush(caracteristiqueSfr);

        // Get all the caracteristiqueSfrList
        restCaracteristiqueSfrMockMvc.perform(get("/api/caracteristique-sfrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caracteristiqueSfr.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].nbrAgent").value(hasItem(DEFAULT_NBR_AGENT)))
            .andExpect(jsonPath("$.[*].equipementInformatique").value(hasItem(DEFAULT_EQUIPEMENT_INFORMATIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].equipementCartographique").value(hasItem(DEFAULT_EQUIPEMENT_CARTOGRAPHIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].accesInternet").value(hasItem(DEFAULT_ACCES_INTERNET.booleanValue())))
            .andExpect(jsonPath("$.[*].equipemementVehicule").value(hasItem(DEFAULT_EQUIPEMEMENT_VEHICULE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCaracteristiqueSfr() throws Exception {
        // Initialize the database
        caracteristiqueSfrRepository.saveAndFlush(caracteristiqueSfr);

        // Get the caracteristiqueSfr
        restCaracteristiqueSfrMockMvc.perform(get("/api/caracteristique-sfrs/{id}", caracteristiqueSfr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caracteristiqueSfr.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.nbrAgent").value(DEFAULT_NBR_AGENT))
            .andExpect(jsonPath("$.equipementInformatique").value(DEFAULT_EQUIPEMENT_INFORMATIQUE.booleanValue()))
            .andExpect(jsonPath("$.equipementCartographique").value(DEFAULT_EQUIPEMENT_CARTOGRAPHIQUE.booleanValue()))
            .andExpect(jsonPath("$.accesInternet").value(DEFAULT_ACCES_INTERNET.booleanValue()))
            .andExpect(jsonPath("$.equipemementVehicule").value(DEFAULT_EQUIPEMEMENT_VEHICULE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCaracteristiqueSfr() throws Exception {
        // Get the caracteristiqueSfr
        restCaracteristiqueSfrMockMvc.perform(get("/api/caracteristique-sfrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaracteristiqueSfr() throws Exception {
        // Initialize the database
        caracteristiqueSfrService.save(caracteristiqueSfr);

        int databaseSizeBeforeUpdate = caracteristiqueSfrRepository.findAll().size();

        // Update the caracteristiqueSfr
        CaracteristiqueSfr updatedCaracteristiqueSfr = caracteristiqueSfrRepository.findById(caracteristiqueSfr.getId()).get();
        // Disconnect from session so that the updates on updatedCaracteristiqueSfr are not directly saved in db
        em.detach(updatedCaracteristiqueSfr);
        updatedCaracteristiqueSfr
            .annee(UPDATED_ANNEE)
            .nbrAgent(UPDATED_NBR_AGENT)
            .equipementInformatique(UPDATED_EQUIPEMENT_INFORMATIQUE)
            .equipementCartographique(UPDATED_EQUIPEMENT_CARTOGRAPHIQUE)
            .accesInternet(UPDATED_ACCES_INTERNET)
            .equipemementVehicule(UPDATED_EQUIPEMEMENT_VEHICULE);

        restCaracteristiqueSfrMockMvc.perform(put("/api/caracteristique-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaracteristiqueSfr)))
            .andExpect(status().isOk());

        // Validate the CaracteristiqueSfr in the database
        List<CaracteristiqueSfr> caracteristiqueSfrList = caracteristiqueSfrRepository.findAll();
        assertThat(caracteristiqueSfrList).hasSize(databaseSizeBeforeUpdate);
        CaracteristiqueSfr testCaracteristiqueSfr = caracteristiqueSfrList.get(caracteristiqueSfrList.size() - 1);
        assertThat(testCaracteristiqueSfr.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testCaracteristiqueSfr.getNbrAgent()).isEqualTo(UPDATED_NBR_AGENT);
        assertThat(testCaracteristiqueSfr.isEquipementInformatique()).isEqualTo(UPDATED_EQUIPEMENT_INFORMATIQUE);
        assertThat(testCaracteristiqueSfr.isEquipementCartographique()).isEqualTo(UPDATED_EQUIPEMENT_CARTOGRAPHIQUE);
        assertThat(testCaracteristiqueSfr.isAccesInternet()).isEqualTo(UPDATED_ACCES_INTERNET);
        assertThat(testCaracteristiqueSfr.isEquipemementVehicule()).isEqualTo(UPDATED_EQUIPEMEMENT_VEHICULE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaracteristiqueSfr() throws Exception {
        int databaseSizeBeforeUpdate = caracteristiqueSfrRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaracteristiqueSfrMockMvc.perform(put("/api/caracteristique-sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caracteristiqueSfr)))
            .andExpect(status().isBadRequest());

        // Validate the CaracteristiqueSfr in the database
        List<CaracteristiqueSfr> caracteristiqueSfrList = caracteristiqueSfrRepository.findAll();
        assertThat(caracteristiqueSfrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaracteristiqueSfr() throws Exception {
        // Initialize the database
        caracteristiqueSfrService.save(caracteristiqueSfr);

        int databaseSizeBeforeDelete = caracteristiqueSfrRepository.findAll().size();

        // Delete the caracteristiqueSfr
        restCaracteristiqueSfrMockMvc.perform(delete("/api/caracteristique-sfrs/{id}", caracteristiqueSfr.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CaracteristiqueSfr> caracteristiqueSfrList = caracteristiqueSfrRepository.findAll();
        assertThat(caracteristiqueSfrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
