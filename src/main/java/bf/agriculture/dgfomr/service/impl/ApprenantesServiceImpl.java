package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ApprenantesService;
import bf.agriculture.dgfomr.domain.Apprenantes;
import bf.agriculture.dgfomr.repository.ApprenantesRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Apprenantes}.
 */
@Service
@Transactional
public class ApprenantesServiceImpl implements ApprenantesService {

    private final Logger log = LoggerFactory.getLogger(ApprenantesServiceImpl.class);

    private final ApprenantesRepository apprenantesRepository;

    public ApprenantesServiceImpl(ApprenantesRepository apprenantesRepository) {
        this.apprenantesRepository = apprenantesRepository;
    }

    @Override
    public Apprenantes save(Apprenantes apprenantes) {
        log.debug("Request to save Apprenantes : {}", apprenantes);
        return apprenantesRepository.save(apprenantes);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Apprenantes> findAll(Pageable pageable) {
        log.debug("Request to get all Apprenantes");
        return apprenantesRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Apprenantes> findOne(Long id) {
        log.debug("Request to get Apprenantes : {}", id);
        return apprenantesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Apprenantes : {}", id);
        apprenantesRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public String generatematricule() {
        String nummatricule=RandomStringUtils.randomNumeric(6);
        return  nummatricule.toString();

    }


}
