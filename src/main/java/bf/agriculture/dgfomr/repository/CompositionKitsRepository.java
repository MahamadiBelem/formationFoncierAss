package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.CompositionKits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the CompositionKits entity.
 */
@Repository
public interface CompositionKitsRepository extends JpaRepository<CompositionKits, Long> {

    @Query(value = "select distinct compositionKits from CompositionKits compositionKits left join fetch compositionKits.kits",
        countQuery = "select count(distinct compositionKits) from CompositionKits compositionKits")
    Page<CompositionKits> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct compositionKits from CompositionKits compositionKits left join fetch compositionKits.kits")
    List<CompositionKits> findAllWithEagerRelationships();

    @Query("select compositionKits from CompositionKits compositionKits left join fetch compositionKits.kits where compositionKits.id =:id")
    Optional<CompositionKits> findOneWithEagerRelationships(@Param("id") Long id);
}
