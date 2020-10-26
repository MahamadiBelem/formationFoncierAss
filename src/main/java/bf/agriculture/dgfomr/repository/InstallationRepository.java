package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Installation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Installation entity.
 */
@Repository
public interface InstallationRepository extends JpaRepository<Installation, Long> {

    @Query(value = "select distinct installation from Installation installation left join fetch installation.activiteinstallations left join fetch installation.sourceIntallations",
        countQuery = "select count(distinct installation) from Installation installation")
    Page<Installation> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct installation from Installation installation left join fetch installation.activiteinstallations left join fetch installation.sourceIntallations")
    List<Installation> findAllWithEagerRelationships();

    @Query("select installation from Installation installation left join fetch installation.activiteinstallations left join fetch installation.sourceIntallations where installation.id =:id")
    Optional<Installation> findOneWithEagerRelationships(@Param("id") Long id);
}
