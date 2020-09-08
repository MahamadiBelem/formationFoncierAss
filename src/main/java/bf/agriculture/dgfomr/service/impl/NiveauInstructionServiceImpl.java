package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.NiveauInstructionService;
import bf.agriculture.dgfomr.domain.NiveauInstruction;
import bf.agriculture.dgfomr.repository.NiveauInstructionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link NiveauInstruction}.
 */
@Service
@Transactional
public class NiveauInstructionServiceImpl implements NiveauInstructionService {

    private final Logger log = LoggerFactory.getLogger(NiveauInstructionServiceImpl.class);

    private final NiveauInstructionRepository niveauInstructionRepository;

    public NiveauInstructionServiceImpl(NiveauInstructionRepository niveauInstructionRepository) {
        this.niveauInstructionRepository = niveauInstructionRepository;
    }

    @Override
    public NiveauInstruction save(NiveauInstruction niveauInstruction) {
        log.debug("Request to save NiveauInstruction : {}", niveauInstruction);
        return niveauInstructionRepository.save(niveauInstruction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NiveauInstruction> findAll() {
        log.debug("Request to get all NiveauInstructions");
        return niveauInstructionRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NiveauInstruction> findOne(Long id) {
        log.debug("Request to get NiveauInstruction : {}", id);
        return niveauInstructionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiveauInstruction : {}", id);
        niveauInstructionRepository.deleteById(id);
    }
}
