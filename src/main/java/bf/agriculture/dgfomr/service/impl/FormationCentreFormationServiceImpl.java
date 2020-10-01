package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.FormationCentreFormationService;
import bf.agriculture.dgfomr.domain.FormationCentreFormation;
import bf.agriculture.dgfomr.repository.FormationCentreFormationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormationCentreFormation}.
 */
@Service
@Transactional
public class FormationCentreFormationServiceImpl implements FormationCentreFormationService {

    private final Logger log = LoggerFactory.getLogger(FormationCentreFormationServiceImpl.class);

    private final FormationCentreFormationRepository formationCentreFormationRepository;

    public FormationCentreFormationServiceImpl(FormationCentreFormationRepository formationCentreFormationRepository) {
        this.formationCentreFormationRepository = formationCentreFormationRepository;
    }

    @Override
    public FormationCentreFormation save(FormationCentreFormation formationCentreFormation) {
        log.debug("Request to save FormationCentreFormation : {}", formationCentreFormation);
        return formationCentreFormationRepository.save(formationCentreFormation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormationCentreFormation> findAll() {
        log.debug("Request to get all FormationCentreFormations");
        return formationCentreFormationRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FormationCentreFormation> findOne(Long id) {
        log.debug("Request to get FormationCentreFormation : {}", id);
        return formationCentreFormationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormationCentreFormation : {}", id);
        formationCentreFormationRepository.deleteById(id);
    }
}
