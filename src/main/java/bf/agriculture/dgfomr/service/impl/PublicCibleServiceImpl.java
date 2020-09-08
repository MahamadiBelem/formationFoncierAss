package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.PublicCibleService;
import bf.agriculture.dgfomr.domain.PublicCible;
import bf.agriculture.dgfomr.repository.PublicCibleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PublicCible}.
 */
@Service
@Transactional
public class PublicCibleServiceImpl implements PublicCibleService {

    private final Logger log = LoggerFactory.getLogger(PublicCibleServiceImpl.class);

    private final PublicCibleRepository publicCibleRepository;

    public PublicCibleServiceImpl(PublicCibleRepository publicCibleRepository) {
        this.publicCibleRepository = publicCibleRepository;
    }

    @Override
    public PublicCible save(PublicCible publicCible) {
        log.debug("Request to save PublicCible : {}", publicCible);
        return publicCibleRepository.save(publicCible);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublicCible> findAll() {
        log.debug("Request to get all PublicCibles");
        return publicCibleRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PublicCible> findOne(Long id) {
        log.debug("Request to get PublicCible : {}", id);
        return publicCibleRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PublicCible : {}", id);
        publicCibleRepository.deleteById(id);
    }
}
