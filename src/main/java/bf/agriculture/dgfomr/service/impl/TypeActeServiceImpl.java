package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.TypeActeService;
import bf.agriculture.dgfomr.domain.TypeActe;
import bf.agriculture.dgfomr.repository.TypeActeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeActe}.
 */
@Service
@Transactional
public class TypeActeServiceImpl implements TypeActeService {

    private final Logger log = LoggerFactory.getLogger(TypeActeServiceImpl.class);

    private final TypeActeRepository typeActeRepository;

    public TypeActeServiceImpl(TypeActeRepository typeActeRepository) {
        this.typeActeRepository = typeActeRepository;
    }

    @Override
    public TypeActe save(TypeActe typeActe) {
        log.debug("Request to save TypeActe : {}", typeActe);
        return typeActeRepository.save(typeActe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeActe> findAll() {
        log.debug("Request to get all TypeActes");
        return typeActeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeActe> findOne(Long id) {
        log.debug("Request to get TypeActe : {}", id);
        return typeActeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeActe : {}", id);
        typeActeRepository.deleteById(id);
    }
}
