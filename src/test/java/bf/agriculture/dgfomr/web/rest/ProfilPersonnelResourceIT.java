package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.ProfilPersonnel;
import bf.agriculture.dgfomr.repository.ProfilPersonnelRepository;
import bf.agriculture.dgfomr.service.ProfilPersonnelService;

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
 * Integration tests for the {@link ProfilPersonnelResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProfilPersonnelResourceIT {

    private static final String DEFAULT_LIBELLE_PROFIL = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_PROFIL = "BBBBBBBBBB";

    @Autowired
    private ProfilPersonnelRepository profilPersonnelRepository;

    @Autowired
    private ProfilPersonnelService profilPersonnelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfilPersonnelMockMvc;

    private ProfilPersonnel profilPersonnel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfilPersonnel createEntity(EntityManager em) {
        ProfilPersonnel profilPersonnel = new ProfilPersonnel()
            .libelleProfil(DEFAULT_LIBELLE_PROFIL);
        return profilPersonnel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfilPersonnel createUpdatedEntity(EntityManager em) {
        ProfilPersonnel profilPersonnel = new ProfilPersonnel()
            .libelleProfil(UPDATED_LIBELLE_PROFIL);
        return profilPersonnel;
    }

    @BeforeEach
    public void initTest() {
        profilPersonnel = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilPersonnel() throws Exception {
        int databaseSizeBeforeCreate = profilPersonnelRepository.findAll().size();
        // Create the ProfilPersonnel
        restProfilPersonnelMockMvc.perform(post("/api/profil-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilPersonnel)))
            .andExpect(status().isCreated());

        // Validate the ProfilPersonnel in the database
        List<ProfilPersonnel> profilPersonnelList = profilPersonnelRepository.findAll();
        assertThat(profilPersonnelList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilPersonnel testProfilPersonnel = profilPersonnelList.get(profilPersonnelList.size() - 1);
        assertThat(testProfilPersonnel.getLibelleProfil()).isEqualTo(DEFAULT_LIBELLE_PROFIL);
    }

    @Test
    @Transactional
    public void createProfilPersonnelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilPersonnelRepository.findAll().size();

        // Create the ProfilPersonnel with an existing ID
        profilPersonnel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilPersonnelMockMvc.perform(post("/api/profil-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilPersonnel)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilPersonnel in the database
        List<ProfilPersonnel> profilPersonnelList = profilPersonnelRepository.findAll();
        assertThat(profilPersonnelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProfilPersonnels() throws Exception {
        // Initialize the database
        profilPersonnelRepository.saveAndFlush(profilPersonnel);

        // Get all the profilPersonnelList
        restProfilPersonnelMockMvc.perform(get("/api/profil-personnels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilPersonnel.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleProfil").value(hasItem(DEFAULT_LIBELLE_PROFIL)));
    }
    
    @Test
    @Transactional
    public void getProfilPersonnel() throws Exception {
        // Initialize the database
        profilPersonnelRepository.saveAndFlush(profilPersonnel);

        // Get the profilPersonnel
        restProfilPersonnelMockMvc.perform(get("/api/profil-personnels/{id}", profilPersonnel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profilPersonnel.getId().intValue()))
            .andExpect(jsonPath("$.libelleProfil").value(DEFAULT_LIBELLE_PROFIL));
    }
    @Test
    @Transactional
    public void getNonExistingProfilPersonnel() throws Exception {
        // Get the profilPersonnel
        restProfilPersonnelMockMvc.perform(get("/api/profil-personnels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilPersonnel() throws Exception {
        // Initialize the database
        profilPersonnelService.save(profilPersonnel);

        int databaseSizeBeforeUpdate = profilPersonnelRepository.findAll().size();

        // Update the profilPersonnel
        ProfilPersonnel updatedProfilPersonnel = profilPersonnelRepository.findById(profilPersonnel.getId()).get();
        // Disconnect from session so that the updates on updatedProfilPersonnel are not directly saved in db
        em.detach(updatedProfilPersonnel);
        updatedProfilPersonnel
            .libelleProfil(UPDATED_LIBELLE_PROFIL);

        restProfilPersonnelMockMvc.perform(put("/api/profil-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfilPersonnel)))
            .andExpect(status().isOk());

        // Validate the ProfilPersonnel in the database
        List<ProfilPersonnel> profilPersonnelList = profilPersonnelRepository.findAll();
        assertThat(profilPersonnelList).hasSize(databaseSizeBeforeUpdate);
        ProfilPersonnel testProfilPersonnel = profilPersonnelList.get(profilPersonnelList.size() - 1);
        assertThat(testProfilPersonnel.getLibelleProfil()).isEqualTo(UPDATED_LIBELLE_PROFIL);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilPersonnel() throws Exception {
        int databaseSizeBeforeUpdate = profilPersonnelRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfilPersonnelMockMvc.perform(put("/api/profil-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profilPersonnel)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilPersonnel in the database
        List<ProfilPersonnel> profilPersonnelList = profilPersonnelRepository.findAll();
        assertThat(profilPersonnelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfilPersonnel() throws Exception {
        // Initialize the database
        profilPersonnelService.save(profilPersonnel);

        int databaseSizeBeforeDelete = profilPersonnelRepository.findAll().size();

        // Delete the profilPersonnel
        restProfilPersonnelMockMvc.perform(delete("/api/profil-personnels/{id}", profilPersonnel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfilPersonnel> profilPersonnelList = profilPersonnelRepository.findAll();
        assertThat(profilPersonnelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
