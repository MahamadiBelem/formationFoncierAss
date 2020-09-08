package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.CompositionKitsService;
import bf.agriculture.dgfomr.domain.CompositionKits;
import bf.agriculture.dgfomr.repository.CompositionKitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CompositionKits}.
 */
@Service
@Transactional
public class CompositionKitsServiceImpl implements CompositionKitsService {

    private final Logger log = LoggerFactory.getLogger(CompositionKitsServiceImpl.class);

    private final CompositionKitsRepository compositionKitsRepository;

    public CompositionKitsServiceImpl(CompositionKitsRepository compositionKitsRepository) {
        this.compositionKitsRepository = compositionKitsRepository;
    }

    @Override
    public CompositionKits save(CompositionKits compositionKits) {
        log.debug("Request to save CompositionKits : {}", compositionKits);
        return compositionKitsRepository.save(compositionKits);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompositionKits> findAll() {
        log.debug("Request to get all CompositionKits");
        return compositionKitsRepository.findAllWithEagerRelationships();
    }


    public Page<CompositionKits> findAllWithEagerRelationships(Pageable pageable) {
        return compositionKitsRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompositionKits> findOne(Long id) {
        log.debug("Request to get CompositionKits : {}", id);
        return compositionKitsRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompositionKits : {}", id);
        compositionKitsRepository.deleteById(id);
    }
}
