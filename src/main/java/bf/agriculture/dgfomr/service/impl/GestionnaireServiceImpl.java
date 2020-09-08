package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.GestionnaireService;
import bf.agriculture.dgfomr.domain.Gestionnaire;
import bf.agriculture.dgfomr.repository.GestionnaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Gestionnaire}.
 */
@Service
@Transactional
public class GestionnaireServiceImpl implements GestionnaireService {

    private final Logger log = LoggerFactory.getLogger(GestionnaireServiceImpl.class);

    private final GestionnaireRepository gestionnaireRepository;

    public GestionnaireServiceImpl(GestionnaireRepository gestionnaireRepository) {
        this.gestionnaireRepository = gestionnaireRepository;
    }

    @Override
    public Gestionnaire save(Gestionnaire gestionnaire) {
        log.debug("Request to save Gestionnaire : {}", gestionnaire);
        return gestionnaireRepository.save(gestionnaire);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gestionnaire> findAll() {
        log.debug("Request to get all Gestionnaires");
        return gestionnaireRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Gestionnaire> findOne(Long id) {
        log.debug("Request to get Gestionnaire : {}", id);
        return gestionnaireRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gestionnaire : {}", id);
        gestionnaireRepository.deleteById(id);
    }
}
