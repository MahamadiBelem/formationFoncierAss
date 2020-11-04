package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.DossierApfr;
import bf.agriculture.dgfomr.repository.DossierApfrRepository;
import bf.agriculture.dgfomr.service.DossierApfrService;

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

import bf.agriculture.dgfomr.domain.enumeration.TrancheAge;
/**
 * Integration tests for the {@link DossierApfrResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DossierApfrResourceIT {

    private static final String DEFAULT_NUM_APFR = "AAAAAAAAAA";
    private static final String UPDATED_NUM_APFR = "BBBBBBBBBB";

    private static final TrancheAge DEFAULT_TRANCHE_AGE = TrancheAge.Vieux;
    private static final TrancheAge UPDATED_TRANCHE_AGE = TrancheAge.Jeune;

    private static final LocalDate DEFAULT_DATE_RECEPTION_CFV = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEPTION_CFV = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RETOUR_CT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RETOUR_CT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ENREGISTREMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENREGISTREMENT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_DEPOT_ST = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEPOT_ST = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RETOUR_ST = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RETOUR_ST = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_NOTIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NOTIFICATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_SIGNATURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_SIGNATURE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TAXES_TOTALE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TAXES_TOTALE = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_DON_COMMUNE = 1F;
    private static final Float UPDATED_DON_COMMUNE = 2F;

    private static final Float DEFAULT_SUPERFICIE_SECURISE = 1F;
    private static final Float UPDATED_SUPERFICIE_SECURISE = 2F;

    private static final LocalDate DEFAULT_DATE_CONSTATATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CONSTATATION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DossierApfrRepository dossierApfrRepository;

    @Autowired
    private DossierApfrService dossierApfrService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDossierApfrMockMvc;

    private DossierApfr dossierApfr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DossierApfr createEntity(EntityManager em) {
        DossierApfr dossierApfr = new DossierApfr()
            .numApfr(DEFAULT_NUM_APFR)
            .trancheAge(DEFAULT_TRANCHE_AGE)
            .dateReceptionCfv(DEFAULT_DATE_RECEPTION_CFV)
            .dateRetourCt(DEFAULT_DATE_RETOUR_CT)
            .dateEnregistrement(DEFAULT_DATE_ENREGISTREMENT)
            .dateDepotSt(DEFAULT_DATE_DEPOT_ST)
            .dateRetourSt(DEFAULT_DATE_RETOUR_ST)
            .dateNotification(DEFAULT_DATE_NOTIFICATION)
            .dateSignature(DEFAULT_DATE_SIGNATURE)
            .taxesTotale(DEFAULT_TAXES_TOTALE)
            .donCommune(DEFAULT_DON_COMMUNE)
            .superficieSecurise(DEFAULT_SUPERFICIE_SECURISE)
            .dateConstatation(DEFAULT_DATE_CONSTATATION);
        return dossierApfr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DossierApfr createUpdatedEntity(EntityManager em) {
        DossierApfr dossierApfr = new DossierApfr()
            .numApfr(UPDATED_NUM_APFR)
            .trancheAge(UPDATED_TRANCHE_AGE)
            .dateReceptionCfv(UPDATED_DATE_RECEPTION_CFV)
            .dateRetourCt(UPDATED_DATE_RETOUR_CT)
            .dateEnregistrement(UPDATED_DATE_ENREGISTREMENT)
            .dateDepotSt(UPDATED_DATE_DEPOT_ST)
            .dateRetourSt(UPDATED_DATE_RETOUR_ST)
            .dateNotification(UPDATED_DATE_NOTIFICATION)
            .dateSignature(UPDATED_DATE_SIGNATURE)
            .taxesTotale(UPDATED_TAXES_TOTALE)
            .donCommune(UPDATED_DON_COMMUNE)
            .superficieSecurise(UPDATED_SUPERFICIE_SECURISE)
            .dateConstatation(UPDATED_DATE_CONSTATATION);
        return dossierApfr;
    }

    @BeforeEach
    public void initTest() {
        dossierApfr = createEntity(em);
    }

    @Test
    @Transactional
    public void createDossierApfr() throws Exception {
        int databaseSizeBeforeCreate = dossierApfrRepository.findAll().size();
        // Create the DossierApfr
        restDossierApfrMockMvc.perform(post("/api/dossier-apfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dossierApfr)))
            .andExpect(status().isCreated());

        // Validate the DossierApfr in the database
        List<DossierApfr> dossierApfrList = dossierApfrRepository.findAll();
        assertThat(dossierApfrList).hasSize(databaseSizeBeforeCreate + 1);
        DossierApfr testDossierApfr = dossierApfrList.get(dossierApfrList.size() - 1);
        assertThat(testDossierApfr.getNumApfr()).isEqualTo(DEFAULT_NUM_APFR);
        assertThat(testDossierApfr.getTrancheAge()).isEqualTo(DEFAULT_TRANCHE_AGE);
        assertThat(testDossierApfr.getDateReceptionCfv()).isEqualTo(DEFAULT_DATE_RECEPTION_CFV);
        assertThat(testDossierApfr.getDateRetourCt()).isEqualTo(DEFAULT_DATE_RETOUR_CT);
        assertThat(testDossierApfr.getDateEnregistrement()).isEqualTo(DEFAULT_DATE_ENREGISTREMENT);
        assertThat(testDossierApfr.getDateDepotSt()).isEqualTo(DEFAULT_DATE_DEPOT_ST);
        assertThat(testDossierApfr.getDateRetourSt()).isEqualTo(DEFAULT_DATE_RETOUR_ST);
        assertThat(testDossierApfr.getDateNotification()).isEqualTo(DEFAULT_DATE_NOTIFICATION);
        assertThat(testDossierApfr.getDateSignature()).isEqualTo(DEFAULT_DATE_SIGNATURE);
        assertThat(testDossierApfr.getTaxesTotale()).isEqualTo(DEFAULT_TAXES_TOTALE);
        assertThat(testDossierApfr.getDonCommune()).isEqualTo(DEFAULT_DON_COMMUNE);
        assertThat(testDossierApfr.getSuperficieSecurise()).isEqualTo(DEFAULT_SUPERFICIE_SECURISE);
        assertThat(testDossierApfr.getDateConstatation()).isEqualTo(DEFAULT_DATE_CONSTATATION);
    }

    @Test
    @Transactional
    public void createDossierApfrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dossierApfrRepository.findAll().size();

        // Create the DossierApfr with an existing ID
        dossierApfr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDossierApfrMockMvc.perform(post("/api/dossier-apfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dossierApfr)))
            .andExpect(status().isBadRequest());

        // Validate the DossierApfr in the database
        List<DossierApfr> dossierApfrList = dossierApfrRepository.findAll();
        assertThat(dossierApfrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDossierApfrs() throws Exception {
        // Initialize the database
        dossierApfrRepository.saveAndFlush(dossierApfr);

        // Get all the dossierApfrList
        restDossierApfrMockMvc.perform(get("/api/dossier-apfrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossierApfr.getId().intValue())))
            .andExpect(jsonPath("$.[*].numApfr").value(hasItem(DEFAULT_NUM_APFR)))
            .andExpect(jsonPath("$.[*].trancheAge").value(hasItem(DEFAULT_TRANCHE_AGE.toString())))
            .andExpect(jsonPath("$.[*].dateReceptionCfv").value(hasItem(DEFAULT_DATE_RECEPTION_CFV.toString())))
            .andExpect(jsonPath("$.[*].dateRetourCt").value(hasItem(DEFAULT_DATE_RETOUR_CT.toString())))
            .andExpect(jsonPath("$.[*].dateEnregistrement").value(hasItem(DEFAULT_DATE_ENREGISTREMENT.toString())))
            .andExpect(jsonPath("$.[*].dateDepotSt").value(hasItem(DEFAULT_DATE_DEPOT_ST.toString())))
            .andExpect(jsonPath("$.[*].dateRetourSt").value(hasItem(DEFAULT_DATE_RETOUR_ST.toString())))
            .andExpect(jsonPath("$.[*].dateNotification").value(hasItem(DEFAULT_DATE_NOTIFICATION.toString())))
            .andExpect(jsonPath("$.[*].dateSignature").value(hasItem(DEFAULT_DATE_SIGNATURE.toString())))
            .andExpect(jsonPath("$.[*].taxesTotale").value(hasItem(DEFAULT_TAXES_TOTALE.toString())))
            .andExpect(jsonPath("$.[*].donCommune").value(hasItem(DEFAULT_DON_COMMUNE.doubleValue())))
            .andExpect(jsonPath("$.[*].superficieSecurise").value(hasItem(DEFAULT_SUPERFICIE_SECURISE.doubleValue())))
            .andExpect(jsonPath("$.[*].dateConstatation").value(hasItem(DEFAULT_DATE_CONSTATATION.toString())));
    }
    
    @Test
    @Transactional
    public void getDossierApfr() throws Exception {
        // Initialize the database
        dossierApfrRepository.saveAndFlush(dossierApfr);

        // Get the dossierApfr
        restDossierApfrMockMvc.perform(get("/api/dossier-apfrs/{id}", dossierApfr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dossierApfr.getId().intValue()))
            .andExpect(jsonPath("$.numApfr").value(DEFAULT_NUM_APFR))
            .andExpect(jsonPath("$.trancheAge").value(DEFAULT_TRANCHE_AGE.toString()))
            .andExpect(jsonPath("$.dateReceptionCfv").value(DEFAULT_DATE_RECEPTION_CFV.toString()))
            .andExpect(jsonPath("$.dateRetourCt").value(DEFAULT_DATE_RETOUR_CT.toString()))
            .andExpect(jsonPath("$.dateEnregistrement").value(DEFAULT_DATE_ENREGISTREMENT.toString()))
            .andExpect(jsonPath("$.dateDepotSt").value(DEFAULT_DATE_DEPOT_ST.toString()))
            .andExpect(jsonPath("$.dateRetourSt").value(DEFAULT_DATE_RETOUR_ST.toString()))
            .andExpect(jsonPath("$.dateNotification").value(DEFAULT_DATE_NOTIFICATION.toString()))
            .andExpect(jsonPath("$.dateSignature").value(DEFAULT_DATE_SIGNATURE.toString()))
            .andExpect(jsonPath("$.taxesTotale").value(DEFAULT_TAXES_TOTALE.toString()))
            .andExpect(jsonPath("$.donCommune").value(DEFAULT_DON_COMMUNE.doubleValue()))
            .andExpect(jsonPath("$.superficieSecurise").value(DEFAULT_SUPERFICIE_SECURISE.doubleValue()))
            .andExpect(jsonPath("$.dateConstatation").value(DEFAULT_DATE_CONSTATATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDossierApfr() throws Exception {
        // Get the dossierApfr
        restDossierApfrMockMvc.perform(get("/api/dossier-apfrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDossierApfr() throws Exception {
        // Initialize the database
        dossierApfrService.save(dossierApfr);

        int databaseSizeBeforeUpdate = dossierApfrRepository.findAll().size();

        // Update the dossierApfr
        DossierApfr updatedDossierApfr = dossierApfrRepository.findById(dossierApfr.getId()).get();
        // Disconnect from session so that the updates on updatedDossierApfr are not directly saved in db
        em.detach(updatedDossierApfr);
        updatedDossierApfr
            .numApfr(UPDATED_NUM_APFR)
            .trancheAge(UPDATED_TRANCHE_AGE)
            .dateReceptionCfv(UPDATED_DATE_RECEPTION_CFV)
            .dateRetourCt(UPDATED_DATE_RETOUR_CT)
            .dateEnregistrement(UPDATED_DATE_ENREGISTREMENT)
            .dateDepotSt(UPDATED_DATE_DEPOT_ST)
            .dateRetourSt(UPDATED_DATE_RETOUR_ST)
            .dateNotification(UPDATED_DATE_NOTIFICATION)
            .dateSignature(UPDATED_DATE_SIGNATURE)
            .taxesTotale(UPDATED_TAXES_TOTALE)
            .donCommune(UPDATED_DON_COMMUNE)
            .superficieSecurise(UPDATED_SUPERFICIE_SECURISE)
            .dateConstatation(UPDATED_DATE_CONSTATATION);

        restDossierApfrMockMvc.perform(put("/api/dossier-apfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDossierApfr)))
            .andExpect(status().isOk());

        // Validate the DossierApfr in the database
        List<DossierApfr> dossierApfrList = dossierApfrRepository.findAll();
        assertThat(dossierApfrList).hasSize(databaseSizeBeforeUpdate);
        DossierApfr testDossierApfr = dossierApfrList.get(dossierApfrList.size() - 1);
        assertThat(testDossierApfr.getNumApfr()).isEqualTo(UPDATED_NUM_APFR);
        assertThat(testDossierApfr.getTrancheAge()).isEqualTo(UPDATED_TRANCHE_AGE);
        assertThat(testDossierApfr.getDateReceptionCfv()).isEqualTo(UPDATED_DATE_RECEPTION_CFV);
        assertThat(testDossierApfr.getDateRetourCt()).isEqualTo(UPDATED_DATE_RETOUR_CT);
        assertThat(testDossierApfr.getDateEnregistrement()).isEqualTo(UPDATED_DATE_ENREGISTREMENT);
        assertThat(testDossierApfr.getDateDepotSt()).isEqualTo(UPDATED_DATE_DEPOT_ST);
        assertThat(testDossierApfr.getDateRetourSt()).isEqualTo(UPDATED_DATE_RETOUR_ST);
        assertThat(testDossierApfr.getDateNotification()).isEqualTo(UPDATED_DATE_NOTIFICATION);
        assertThat(testDossierApfr.getDateSignature()).isEqualTo(UPDATED_DATE_SIGNATURE);
        assertThat(testDossierApfr.getTaxesTotale()).isEqualTo(UPDATED_TAXES_TOTALE);
        assertThat(testDossierApfr.getDonCommune()).isEqualTo(UPDATED_DON_COMMUNE);
        assertThat(testDossierApfr.getSuperficieSecurise()).isEqualTo(UPDATED_SUPERFICIE_SECURISE);
        assertThat(testDossierApfr.getDateConstatation()).isEqualTo(UPDATED_DATE_CONSTATATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDossierApfr() throws Exception {
        int databaseSizeBeforeUpdate = dossierApfrRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDossierApfrMockMvc.perform(put("/api/dossier-apfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dossierApfr)))
            .andExpect(status().isBadRequest());

        // Validate the DossierApfr in the database
        List<DossierApfr> dossierApfrList = dossierApfrRepository.findAll();
        assertThat(dossierApfrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDossierApfr() throws Exception {
        // Initialize the database
        dossierApfrService.save(dossierApfr);

        int databaseSizeBeforeDelete = dossierApfrRepository.findAll().size();

        // Delete the dossierApfr
        restDossierApfrMockMvc.perform(delete("/api/dossier-apfrs/{id}", dossierApfr.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DossierApfr> dossierApfrList = dossierApfrRepository.findAll();
        assertThat(dossierApfrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
