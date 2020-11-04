package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.FormationSFRService;
import bf.agriculture.dgfomr.domain.FormationSFR;
import bf.agriculture.dgfomr.repository.FormationSFRRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FormationSFR}.
 */
@Service
@Transactional
public class FormationSFRServiceImpl implements FormationSFRService {

    private final Logger log = LoggerFactory.getLogger(FormationSFRServiceImpl.class);

    private final FormationSFRRepository formationSFRRepository;

    public FormationSFRServiceImpl(FormationSFRRepository formationSFRRepository) {
        this.formationSFRRepository = formationSFRRepository;
    }

    @Override
    public FormationSFR save(FormationSFR formationSFR) {
        log.debug("Request to save FormationSFR : {}", formationSFR);
        return formationSFRRepository.save(formationSFR);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FormationSFR> findAll(Pageable pageable) {
        log.debug("Request to get all FormationSFRS");
        return formationSFRRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FormationSFR> findOne(Long id) {
        log.debug("Request to get FormationSFR : {}", id);
        return formationSFRRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormationSFR : {}", id);
        formationSFRRepository.deleteById(id);
    }
}
