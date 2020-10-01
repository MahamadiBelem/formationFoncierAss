package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.InscriptionService;
import bf.agriculture.dgfomr.domain.Inscription;
import bf.agriculture.dgfomr.repository.InscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Inscription}.
 */
@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService {

    private final Logger log = LoggerFactory.getLogger(InscriptionServiceImpl.class);

    private final InscriptionRepository inscriptionRepository;

    public InscriptionServiceImpl(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public Inscription save(Inscription inscription) {
        log.debug("Request to save Inscription : {}", inscription);
        return inscriptionRepository.save(inscription);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Inscription> findAll(Pageable pageable) {
        log.debug("Request to get all Inscriptions");
        return inscriptionRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Inscription> findOne(Long id) {
        log.debug("Request to get Inscription : {}", id);
        return inscriptionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Inscription : {}", id);
        inscriptionRepository.deleteById(id);
    }
}
