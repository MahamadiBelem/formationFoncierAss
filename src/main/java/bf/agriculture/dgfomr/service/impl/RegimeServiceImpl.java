package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.RegimeService;
import bf.agriculture.dgfomr.domain.Regime;
import bf.agriculture.dgfomr.repository.RegimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Regime}.
 */
@Service
@Transactional
public class RegimeServiceImpl implements RegimeService {

    private final Logger log = LoggerFactory.getLogger(RegimeServiceImpl.class);

    private final RegimeRepository regimeRepository;

    public RegimeServiceImpl(RegimeRepository regimeRepository) {
        this.regimeRepository = regimeRepository;
    }

    @Override
    public Regime save(Regime regime) {
        log.debug("Request to save Regime : {}", regime);
        return regimeRepository.save(regime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Regime> findAll() {
        log.debug("Request to get all Regimes");
        return regimeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Regime> findOne(Long id) {
        log.debug("Request to get Regime : {}", id);
        return regimeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Regime : {}", id);
        regimeRepository.deleteById(id);
    }
}
