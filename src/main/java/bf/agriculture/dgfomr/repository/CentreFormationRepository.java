package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.CentreFormation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the CentreFormation entity.
 */
@Repository
public interface CentreFormationRepository extends JpaRepository<CentreFormation, Long> {

    @Query(value = "select distinct centreFormation from CentreFormation centreFormation left join fetch centreFormation.approchepedagogiques left join fetch centreFormation.publiccibles left join fetch centreFormation.specialites left join fetch centreFormation.domaineformations left join fetch centreFormation.contributions left join fetch centreFormation.niveaurecrutements left join fetch centreFormation.formateurs left join fetch centreFormation.conditionaccesses left join fetch centreFormation.formations",
        countQuery = "select count(distinct centreFormation) from CentreFormation centreFormation")
    Page<CentreFormation> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct centreFormation from CentreFormation centreFormation left join fetch centreFormation.approchepedagogiques left join fetch centreFormation.publiccibles left join fetch centreFormation.specialites left join fetch centreFormation.domaineformations left join fetch centreFormation.contributions left join fetch centreFormation.niveaurecrutements left join fetch centreFormation.formateurs left join fetch centreFormation.conditionaccesses left join fetch centreFormation.formations")
    List<CentreFormation> findAllWithEagerRelationships();

    @Query("select centreFormation from CentreFormation centreFormation left join fetch centreFormation.approchepedagogiques left join fetch centreFormation.publiccibles left join fetch centreFormation.specialites left join fetch centreFormation.domaineformations left join fetch centreFormation.contributions left join fetch centreFormation.niveaurecrutements left join fetch centreFormation.formateurs left join fetch centreFormation.conditionaccesses left join fetch centreFormation.formations where centreFormation.id =:id")
    Optional<CentreFormation> findOneWithEagerRelationships(@Param("id") Long id);
}
