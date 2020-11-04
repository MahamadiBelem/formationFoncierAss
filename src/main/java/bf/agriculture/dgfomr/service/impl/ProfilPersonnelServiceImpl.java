package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ProfilPersonnelService;
import bf.agriculture.dgfomr.domain.ProfilPersonnel;
import bf.agriculture.dgfomr.repository.ProfilPersonnelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProfilPersonnel}.
 */
@Service
@Transactional
public class ProfilPersonnelServiceImpl implements ProfilPersonnelService {

    private final Logger log = LoggerFactory.getLogger(ProfilPersonnelServiceImpl.class);

    private final ProfilPersonnelRepository profilPersonnelRepository;

    public ProfilPersonnelServiceImpl(ProfilPersonnelRepository profilPersonnelRepository) {
        this.profilPersonnelRepository = profilPersonnelRepository;
    }

    @Override
    public ProfilPersonnel save(ProfilPersonnel profilPersonnel) {
        log.debug("Request to save ProfilPersonnel : {}", profilPersonnel);
        return profilPersonnelRepository.save(profilPersonnel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfilPersonnel> findAll() {
        log.debug("Request to get all ProfilPersonnels");
        return profilPersonnelRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilPersonnel> findOne(Long id) {
        log.debug("Request to get ProfilPersonnel : {}", id);
        return profilPersonnelRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProfilPersonnel : {}", id);
        profilPersonnelRepository.deleteById(id);
    }
}
