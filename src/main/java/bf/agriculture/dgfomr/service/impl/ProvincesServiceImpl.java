package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ProvincesService;
import bf.agriculture.dgfomr.domain.Provinces;
import bf.agriculture.dgfomr.repository.ProvincesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Provinces}.
 */
@Service
@Transactional
public class ProvincesServiceImpl implements ProvincesService {

    private final Logger log = LoggerFactory.getLogger(ProvincesServiceImpl.class);

    private final ProvincesRepository provincesRepository;

    public ProvincesServiceImpl(ProvincesRepository provincesRepository) {
        this.provincesRepository = provincesRepository;
    }

    @Override
    public Provinces save(Provinces provinces) {
        log.debug("Request to save Provinces : {}", provinces);
        return provincesRepository.save(provinces);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Provinces> findAll() {
        log.debug("Request to get all Provinces");
        return provincesRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Provinces> findOne(Long id) {
        log.debug("Request to get Provinces : {}", id);
        return provincesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Provinces : {}", id);
        provincesRepository.deleteById(id);
    }
}
