package bf.agriculture.dgfomr.web.rest;

import bf.agriculture.dgfomr.GestionFormationApp;
import bf.agriculture.dgfomr.domain.NiveauInstruction;
import bf.agriculture.dgfomr.repository.NiveauInstructionRepository;
import bf.agriculture.dgfomr.service.NiveauInstructionService;

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
 * Integration tests for the {@link NiveauInstructionResource} REST controller.
 */
@SpringBootTest(classes = GestionFormationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NiveauInstructionResourceIT {

    private static final String DEFAULT_NIVEAU_INSTRUCTION = "AAAAAAAAAA";
    private static final String UPDATED_NIVEAU_INSTRUCTION = "BBBBBBBBBB";

    @Autowired
    private NiveauInstructionRepository niveauInstructionRepository;

    @Autowired
    private NiveauInstructionService niveauInstructionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNiveauInstructionMockMvc;

    private NiveauInstruction niveauInstruction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NiveauInstruction createEntity(EntityManager em) {
        NiveauInstruction niveauInstruction = new NiveauInstruction()
            .niveauInstruction(DEFAULT_NIVEAU_INSTRUCTION);
        return niveauInstruction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NiveauInstruction createUpdatedEntity(EntityManager em) {
        NiveauInstruction niveauInstruction = new NiveauInstruction()
            .niveauInstruction(UPDATED_NIVEAU_INSTRUCTION);
        return niveauInstruction;
    }

    @BeforeEach
    public void initTest() {
        niveauInstruction = createEntity(em);
    }

    @Test
    @Transactional
    public void createNiveauInstruction() throws Exception {
        int databaseSizeBeforeCreate = niveauInstructionRepository.findAll().size();
        // Create the NiveauInstruction
        restNiveauInstructionMockMvc.perform(post("/api/niveau-instructions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauInstruction)))
            .andExpect(status().isCreated());

        // Validate the NiveauInstruction in the database
        List<NiveauInstruction> niveauInstructionList = niveauInstructionRepository.findAll();
        assertThat(niveauInstructionList).hasSize(databaseSizeBeforeCreate + 1);
        NiveauInstruction testNiveauInstruction = niveauInstructionList.get(niveauInstructionList.size() - 1);
        assertThat(testNiveauInstruction.getNiveauInstruction()).isEqualTo(DEFAULT_NIVEAU_INSTRUCTION);
    }

    @Test
    @Transactional
    public void createNiveauInstructionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = niveauInstructionRepository.findAll().size();

        // Create the NiveauInstruction with an existing ID
        niveauInstruction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNiveauInstructionMockMvc.perform(post("/api/niveau-instructions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauInstruction)))
            .andExpect(status().isBadRequest());

        // Validate the NiveauInstruction in the database
        List<NiveauInstruction> niveauInstructionList = niveauInstructionRepository.findAll();
        assertThat(niveauInstructionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNiveauInstructionIsRequired() throws Exception {
        int databaseSizeBeforeTest = niveauInstructionRepository.findAll().size();
        // set the field null
        niveauInstruction.setNiveauInstruction(null);

        // Create the NiveauInstruction, which fails.


        restNiveauInstructionMockMvc.perform(post("/api/niveau-instructions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauInstruction)))
            .andExpect(status().isBadRequest());

        List<NiveauInstruction> niveauInstructionList = niveauInstructionRepository.findAll();
        assertThat(niveauInstructionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNiveauInstructions() throws Exception {
        // Initialize the database
        niveauInstructionRepository.saveAndFlush(niveauInstruction);

        // Get all the niveauInstructionList
        restNiveauInstructionMockMvc.perform(get("/api/niveau-instructions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(niveauInstruction.getId().intValue())))
            .andExpect(jsonPath("$.[*].niveauInstruction").value(hasItem(DEFAULT_NIVEAU_INSTRUCTION)));
    }
    
    @Test
    @Transactional
    public void getNiveauInstruction() throws Exception {
        // Initialize the database
        niveauInstructionRepository.saveAndFlush(niveauInstruction);

        // Get the niveauInstruction
        restNiveauInstructionMockMvc.perform(get("/api/niveau-instructions/{id}", niveauInstruction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(niveauInstruction.getId().intValue()))
            .andExpect(jsonPath("$.niveauInstruction").value(DEFAULT_NIVEAU_INSTRUCTION));
    }
    @Test
    @Transactional
    public void getNonExistingNiveauInstruction() throws Exception {
        // Get the niveauInstruction
        restNiveauInstructionMockMvc.perform(get("/api/niveau-instructions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNiveauInstruction() throws Exception {
        // Initialize the database
        niveauInstructionService.save(niveauInstruction);

        int databaseSizeBeforeUpdate = niveauInstructionRepository.findAll().size();

        // Update the niveauInstruction
        NiveauInstruction updatedNiveauInstruction = niveauInstructionRepository.findById(niveauInstruction.getId()).get();
        // Disconnect from session so that the updates on updatedNiveauInstruction are not directly saved in db
        em.detach(updatedNiveauInstruction);
        updatedNiveauInstruction
            .niveauInstruction(UPDATED_NIVEAU_INSTRUCTION);

        restNiveauInstructionMockMvc.perform(put("/api/niveau-instructions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNiveauInstruction)))
            .andExpect(status().isOk());

        // Validate the NiveauInstruction in the database
        List<NiveauInstruction> niveauInstructionList = niveauInstructionRepository.findAll();
        assertThat(niveauInstructionList).hasSize(databaseSizeBeforeUpdate);
        NiveauInstruction testNiveauInstruction = niveauInstructionList.get(niveauInstructionList.size() - 1);
        assertThat(testNiveauInstruction.getNiveauInstruction()).isEqualTo(UPDATED_NIVEAU_INSTRUCTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNiveauInstruction() throws Exception {
        int databaseSizeBeforeUpdate = niveauInstructionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNiveauInstructionMockMvc.perform(put("/api/niveau-instructions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(niveauInstruction)))
            .andExpect(status().isBadRequest());

        // Validate the NiveauInstruction in the database
        List<NiveauInstruction> niveauInstructionList = niveauInstructionRepository.findAll();
        assertThat(niveauInstructionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNiveauInstruction() throws Exception {
        // Initialize the database
        niveauInstructionService.save(niveauInstruction);

        int databaseSizeBeforeDelete = niveauInstructionRepository.findAll().size();

        // Delete the niveauInstruction
        restNiveauInstructionMockMvc.perform(delete("/api/niveau-instructions/{id}", niveauInstruction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NiveauInstruction> niveauInstructionList = niveauInstructionRepository.findAll();
        assertThat(niveauInstructionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
