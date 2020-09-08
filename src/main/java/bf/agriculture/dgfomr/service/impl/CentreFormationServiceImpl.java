package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.CentreFormationService;
import bf.agriculture.dgfomr.domain.CentreFormation;
import bf.agriculture.dgfomr.repository.CentreFormationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CentreFormation}.
 */
@Service
@Transactional
public class CentreFormationServiceImpl implements CentreFormationService {

    private final Logger log = LoggerFactory.getLogger(CentreFormationServiceImpl.class);

    private final CentreFormationRepository centreFormationRepository;

    public CentreFormationServiceImpl(CentreFormationRepository centreFormationRepository) {
        this.centreFormationRepository = centreFormationRepository;
    }

    @Override
    public CentreFormation save(CentreFormation centreFormation) {
        log.debug("Request to save CentreFormation : {}", centreFormation);
        return centreFormationRepository.save(centreFormation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CentreFormation> findAll(Pageable pageable) {
        log.debug("Request to get all CentreFormations");
        return centreFormationRepository.findAll(pageable);
    }


    public Page<CentreFormation> findAllWithEagerRelationships(Pageable pageable) {
        return centreFormationRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CentreFormation> findOne(Long id) {
        log.debug("Request to get CentreFormation : {}", id);
        return centreFormationRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CentreFormation : {}", id);
        centreFormationRepository.deleteById(id);
    }
}
