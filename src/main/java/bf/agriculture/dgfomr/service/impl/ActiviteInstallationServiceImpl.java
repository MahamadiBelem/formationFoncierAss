package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ActiviteInstallationService;
import bf.agriculture.dgfomr.domain.ActiviteInstallation;
import bf.agriculture.dgfomr.repository.ActiviteInstallationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ActiviteInstallation}.
 */
@Service
@Transactional
public class ActiviteInstallationServiceImpl implements ActiviteInstallationService {

    private final Logger log = LoggerFactory.getLogger(ActiviteInstallationServiceImpl.class);

    private final ActiviteInstallationRepository activiteInstallationRepository;

    public ActiviteInstallationServiceImpl(ActiviteInstallationRepository activiteInstallationRepository) {
        this.activiteInstallationRepository = activiteInstallationRepository;
    }

    @Override
    public ActiviteInstallation save(ActiviteInstallation activiteInstallation) {
        log.debug("Request to save ActiviteInstallation : {}", activiteInstallation);
        return activiteInstallationRepository.save(activiteInstallation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActiviteInstallation> findAll() {
        log.debug("Request to get all ActiviteInstallations");
        return activiteInstallationRepository.findAllWithEagerRelationships();
    }


    public Page<ActiviteInstallation> findAllWithEagerRelationships(Pageable pageable) {
        return activiteInstallationRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActiviteInstallation> findOne(Long id) {
        log.debug("Request to get ActiviteInstallation : {}", id);
        return activiteInstallationRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ActiviteInstallation : {}", id);
        activiteInstallationRepository.deleteById(id);
    }
}
