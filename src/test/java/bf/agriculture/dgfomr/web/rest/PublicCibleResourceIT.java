package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.PublicCible;
import bf.agriculture.dgfomr.repository.PublicCibleRepository;
import bf.agriculture.dgfomr.service.PublicCibleService;

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
 * Integration tests for the {@link PublicCibleResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PublicCibleResourceIT {

    private static final String DEFAULT_LIBELLE_PUBLIC = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_PUBLIC = "BBBBBBBBBB";

    @Autowired
    private PublicCibleRepository publicCibleRepository;

    @Autowired
    private PublicCibleService publicCibleService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPublicCibleMockMvc;

    private PublicCible publicCible;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PublicCible createEntity(EntityManager em) {
        PublicCible publicCible = new PublicCible()
            .libellePublic(DEFAULT_LIBELLE_PUBLIC);
        return publicCible;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PublicCible createUpdatedEntity(EntityManager em) {
        PublicCible publicCible = new PublicCible()
            .libellePublic(UPDATED_LIBELLE_PUBLIC);
        return publicCible;
    }

    @BeforeEach
    public void initTest() {
        publicCible = createEntity(em);
    }

    @Test
    @Transactional
    public void createPublicCible() throws Exception {
        int databaseSizeBeforeCreate = publicCibleRepository.findAll().size();
        // Create the PublicCible
        restPublicCibleMockMvc.perform(post("/api/public-cibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(publicCible)))
            .andExpect(status().isCreated());

        // Validate the PublicCible in the database
        List<PublicCible> publicCibleList = publicCibleRepository.findAll();
        assertThat(publicCibleList).hasSize(databaseSizeBeforeCreate + 1);
        PublicCible testPublicCible = publicCibleList.get(publicCibleList.size() - 1);
        assertThat(testPublicCible.getLibellePublic()).isEqualTo(DEFAULT_LIBELLE_PUBLIC);
    }

    @Test
    @Transactional
    public void createPublicCibleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = publicCibleRepository.findAll().size();

        // Create the PublicCible with an existing ID
        publicCible.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPublicCibleMockMvc.perform(post("/api/public-cibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(publicCible)))
            .andExpect(status().isBadRequest());

        // Validate the PublicCible in the database
        List<PublicCible> publicCibleList = publicCibleRepository.findAll();
        assertThat(publicCibleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibellePublicIsRequired() throws Exception {
        int databaseSizeBeforeTest = publicCibleRepository.findAll().size();
        // set the field null
        publicCible.setLibellePublic(null);

        // Create the PublicCible, which fails.


        restPublicCibleMockMvc.perform(post("/api/public-cibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(publicCible)))
            .andExpect(status().isBadRequest());

        List<PublicCible> publicCibleList = publicCibleRepository.findAll();
        assertThat(publicCibleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPublicCibles() throws Exception {
        // Initialize the database
        publicCibleRepository.saveAndFlush(publicCible);

        // Get all the publicCibleList
        restPublicCibleMockMvc.perform(get("/api/public-cibles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(publicCible.getId().intValue())))
            .andExpect(jsonPath("$.[*].libellePublic").value(hasItem(DEFAULT_LIBELLE_PUBLIC)));
    }
    
    @Test
    @Transactional
    public void getPublicCible() throws Exception {
        // Initialize the database
        publicCibleRepository.saveAndFlush(publicCible);

        // Get the publicCible
        restPublicCibleMockMvc.perform(get("/api/public-cibles/{id}", publicCible.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(publicCible.getId().intValue()))
            .andExpect(jsonPath("$.libellePublic").value(DEFAULT_LIBELLE_PUBLIC));
    }
    @Test
    @Transactional
    public void getNonExistingPublicCible() throws Exception {
        // Get the publicCible
        restPublicCibleMockMvc.perform(get("/api/public-cibles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePublicCible() throws Exception {
        // Initialize the database
        publicCibleService.save(publicCible);

        int databaseSizeBeforeUpdate = publicCibleRepository.findAll().size();

        // Update the publicCible
        PublicCible updatedPublicCible = publicCibleRepository.findById(publicCible.getId()).get();
        // Disconnect from session so that the updates on updatedPublicCible are not directly saved in db
        em.detach(updatedPublicCible);
        updatedPublicCible
            .libellePublic(UPDATED_LIBELLE_PUBLIC);

        restPublicCibleMockMvc.perform(put("/api/public-cibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPublicCible)))
            .andExpect(status().isOk());

        // Validate the PublicCible in the database
        List<PublicCible> publicCibleList = publicCibleRepository.findAll();
        assertThat(publicCibleList).hasSize(databaseSizeBeforeUpdate);
        PublicCible testPublicCible = publicCibleList.get(publicCibleList.size() - 1);
        assertThat(testPublicCible.getLibellePublic()).isEqualTo(UPDATED_LIBELLE_PUBLIC);
    }

    @Test
    @Transactional
    public void updateNonExistingPublicCible() throws Exception {
        int databaseSizeBeforeUpdate = publicCibleRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPublicCibleMockMvc.perform(put("/api/public-cibles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(publicCible)))
            .andExpect(status().isBadRequest());

        // Validate the PublicCible in the database
        List<PublicCible> publicCibleList = publicCibleRepository.findAll();
        assertThat(publicCibleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePublicCible() throws Exception {
        // Initialize the database
        publicCibleService.save(publicCible);

        int databaseSizeBeforeDelete = publicCibleRepository.findAll().size();

        // Delete the publicCible
        restPublicCibleMockMvc.perform(delete("/api/public-cibles/{id}", publicCible.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PublicCible> publicCibleList = publicCibleRepository.findAll();
        assertThat(publicCibleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
