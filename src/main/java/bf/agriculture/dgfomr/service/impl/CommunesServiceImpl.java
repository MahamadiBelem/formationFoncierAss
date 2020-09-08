package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.CommunesService;
import bf.agriculture.dgfomr.domain.Communes;
import bf.agriculture.dgfomr.repository.CommunesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Communes}.
 */
@Service
@Transactional
public class CommunesServiceImpl implements CommunesService {

    private final Logger log = LoggerFactory.getLogger(CommunesServiceImpl.class);

    private final CommunesRepository communesRepository;

    public CommunesServiceImpl(CommunesRepository communesRepository) {
        this.communesRepository = communesRepository;
    }

    @Override
    public Communes save(Communes communes) {
        log.debug("Request to save Communes : {}", communes);
        return communesRepository.save(communes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Communes> findAll() {
        log.debug("Request to get all Communes");
        return communesRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Communes> findOne(Long id) {
        log.debug("Request to get Communes : {}", id);
        return communesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Communes : {}", id);
        communesRepository.deleteById(id);
    }
}
