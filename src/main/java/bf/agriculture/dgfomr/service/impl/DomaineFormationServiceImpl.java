package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.DomaineFormationService;
import bf.agriculture.dgfomr.domain.DomaineFormation;
import bf.agriculture.dgfomr.repository.DomaineFormationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DomaineFormation}.
 */
@Service
@Transactional
public class DomaineFormationServiceImpl implements DomaineFormationService {

    private final Logger log = LoggerFactory.getLogger(DomaineFormationServiceImpl.class);

    private final DomaineFormationRepository domaineFormationRepository;

    public DomaineFormationServiceImpl(DomaineFormationRepository domaineFormationRepository) {
        this.domaineFormationRepository = domaineFormationRepository;
    }

    @Override
    public DomaineFormation save(DomaineFormation domaineFormation) {
        log.debug("Request to save DomaineFormation : {}", domaineFormation);
        return domaineFormationRepository.save(domaineFormation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DomaineFormation> findAll() {
        log.debug("Request to get all DomaineFormations");
        return domaineFormationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DomaineFormation> findOne(Long id) {
        log.debug("Request to get DomaineFormation : {}", id);
        return domaineFormationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DomaineFormation : {}", id);
        domaineFormationRepository.deleteById(id);
    }
}
