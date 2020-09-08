package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ConditionAccessService;
import bf.agriculture.dgfomr.domain.ConditionAccess;
import bf.agriculture.dgfomr.repository.ConditionAccessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ConditionAccess}.
 */
@Service
@Transactional
public class ConditionAccessServiceImpl implements ConditionAccessService {

    private final Logger log = LoggerFactory.getLogger(ConditionAccessServiceImpl.class);

    private final ConditionAccessRepository conditionAccessRepository;

    public ConditionAccessServiceImpl(ConditionAccessRepository conditionAccessRepository) {
        this.conditionAccessRepository = conditionAccessRepository;
    }

    @Override
    public ConditionAccess save(ConditionAccess conditionAccess) {
        log.debug("Request to save ConditionAccess : {}", conditionAccess);
        return conditionAccessRepository.save(conditionAccess);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConditionAccess> findAll() {
        log.debug("Request to get all ConditionAccesses");
        return conditionAccessRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ConditionAccess> findOne(Long id) {
        log.debug("Request to get ConditionAccess : {}", id);
        return conditionAccessRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConditionAccess : {}", id);
        conditionAccessRepository.deleteById(id);
    }
}
