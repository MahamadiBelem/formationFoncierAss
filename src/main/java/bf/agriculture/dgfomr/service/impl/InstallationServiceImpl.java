package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.InstallationService;
import bf.agriculture.dgfomr.domain.Installation;
import bf.agriculture.dgfomr.repository.InstallationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Installation}.
 */
@Service
@Transactional
public class InstallationServiceImpl implements InstallationService {

    private final Logger log = LoggerFactory.getLogger(InstallationServiceImpl.class);

    private final InstallationRepository installationRepository;

    public InstallationServiceImpl(InstallationRepository installationRepository) {
        this.installationRepository = installationRepository;
    }

    @Override
    public Installation save(Installation installation) {
        log.debug("Request to save Installation : {}", installation);
        return installationRepository.save(installation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Installation> findAll() {
        log.debug("Request to get all Installations");
        return installationRepository.findAllWithEagerRelationships();
    }


    public Page<Installation> findAllWithEagerRelationships(Pageable pageable) {
        return installationRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Installation> findOne(Long id) {
        log.debug("Request to get Installation : {}", id);
        return installationRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Installation : {}", id);
        installationRepository.deleteById(id);
    }
}
