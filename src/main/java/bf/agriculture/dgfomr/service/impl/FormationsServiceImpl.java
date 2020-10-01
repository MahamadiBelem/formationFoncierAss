package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.FormationsService;
import bf.agriculture.dgfomr.domain.Formations;
import bf.agriculture.dgfomr.repository.FormationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Formations}.
 */
@Service
@Transactional
public class FormationsServiceImpl implements FormationsService {

    private final Logger log = LoggerFactory.getLogger(FormationsServiceImpl.class);

    private final FormationsRepository formationsRepository;

    public FormationsServiceImpl(FormationsRepository formationsRepository) {
        this.formationsRepository = formationsRepository;
    }

    @Override
    public Formations save(Formations formations) {
        log.debug("Request to save Formations : {}", formations);
        return formationsRepository.save(formations);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Formations> findAll(Pageable pageable) {
        log.debug("Request to get all Formations");
        return formationsRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Formations> findOne(Long id) {
        log.debug("Request to get Formations : {}", id);
        return formationsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Formations : {}", id);
        formationsRepository.deleteById(id);
    }
}
