package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.DossierApfrService;
import bf.agriculture.dgfomr.domain.DossierApfr;
import bf.agriculture.dgfomr.repository.DossierApfrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DossierApfr}.
 */
@Service
@Transactional
public class DossierApfrServiceImpl implements DossierApfrService {

    private final Logger log = LoggerFactory.getLogger(DossierApfrServiceImpl.class);

    private final DossierApfrRepository dossierApfrRepository;

    public DossierApfrServiceImpl(DossierApfrRepository dossierApfrRepository) {
        this.dossierApfrRepository = dossierApfrRepository;
    }

    @Override
    public DossierApfr save(DossierApfr dossierApfr) {
        log.debug("Request to save DossierApfr : {}", dossierApfr);
        return dossierApfrRepository.save(dossierApfr);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DossierApfr> findAll(Pageable pageable) {
        log.debug("Request to get all DossierApfrs");
        return dossierApfrRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DossierApfr> findOne(Long id) {
        log.debug("Request to get DossierApfr : {}", id);
        return dossierApfrRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DossierApfr : {}", id);
        dossierApfrRepository.deleteById(id);
    }
}
