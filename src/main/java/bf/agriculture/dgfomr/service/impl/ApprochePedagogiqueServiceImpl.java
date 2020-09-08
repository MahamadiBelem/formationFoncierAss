package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.ApprochePedagogiqueService;
import bf.agriculture.dgfomr.domain.ApprochePedagogique;
import bf.agriculture.dgfomr.repository.ApprochePedagogiqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ApprochePedagogique}.
 */
@Service
@Transactional
public class ApprochePedagogiqueServiceImpl implements ApprochePedagogiqueService {

    private final Logger log = LoggerFactory.getLogger(ApprochePedagogiqueServiceImpl.class);

    private final ApprochePedagogiqueRepository approchePedagogiqueRepository;

    public ApprochePedagogiqueServiceImpl(ApprochePedagogiqueRepository approchePedagogiqueRepository) {
        this.approchePedagogiqueRepository = approchePedagogiqueRepository;
    }

    @Override
    public ApprochePedagogique save(ApprochePedagogique approchePedagogique) {
        log.debug("Request to save ApprochePedagogique : {}", approchePedagogique);
        return approchePedagogiqueRepository.save(approchePedagogique);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApprochePedagogique> findAll() {
        log.debug("Request to get all ApprochePedagogiques");
        return approchePedagogiqueRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ApprochePedagogique> findOne(Long id) {
        log.debug("Request to get ApprochePedagogique : {}", id);
        return approchePedagogiqueRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApprochePedagogique : {}", id);
        approchePedagogiqueRepository.deleteById(id);
    }
}
