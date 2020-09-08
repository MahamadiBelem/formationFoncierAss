package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.Formations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Formations entity.
 */
@Repository
public interface FormationsRepository extends JpaRepository<Formations, Long> {

    @Query(value = "select distinct formations from Formations formations left join fetch formations.formations",
        countQuery = "select count(distinct formations) from Formations formations")
    Page<Formations> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct formations from Formations formations left join fetch formations.formations")
    List<Formations> findAllWithEagerRelationships();

    @Query("select formations from Formations formations left join fetch formations.formations where formations.id =:id")
    Optional<Formations> findOneWithEagerRelationships(@Param("id") Long id);
}
