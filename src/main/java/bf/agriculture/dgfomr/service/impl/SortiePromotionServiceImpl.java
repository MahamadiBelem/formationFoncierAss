package bf.agriculture.dgfomr.service.impl;

import bf.agriculture.dgfomr.service.SortiePromotionService;
import bf.agriculture.dgfomr.domain.SortiePromotion;
import bf.agriculture.dgfomr.repository.SortiePromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SortiePromotion}.
 */
@Service
@Transactional
public class SortiePromotionServiceImpl implements SortiePromotionService {

    private final Logger log = LoggerFactory.getLogger(SortiePromotionServiceImpl.class);

    private final SortiePromotionRepository sortiePromotionRepository;

    public SortiePromotionServiceImpl(SortiePromotionRepository sortiePromotionRepository) {
        this.sortiePromotionRepository = sortiePromotionRepository;
    }

    @Override
    public SortiePromotion save(SortiePromotion sortiePromotion) {
        log.debug("Request to save SortiePromotion : {}", sortiePromotion);
        return sortiePromotionRepository.save(sortiePromotion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SortiePromotion> findAll() {
        log.debug("Request to get all SortiePromotions");
        return sortiePromotionRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SortiePromotion> findOne(Long id) {
        log.debug("Request to get SortiePromotion : {}", id);
        return sortiePromotionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SortiePromotion : {}", id);
        sortiePromotionRepository.deleteById(id);
    }
}
