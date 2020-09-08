package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.AmenagementService;
import bf.agriculture.dgfomr.domain.Amenagement;
import bf.agriculture.dgfomr.repository.AmenagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Amenagement}.
 */
@Service
@Transactional
public class AmenagementServiceImpl implements AmenagementService {

    private final Logger log = LoggerFactory.getLogger(AmenagementServiceImpl.class);

    private final AmenagementRepository amenagementRepository;

    public AmenagementServiceImpl(AmenagementRepository amenagementRepository) {
        this.amenagementRepository = amenagementRepository;
    }

    @Override
    public Amenagement save(Amenagement amenagement) {
        log.debug("Request to save Amenagement : {}", amenagement);
        return amenagementRepository.save(amenagement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Amenagement> findAll(Pageable pageable) {
        log.debug("Request to get all Amenagements");
        return amenagementRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Amenagement> findOne(Long id) {
        log.debug("Request to get Amenagement : {}", id);
        return amenagementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Amenagement : {}", id);
        amenagementRepository.deleteById(id);
    }
}
