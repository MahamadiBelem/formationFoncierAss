package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.Region;
import bf.agriculture.dgfomr.repository.RegionRepository;
import bf.agriculture.dgfomr.service.RegionService;

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
 * Integration tests for the {@link RegionResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RegionResourceIT {

    private static final String DEFAULT_LIBELLE_REGION = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_REGION = "BBBBBBBBBB";

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionService regionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRegionMockMvc;

    private Region region;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Region createEntity(EntityManager em) {
        Region region = new Region()
            .libelleRegion(DEFAULT_LIBELLE_REGION);
        return region;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Region createUpdatedEntity(EntityManager em) {
        Region region = new Region()
            .libelleRegion(UPDATED_LIBELLE_REGION);
        return region;
    }

    @BeforeEach
    public void initTest() {
        region = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegion() throws Exception {
        int databaseSizeBeforeCreate = regionRepository.findAll().size();
        // Create the Region
        restRegionMockMvc.perform(post("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isCreated());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeCreate + 1);
        Region testRegion = regionList.get(regionList.size() - 1);
        assertThat(testRegion.getLibelleRegion()).isEqualTo(DEFAULT_LIBELLE_REGION);
    }

    @Test
    @Transactional
    public void createRegionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regionRepository.findAll().size();

        // Create the Region with an existing ID
        region.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegionMockMvc.perform(post("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isBadRequest());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleRegionIsRequired() throws Exception {
        int databaseSizeBeforeTest = regionRepository.findAll().size();
        // set the field null
        region.setLibelleRegion(null);

        // Create the Region, which fails.


        restRegionMockMvc.perform(post("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isBadRequest());

        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRegions() throws Exception {
        // Initialize the database
        regionRepository.saveAndFlush(region);

        // Get all the regionList
        restRegionMockMvc.perform(get("/api/regions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(region.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelleRegion").value(hasItem(DEFAULT_LIBELLE_REGION)));
    }
    
    @Test
    @Transactional
    public void getRegion() throws Exception {
        // Initialize the database
        regionRepository.saveAndFlush(region);

        // Get the region
        restRegionMockMvc.perform(get("/api/regions/{id}", region.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(region.getId().intValue()))
            .andExpect(jsonPath("$.libelleRegion").value(DEFAULT_LIBELLE_REGION));
    }
    @Test
    @Transactional
    public void getNonExistingRegion() throws Exception {
        // Get the region
        restRegionMockMvc.perform(get("/api/regions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegion() throws Exception {
        // Initialize the database
        regionService.save(region);

        int databaseSizeBeforeUpdate = regionRepository.findAll().size();

        // Update the region
        Region updatedRegion = regionRepository.findById(region.getId()).get();
        // Disconnect from session so that the updates on updatedRegion are not directly saved in db
        em.detach(updatedRegion);
        updatedRegion
            .libelleRegion(UPDATED_LIBELLE_REGION);

        restRegionMockMvc.perform(put("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegion)))
            .andExpect(status().isOk());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeUpdate);
        Region testRegion = regionList.get(regionList.size() - 1);
        assertThat(testRegion.getLibelleRegion()).isEqualTo(UPDATED_LIBELLE_REGION);
    }

    @Test
    @Transactional
    public void updateNonExistingRegion() throws Exception {
        int databaseSizeBeforeUpdate = regionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegionMockMvc.perform(put("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isBadRequest());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegion() throws Exception {
        // Initialize the database
        regionService.save(region);

        int databaseSizeBeforeDelete = regionRepository.findAll().size();

        // Delete the region
        restRegionMockMvc.perform(delete("/api/regions/{id}", region.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
