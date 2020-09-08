package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.PromoteursService;
import bf.agriculture.dgfomr.domain.Promoteurs;
import bf.agriculture.dgfomr.repository.PromoteursRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Promoteurs}.
 */
@Service
@Transactional
public class PromoteursServiceImpl implements PromoteursService {

    private final Logger log = LoggerFactory.getLogger(PromoteursServiceImpl.class);

    private final PromoteursRepository promoteursRepository;

    public PromoteursServiceImpl(PromoteursRepository promoteursRepository) {
        this.promoteursRepository = promoteursRepository;
    }

    @Override
    public Promoteurs save(Promoteurs promoteurs) {
        log.debug("Request to save Promoteurs : {}", promoteurs);
        return promoteursRepository.save(promoteurs);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Promoteurs> findAll() {
        log.debug("Request to get all Promoteurs");
        return promoteursRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Promoteurs> findOne(Long id) {
        log.debug("Request to get Promoteurs : {}", id);
        return promoteursRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Promoteurs : {}", id);
        promoteursRepository.deleteById(id);
    }
}
