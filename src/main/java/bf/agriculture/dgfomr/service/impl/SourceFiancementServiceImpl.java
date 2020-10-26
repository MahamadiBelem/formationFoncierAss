package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.SourceFiancementService;
import bf.agriculture.dgfomr.domain.SourceFiancement;
import bf.agriculture.dgfomr.repository.SourceFiancementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SourceFiancement}.
 */
@Service
@Transactional
public class SourceFiancementServiceImpl implements SourceFiancementService {

    private final Logger log = LoggerFactory.getLogger(SourceFiancementServiceImpl.class);

    private final SourceFiancementRepository sourceFiancementRepository;

    public SourceFiancementServiceImpl(SourceFiancementRepository sourceFiancementRepository) {
        this.sourceFiancementRepository = sourceFiancementRepository;
    }

    @Override
    public SourceFiancement save(SourceFiancement sourceFiancement) {
        log.debug("Request to save SourceFiancement : {}", sourceFiancement);
        return sourceFiancementRepository.save(sourceFiancement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SourceFiancement> findAll() {
        log.debug("Request to get all SourceFiancements");
        return sourceFiancementRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SourceFiancement> findOne(Long id) {
        log.debug("Request to get SourceFiancement : {}", id);
        return sourceFiancementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SourceFiancement : {}", id);
        sourceFiancementRepository.deleteById(id);
    }
}
