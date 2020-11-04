package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.SfrService;
import bf.agriculture.dgfomr.domain.Sfr;
import bf.agriculture.dgfomr.repository.SfrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Sfr}.
 */
@Service
@Transactional
public class SfrServiceImpl implements SfrService {

    private final Logger log = LoggerFactory.getLogger(SfrServiceImpl.class);

    private final SfrRepository sfrRepository;

    public SfrServiceImpl(SfrRepository sfrRepository) {
        this.sfrRepository = sfrRepository;
    }

    @Override
    public Sfr save(Sfr sfr) {
        log.debug("Request to save Sfr : {}", sfr);
        return sfrRepository.save(sfr);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Sfr> findAll(Pageable pageable) {
        log.debug("Request to get all Sfrs");
        return sfrRepository.findAll(pageable);
    }



    /**
     *  Get all the sfrs where Communes is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<Sfr> findAllWhereCommunesIsNull() {
        log.debug("Request to get all sfrs where Communes is null");
        return StreamSupport
            .stream(sfrRepository.findAll().spliterator(), false)
            .filter(sfr -> sfr.getCommunes() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sfr> findOne(Long id) {
        log.debug("Request to get Sfr : {}", id);
        return sfrRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sfr : {}", id);
        sfrRepository.deleteById(id);
    }
}
