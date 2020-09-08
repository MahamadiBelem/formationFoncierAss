package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.KitsService;
import bf.agriculture.dgfomr.domain.Kits;
import bf.agriculture.dgfomr.repository.KitsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Kits}.
 */
@Service
@Transactional
public class KitsServiceImpl implements KitsService {

    private final Logger log = LoggerFactory.getLogger(KitsServiceImpl.class);

    private final KitsRepository kitsRepository;

    public KitsServiceImpl(KitsRepository kitsRepository) {
        this.kitsRepository = kitsRepository;
    }

    @Override
    public Kits save(Kits kits) {
        log.debug("Request to save Kits : {}", kits);
        return kitsRepository.save(kits);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kits> findAll() {
        log.debug("Request to get all Kits");
        return kitsRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Kits> findOne(Long id) {
        log.debug("Request to get Kits : {}", id);
        return kitsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Kits : {}", id);
        kitsRepository.deleteById(id);
    }
}
