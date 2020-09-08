package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.SpecialitesService;
import bf.agriculture.dgfomr.domain.Specialites;
import bf.agriculture.dgfomr.repository.SpecialitesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Specialites}.
 */
@Service
@Transactional
public class SpecialitesServiceImpl implements SpecialitesService {

    private final Logger log = LoggerFactory.getLogger(SpecialitesServiceImpl.class);

    private final SpecialitesRepository specialitesRepository;

    public SpecialitesServiceImpl(SpecialitesRepository specialitesRepository) {
        this.specialitesRepository = specialitesRepository;
    }

    @Override
    public Specialites save(Specialites specialites) {
        log.debug("Request to save Specialites : {}", specialites);
        return specialitesRepository.save(specialites);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialites> findAll() {
        log.debug("Request to get all Specialites");
        return specialitesRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Specialites> findOne(Long id) {
        log.debug("Request to get Specialites : {}", id);
        return specialitesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Specialites : {}", id);
        specialitesRepository.deleteById(id);
    }
}
