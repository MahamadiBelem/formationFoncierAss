package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.CaracteristiqueSfrService;
import bf.agriculture.dgfomr.domain.CaracteristiqueSfr;
import bf.agriculture.dgfomr.repository.CaracteristiqueSfrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaracteristiqueSfr}.
 */
@Service
@Transactional
public class CaracteristiqueSfrServiceImpl implements CaracteristiqueSfrService {

    private final Logger log = LoggerFactory.getLogger(CaracteristiqueSfrServiceImpl.class);

    private final CaracteristiqueSfrRepository caracteristiqueSfrRepository;

    public CaracteristiqueSfrServiceImpl(CaracteristiqueSfrRepository caracteristiqueSfrRepository) {
        this.caracteristiqueSfrRepository = caracteristiqueSfrRepository;
    }

    @Override
    public CaracteristiqueSfr save(CaracteristiqueSfr caracteristiqueSfr) {
        log.debug("Request to save CaracteristiqueSfr : {}", caracteristiqueSfr);
        return caracteristiqueSfrRepository.save(caracteristiqueSfr);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CaracteristiqueSfr> findAll(Pageable pageable) {
        log.debug("Request to get all CaracteristiqueSfrs");
        return caracteristiqueSfrRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CaracteristiqueSfr> findOne(Long id) {
        log.debug("Request to get CaracteristiqueSfr : {}", id);
        return caracteristiqueSfrRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaracteristiqueSfr : {}", id);
        caracteristiqueSfrRepository.deleteById(id);
    }
}
