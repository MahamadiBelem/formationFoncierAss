package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.TypecandidatService;
import bf.agriculture.dgfomr.domain.Typecandidat;
import bf.agriculture.dgfomr.repository.TypecandidatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Typecandidat}.
 */
@Service
@Transactional
public class TypecandidatServiceImpl implements TypecandidatService {

    private final Logger log = LoggerFactory.getLogger(TypecandidatServiceImpl.class);

    private final TypecandidatRepository typecandidatRepository;

    public TypecandidatServiceImpl(TypecandidatRepository typecandidatRepository) {
        this.typecandidatRepository = typecandidatRepository;
    }

    @Override
    public Typecandidat save(Typecandidat typecandidat) {
        log.debug("Request to save Typecandidat : {}", typecandidat);
        return typecandidatRepository.save(typecandidat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Typecandidat> findAll() {
        log.debug("Request to get all Typecandidats");
        return typecandidatRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Typecandidat> findOne(Long id) {
        log.debug("Request to get Typecandidat : {}", id);
        return typecandidatRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Typecandidat : {}", id);
        typecandidatRepository.deleteById(id);
    }
}
