package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.NiveauRecrutementService;
import bf.agriculture.dgfomr.domain.NiveauRecrutement;
import bf.agriculture.dgfomr.repository.NiveauRecrutementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link NiveauRecrutement}.
 */
@Service
@Transactional
public class NiveauRecrutementServiceImpl implements NiveauRecrutementService {

    private final Logger log = LoggerFactory.getLogger(NiveauRecrutementServiceImpl.class);

    private final NiveauRecrutementRepository niveauRecrutementRepository;

    public NiveauRecrutementServiceImpl(NiveauRecrutementRepository niveauRecrutementRepository) {
        this.niveauRecrutementRepository = niveauRecrutementRepository;
    }

    @Override
    public NiveauRecrutement save(NiveauRecrutement niveauRecrutement) {
        log.debug("Request to save NiveauRecrutement : {}", niveauRecrutement);
        return niveauRecrutementRepository.save(niveauRecrutement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NiveauRecrutement> findAll() {
        log.debug("Request to get all NiveauRecrutements");
        return niveauRecrutementRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NiveauRecrutement> findOne(Long id) {
        log.debug("Request to get NiveauRecrutement : {}", id);
        return niveauRecrutementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiveauRecrutement : {}", id);
        niveauRecrutementRepository.deleteById(id);
    }
}
