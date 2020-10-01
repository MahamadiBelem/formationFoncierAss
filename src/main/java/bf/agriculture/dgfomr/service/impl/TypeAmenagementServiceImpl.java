package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.TypeAmenagementService;
import bf.agriculture.dgfomr.domain.TypeAmenagement;
import bf.agriculture.dgfomr.repository.TypeAmenagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeAmenagement}.
 */
@Service
@Transactional
public class TypeAmenagementServiceImpl implements TypeAmenagementService {

    private final Logger log = LoggerFactory.getLogger(TypeAmenagementServiceImpl.class);

    private final TypeAmenagementRepository typeAmenagementRepository;

    public TypeAmenagementServiceImpl(TypeAmenagementRepository typeAmenagementRepository) {
        this.typeAmenagementRepository = typeAmenagementRepository;
    }

    @Override
    public TypeAmenagement save(TypeAmenagement typeAmenagement) {
        log.debug("Request to save TypeAmenagement : {}", typeAmenagement);
        return typeAmenagementRepository.save(typeAmenagement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeAmenagement> findAll(Pageable pageable) {
        log.debug("Request to get all TypeAmenagements");
        return typeAmenagementRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeAmenagement> findOne(Long id) {
        log.debug("Request to get TypeAmenagement : {}", id);
        return typeAmenagementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeAmenagement : {}", id);
        typeAmenagementRepository.deleteById(id);
    }
}
