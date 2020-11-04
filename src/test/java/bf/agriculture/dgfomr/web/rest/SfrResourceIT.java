package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Sfr;
import bf.agriculture.dgfomr.repository.SfrRepository;
import bf.agriculture.dgfomr.service.SfrService;

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
 * Integration tests for the {@link SfrResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SfrResourceIT {

    private static final Integer DEFAULT_NBR_PERSONNE = 1;
    private static final Integer UPDATED_NBR_PERSONNE = 2;

    @Autowired
    private SfrRepository sfrRepository;

    @Autowired
    private SfrService sfrService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSfrMockMvc;

    private Sfr sfr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sfr createEntity(EntityManager em) {
        Sfr sfr = new Sfr()
            .nbrPersonne(DEFAULT_NBR_PERSONNE);
        return sfr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sfr createUpdatedEntity(EntityManager em) {
        Sfr sfr = new Sfr()
            .nbrPersonne(UPDATED_NBR_PERSONNE);
        return sfr;
    }

    @BeforeEach
    public void initTest() {
        sfr = createEntity(em);
    }

    @Test
    @Transactional
    public void createSfr() throws Exception {
        int databaseSizeBeforeCreate = sfrRepository.findAll().size();
        // Create the Sfr
        restSfrMockMvc.perform(post("/api/sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sfr)))
            .andExpect(status().isCreated());

        // Validate the Sfr in the database
        List<Sfr> sfrList = sfrRepository.findAll();
        assertThat(sfrList).hasSize(databaseSizeBeforeCreate + 1);
        Sfr testSfr = sfrList.get(sfrList.size() - 1);
        assertThat(testSfr.getNbrPersonne()).isEqualTo(DEFAULT_NBR_PERSONNE);
    }

    @Test
    @Transactional
    public void createSfrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sfrRepository.findAll().size();

        // Create the Sfr with an existing ID
        sfr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSfrMockMvc.perform(post("/api/sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sfr)))
            .andExpect(status().isBadRequest());

        // Validate the Sfr in the database
        List<Sfr> sfrList = sfrRepository.findAll();
        assertThat(sfrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSfrs() throws Exception {
        // Initialize the database
        sfrRepository.saveAndFlush(sfr);

        // Get all the sfrList
        restSfrMockMvc.perform(get("/api/sfrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sfr.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbrPersonne").value(hasItem(DEFAULT_NBR_PERSONNE)));
    }
    
    @Test
    @Transactional
    public void getSfr() throws Exception {
        // Initialize the database
        sfrRepository.saveAndFlush(sfr);

        // Get the sfr
        restSfrMockMvc.perform(get("/api/sfrs/{id}", sfr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sfr.getId().intValue()))
            .andExpect(jsonPath("$.nbrPersonne").value(DEFAULT_NBR_PERSONNE));
    }
    @Test
    @Transactional
    public void getNonExistingSfr() throws Exception {
        // Get the sfr
        restSfrMockMvc.perform(get("/api/sfrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSfr() throws Exception {
        // Initialize the database
        sfrService.save(sfr);

        int databaseSizeBeforeUpdate = sfrRepository.findAll().size();

        // Update the sfr
        Sfr updatedSfr = sfrRepository.findById(sfr.getId()).get();
        // Disconnect from session so that the updates on updatedSfr are not directly saved in db
        em.detach(updatedSfr);
        updatedSfr
            .nbrPersonne(UPDATED_NBR_PERSONNE);

        restSfrMockMvc.perform(put("/api/sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSfr)))
            .andExpect(status().isOk());

        // Validate the Sfr in the database
        List<Sfr> sfrList = sfrRepository.findAll();
        assertThat(sfrList).hasSize(databaseSizeBeforeUpdate);
        Sfr testSfr = sfrList.get(sfrList.size() - 1);
        assertThat(testSfr.getNbrPersonne()).isEqualTo(UPDATED_NBR_PERSONNE);
    }

    @Test
    @Transactional
    public void updateNonExistingSfr() throws Exception {
        int databaseSizeBeforeUpdate = sfrRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSfrMockMvc.perform(put("/api/sfrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sfr)))
            .andExpect(status().isBadRequest());

        // Validate the Sfr in the database
        List<Sfr> sfrList = sfrRepository.findAll();
        assertThat(sfrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSfr() throws Exception {
        // Initialize the database
        sfrService.save(sfr);

        int databaseSizeBeforeDelete = sfrRepository.findAll().size();

        // Delete the sfr
        restSfrMockMvc.perform(delete("/api/sfrs/{id}", sfr.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sfr> sfrList = sfrRepository.findAll();
        assertThat(sfrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
