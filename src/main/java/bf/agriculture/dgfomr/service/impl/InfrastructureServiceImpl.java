package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.InfrastructureService;
import bf.agriculture.dgfomr.domain.Infrastructure;
import bf.agriculture.dgfomr.repository.InfrastructureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Infrastructure}.
 */
@Service
@Transactional
public class InfrastructureServiceImpl implements InfrastructureService {

    private final Logger log = LoggerFactory.getLogger(InfrastructureServiceImpl.class);

    private final InfrastructureRepository infrastructureRepository;

    public InfrastructureServiceImpl(InfrastructureRepository infrastructureRepository) {
        this.infrastructureRepository = infrastructureRepository;
    }

    @Override
    public Infrastructure save(Infrastructure infrastructure) {
        log.debug("Request to save Infrastructure : {}", infrastructure);
        return infrastructureRepository.save(infrastructure);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Infrastructure> findAll(Pageable pageable) {
        log.debug("Request to get all Infrastructures");
        return infrastructureRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Infrastructure> findOne(Long id) {
        log.debug("Request to get Infrastructure : {}", id);
        return infrastructureRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Infrastructure : {}", id);
        infrastructureRepository.deleteById(id);
    }
}
