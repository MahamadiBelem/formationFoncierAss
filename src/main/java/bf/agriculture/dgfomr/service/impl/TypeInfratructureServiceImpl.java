package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.TypeInfratructureService;
import bf.agriculture.dgfomr.domain.TypeInfratructure;
import bf.agriculture.dgfomr.repository.TypeInfratructureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeInfratructure}.
 */
@Service
@Transactional
public class TypeInfratructureServiceImpl implements TypeInfratructureService {

    private final Logger log = LoggerFactory.getLogger(TypeInfratructureServiceImpl.class);

    private final TypeInfratructureRepository typeInfratructureRepository;

    public TypeInfratructureServiceImpl(TypeInfratructureRepository typeInfratructureRepository) {
        this.typeInfratructureRepository = typeInfratructureRepository;
    }

    @Override
    public TypeInfratructure save(TypeInfratructure typeInfratructure) {
        log.debug("Request to save TypeInfratructure : {}", typeInfratructure);
        return typeInfratructureRepository.save(typeInfratructure);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeInfratructure> findAll() {
        log.debug("Request to get all TypeInfratructures");
        return typeInfratructureRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeInfratructure> findOne(Long id) {
        log.debug("Request to get TypeInfratructure : {}", id);
        return typeInfratructureRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeInfratructure : {}", id);
        typeInfratructureRepository.deleteById(id);
    }
}
