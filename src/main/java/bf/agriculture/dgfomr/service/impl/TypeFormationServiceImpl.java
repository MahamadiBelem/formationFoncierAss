package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.TypeFormationService;
import bf.agriculture.dgfomr.domain.TypeFormation;
import bf.agriculture.dgfomr.repository.TypeFormationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeFormation}.
 */
@Service
@Transactional
public class TypeFormationServiceImpl implements TypeFormationService {

    private final Logger log = LoggerFactory.getLogger(TypeFormationServiceImpl.class);

    private final TypeFormationRepository typeFormationRepository;

    public TypeFormationServiceImpl(TypeFormationRepository typeFormationRepository) {
        this.typeFormationRepository = typeFormationRepository;
    }

    @Override
    public TypeFormation save(TypeFormation typeFormation) {
        log.debug("Request to save TypeFormation : {}", typeFormation);
        return typeFormationRepository.save(typeFormation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeFormation> findAll() {
        log.debug("Request to get all TypeFormations");
        return typeFormationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeFormation> findOne(Long id) {
        log.debug("Request to get TypeFormation : {}", id);
        return typeFormationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeFormation : {}", id);
        typeFormationRepository.deleteById(id);
    }
}
