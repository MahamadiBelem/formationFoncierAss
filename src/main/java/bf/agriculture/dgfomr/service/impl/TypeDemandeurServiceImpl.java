package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.TypeDemandeurService;
import bf.agriculture.dgfomr.domain.TypeDemandeur;
import bf.agriculture.dgfomr.repository.TypeDemandeurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeDemandeur}.
 */
@Service
@Transactional
public class TypeDemandeurServiceImpl implements TypeDemandeurService {

    private final Logger log = LoggerFactory.getLogger(TypeDemandeurServiceImpl.class);

    private final TypeDemandeurRepository typeDemandeurRepository;

    public TypeDemandeurServiceImpl(TypeDemandeurRepository typeDemandeurRepository) {
        this.typeDemandeurRepository = typeDemandeurRepository;
    }

    @Override
    public TypeDemandeur save(TypeDemandeur typeDemandeur) {
        log.debug("Request to save TypeDemandeur : {}", typeDemandeur);
        return typeDemandeurRepository.save(typeDemandeur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDemandeur> findAll() {
        log.debug("Request to get all TypeDemandeurs");
        return typeDemandeurRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDemandeur> findOne(Long id) {
        log.debug("Request to get TypeDemandeur : {}", id);
        return typeDemandeurRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeDemandeur : {}", id);
        typeDemandeurRepository.deleteById(id);
    }
}
