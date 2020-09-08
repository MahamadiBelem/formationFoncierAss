package bf.agriculture.dgfomr.repository;

import bf.agriculture.dgfomr.domain.NiveauInstruction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NiveauInstruction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiveauInstructionRepository extends JpaRepository<NiveauInstruction, Long> {
}
