package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.VillagesService;
import bf.agriculture.dgfomr.domain.Villages;
import bf.agriculture.dgfomr.repository.VillagesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Villages}.
 */
@Service
@Transactional
public class VillagesServiceImpl implements VillagesService {

    private final Logger log = LoggerFactory.getLogger(VillagesServiceImpl.class);

    private final VillagesRepository villagesRepository;

    public VillagesServiceImpl(VillagesRepository villagesRepository) {
        this.villagesRepository = villagesRepository;
    }

    @Override
    public Villages save(Villages villages) {
        log.debug("Request to save Villages : {}", villages);
        return villagesRepository.save(villages);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Villages> findAll(Pageable pageable) {
        log.debug("Request to get all Villages");
        return villagesRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Villages> findOne(Long id) {
        log.debug("Request to get Villages : {}", id);
        return villagesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Villages : {}", id);
        villagesRepository.deleteById(id);
    }
}
