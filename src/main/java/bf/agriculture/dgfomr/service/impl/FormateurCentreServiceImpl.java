package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.FormateurCentreService;
import bf.agriculture.dgfomr.domain.FormateurCentre;
import bf.agriculture.dgfomr.repository.FormateurCentreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormateurCentre}.
 */
@Service
@Transactional
public class FormateurCentreServiceImpl implements FormateurCentreService {

    private final Logger log = LoggerFactory.getLogger(FormateurCentreServiceImpl.class);

    private final FormateurCentreRepository formateurCentreRepository;

    public FormateurCentreServiceImpl(FormateurCentreRepository formateurCentreRepository) {
        this.formateurCentreRepository = formateurCentreRepository;
    }

    @Override
    public FormateurCentre save(FormateurCentre formateurCentre) {
        log.debug("Request to save FormateurCentre : {}", formateurCentre);
        return formateurCentreRepository.save(formateurCentre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormateurCentre> findAll() {
        log.debug("Request to get all FormateurCentres");
        return formateurCentreRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FormateurCentre> findOne(Long id) {
        log.debug("Request to get FormateurCentre : {}", id);
        return formateurCentreRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormateurCentre : {}", id);
        formateurCentreRepository.deleteById(id);
    }
}
