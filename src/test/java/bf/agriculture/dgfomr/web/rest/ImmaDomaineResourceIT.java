package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.ImmaDomaine;
import bf.agriculture.dgfomr.repository.ImmaDomaineRepository;
import bf.agriculture.dgfomr.service.ImmaDomaineService;

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
 * Integration tests for the {@link ImmaDomaineResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ImmaDomaineResourceIT {

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final Float DEFAULT_SUPERFICE_TOT_INVENTORIE = 1F;
    private static final Float UPDATED_SUPERFICE_TOT_INVENTORIE = 2F;

    private static final Float DEFAULT_SUPERFICIE_TOT_IMMATRICULE = 1F;
    private static final Float UPDATED_SUPERFICIE_TOT_IMMATRICULE = 2F;

    private static final Float DEFAULT_SUPERFICIE_TOT_ENCADRE = 1F;
    private static final Float UPDATED_SUPERFICIE_TOT_ENCADRE = 2F;

    @Autowired
    private ImmaDomaineRepository immaDomaineRepository;

    @Autowired
    private ImmaDomaineService immaDomaineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImmaDomaineMockMvc;

    private ImmaDomaine immaDomaine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImmaDomaine createEntity(EntityManager em) {
        ImmaDomaine immaDomaine = new ImmaDomaine()
            .annee(DEFAULT_ANNEE)
            .superficeTotInventorie(DEFAULT_SUPERFICE_TOT_INVENTORIE)
            .superficieTotImmatricule(DEFAULT_SUPERFICIE_TOT_IMMATRICULE)
            .superficieTotEncadre(DEFAULT_SUPERFICIE_TOT_ENCADRE);
        return immaDomaine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImmaDomaine createUpdatedEntity(EntityManager em) {
        ImmaDomaine immaDomaine = new ImmaDomaine()
            .annee(UPDATED_ANNEE)
            .superficeTotInventorie(UPDATED_SUPERFICE_TOT_INVENTORIE)
            .superficieTotImmatricule(UPDATED_SUPERFICIE_TOT_IMMATRICULE)
            .superficieTotEncadre(UPDATED_SUPERFICIE_TOT_ENCADRE);
        return immaDomaine;
    }

    @BeforeEach
    public void initTest() {
        immaDomaine = createEntity(em);
    }

    @Test
    @Transactional
    public void createImmaDomaine() throws Exception {
        int databaseSizeBeforeCreate = immaDomaineRepository.findAll().size();
        // Create the ImmaDomaine
        restImmaDomaineMockMvc.perform(post("/api/imma-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(immaDomaine)))
            .andExpect(status().isCreated());

        // Validate the ImmaDomaine in the database
        List<ImmaDomaine> immaDomaineList = immaDomaineRepository.findAll();
        assertThat(immaDomaineList).hasSize(databaseSizeBeforeCreate + 1);
        ImmaDomaine testImmaDomaine = immaDomaineList.get(immaDomaineList.size() - 1);
        assertThat(testImmaDomaine.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testImmaDomaine.getSuperficeTotInventorie()).isEqualTo(DEFAULT_SUPERFICE_TOT_INVENTORIE);
        assertThat(testImmaDomaine.getSuperficieTotImmatricule()).isEqualTo(DEFAULT_SUPERFICIE_TOT_IMMATRICULE);
        assertThat(testImmaDomaine.getSuperficieTotEncadre()).isEqualTo(DEFAULT_SUPERFICIE_TOT_ENCADRE);
    }

    @Test
    @Transactional
    public void createImmaDomaineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = immaDomaineRepository.findAll().size();

        // Create the ImmaDomaine with an existing ID
        immaDomaine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImmaDomaineMockMvc.perform(post("/api/imma-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(immaDomaine)))
            .andExpect(status().isBadRequest());

        // Validate the ImmaDomaine in the database
        List<ImmaDomaine> immaDomaineList = immaDomaineRepository.findAll();
        assertThat(immaDomaineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImmaDomaines() throws Exception {
        // Initialize the database
        immaDomaineRepository.saveAndFlush(immaDomaine);

        // Get all the immaDomaineList
        restImmaDomaineMockMvc.perform(get("/api/imma-domaines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(immaDomaine.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].superficeTotInventorie").value(hasItem(DEFAULT_SUPERFICE_TOT_INVENTORIE.doubleValue())))
            .andExpect(jsonPath("$.[*].superficieTotImmatricule").value(hasItem(DEFAULT_SUPERFICIE_TOT_IMMATRICULE.doubleValue())))
            .andExpect(jsonPath("$.[*].superficieTotEncadre").value(hasItem(DEFAULT_SUPERFICIE_TOT_ENCADRE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getImmaDomaine() throws Exception {
        // Initialize the database
        immaDomaineRepository.saveAndFlush(immaDomaine);

        // Get the immaDomaine
        restImmaDomaineMockMvc.perform(get("/api/imma-domaines/{id}", immaDomaine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(immaDomaine.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.superficeTotInventorie").value(DEFAULT_SUPERFICE_TOT_INVENTORIE.doubleValue()))
            .andExpect(jsonPath("$.superficieTotImmatricule").value(DEFAULT_SUPERFICIE_TOT_IMMATRICULE.doubleValue()))
            .andExpect(jsonPath("$.superficieTotEncadre").value(DEFAULT_SUPERFICIE_TOT_ENCADRE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingImmaDomaine() throws Exception {
        // Get the immaDomaine
        restImmaDomaineMockMvc.perform(get("/api/imma-domaines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImmaDomaine() throws Exception {
        // Initialize the database
        immaDomaineService.save(immaDomaine);

        int databaseSizeBeforeUpdate = immaDomaineRepository.findAll().size();

        // Update the immaDomaine
        ImmaDomaine updatedImmaDomaine = immaDomaineRepository.findById(immaDomaine.getId()).get();
        // Disconnect from session so that the updates on updatedImmaDomaine are not directly saved in db
        em.detach(updatedImmaDomaine);
        updatedImmaDomaine
            .annee(UPDATED_ANNEE)
            .superficeTotInventorie(UPDATED_SUPERFICE_TOT_INVENTORIE)
            .superficieTotImmatricule(UPDATED_SUPERFICIE_TOT_IMMATRICULE)
            .superficieTotEncadre(UPDATED_SUPERFICIE_TOT_ENCADRE);

        restImmaDomaineMockMvc.perform(put("/api/imma-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedImmaDomaine)))
            .andExpect(status().isOk());

        // Validate the ImmaDomaine in the database
        List<ImmaDomaine> immaDomaineList = immaDomaineRepository.findAll();
        assertThat(immaDomaineList).hasSize(databaseSizeBeforeUpdate);
        ImmaDomaine testImmaDomaine = immaDomaineList.get(immaDomaineList.size() - 1);
        assertThat(testImmaDomaine.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testImmaDomaine.getSuperficeTotInventorie()).isEqualTo(UPDATED_SUPERFICE_TOT_INVENTORIE);
        assertThat(testImmaDomaine.getSuperficieTotImmatricule()).isEqualTo(UPDATED_SUPERFICIE_TOT_IMMATRICULE);
        assertThat(testImmaDomaine.getSuperficieTotEncadre()).isEqualTo(UPDATED_SUPERFICIE_TOT_ENCADRE);
    }

    @Test
    @Transactional
    public void updateNonExistingImmaDomaine() throws Exception {
        int databaseSizeBeforeUpdate = immaDomaineRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImmaDomaineMockMvc.perform(put("/api/imma-domaines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(immaDomaine)))
            .andExpect(status().isBadRequest());

        // Validate the ImmaDomaine in the database
        List<ImmaDomaine> immaDomaineList = immaDomaineRepository.findAll();
        assertThat(immaDomaineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImmaDomaine() throws Exception {
        // Initialize the database
        immaDomaineService.save(immaDomaine);

        int databaseSizeBeforeDelete = immaDomaineRepository.findAll().size();

        // Delete the immaDomaine
        restImmaDomaineMockMvc.perform(delete("/api/imma-domaines/{id}", immaDomaine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImmaDomaine> immaDomaineList = immaDomaineRepository.findAll();
        assertThat(immaDomaineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
