package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.FormateursService;
import bf.agriculture.dgfomr.domain.Formateurs;
import bf.agriculture.dgfomr.repository.FormateursRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Formateurs}.
 */
@Service
@Transactional
public class FormateursServiceImpl implements FormateursService {

    private final Logger log = LoggerFactory.getLogger(FormateursServiceImpl.class);

    private final FormateursRepository formateursRepository;

    public FormateursServiceImpl(FormateursRepository formateursRepository) {
        this.formateursRepository = formateursRepository;
    }

    @Override
    public Formateurs save(Formateurs formateurs) {
        log.debug("Request to save Formateurs : {}", formateurs);
        return formateursRepository.save(formateurs);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Formateurs> findAll(Pageable pageable) {
        log.debug("Request to get all Formateurs");
        return formateursRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Formateurs> findOne(Long id) {
        log.debug("Request to get Formateurs : {}", id);
        return formateursRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Formateurs : {}", id);
        formateursRepository.deleteById(id);
    }
}
