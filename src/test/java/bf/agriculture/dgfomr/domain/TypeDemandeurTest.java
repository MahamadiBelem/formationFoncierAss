package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class TypeDemandeurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDemandeur.class);
        TypeDemandeur typeDemandeur1 = new TypeDemandeur();
        typeDemandeur1.setId(1L);
        TypeDemandeur typeDemandeur2 = new TypeDemandeur();
        typeDemandeur2.setId(typeDemandeur1.getId());
        assertThat(typeDemandeur1).isEqualTo(typeDemandeur2);
        typeDemandeur2.setId(2L);
        assertThat(typeDemandeur1).isNotEqualTo(typeDemandeur2);
        typeDemandeur1.setId(null);
        assertThat(typeDemandeur1).isNotEqualTo(typeDemandeur2);
    }
}
