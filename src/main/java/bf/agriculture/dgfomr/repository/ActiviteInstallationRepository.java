package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.ActiviteInstallation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ActiviteInstallation entity.
 */
@Repository
public interface ActiviteInstallationRepository extends JpaRepository<ActiviteInstallation, Long> {

    @Query(value = "select distinct activiteInstallation from ActiviteInstallation activiteInstallation left join fetch activiteInstallation.kits",
        countQuery = "select count(distinct activiteInstallation) from ActiviteInstallation activiteInstallation")
    Page<ActiviteInstallation> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct activiteInstallation from ActiviteInstallation activiteInstallation left join fetch activiteInstallation.kits")
    List<ActiviteInstallation> findAllWithEagerRelationships();

    @Query("select activiteInstallation from ActiviteInstallation activiteInstallation left join fetch activiteInstallation.kits where activiteInstallation.id =:id")
    Optional<ActiviteInstallation> findOneWithEagerRelationships(@Param("id") Long id);
}
