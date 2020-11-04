package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ImmaDomaineService;
import bf.agriculture.dgfomr.domain.ImmaDomaine;
import bf.agriculture.dgfomr.repository.ImmaDomaineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ImmaDomaine}.
 */
@Service
@Transactional
public class ImmaDomaineServiceImpl implements ImmaDomaineService {

    private final Logger log = LoggerFactory.getLogger(ImmaDomaineServiceImpl.class);

    private final ImmaDomaineRepository immaDomaineRepository;

    public ImmaDomaineServiceImpl(ImmaDomaineRepository immaDomaineRepository) {
        this.immaDomaineRepository = immaDomaineRepository;
    }

    @Override
    public ImmaDomaine save(ImmaDomaine immaDomaine) {
        log.debug("Request to save ImmaDomaine : {}", immaDomaine);
        return immaDomaineRepository.save(immaDomaine);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ImmaDomaine> findAll(Pageable pageable) {
        log.debug("Request to get all ImmaDomaines");
        return immaDomaineRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ImmaDomaine> findOne(Long id) {
        log.debug("Request to get ImmaDomaine : {}", id);
        return immaDomaineRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImmaDomaine : {}", id);
        immaDomaineRepository.deleteById(id);
    }
}
