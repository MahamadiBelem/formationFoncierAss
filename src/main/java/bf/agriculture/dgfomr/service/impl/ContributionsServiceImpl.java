package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ContributionsService;
import bf.agriculture.dgfomr.domain.Contributions;
import bf.agriculture.dgfomr.repository.ContributionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Contributions}.
 */
@Service
@Transactional
public class ContributionsServiceImpl implements ContributionsService {

    private final Logger log = LoggerFactory.getLogger(ContributionsServiceImpl.class);

    private final ContributionsRepository contributionsRepository;

    public ContributionsServiceImpl(ContributionsRepository contributionsRepository) {
        this.contributionsRepository = contributionsRepository;
    }

    @Override
    public Contributions save(Contributions contributions) {
        log.debug("Request to save Contributions : {}", contributions);
        return contributionsRepository.save(contributions);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contributions> findAll() {
        log.debug("Request to get all Contributions");
        return contributionsRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Contributions> findOne(Long id) {
        log.debug("Request to get Contributions : {}", id);
        return contributionsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contributions : {}", id);
        contributionsRepository.deleteById(id);
    }
}
