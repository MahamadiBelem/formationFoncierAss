package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class TypeFormationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeFormation.class);
        TypeFormation typeFormation1 = new TypeFormation();
        typeFormation1.setId(1L);
        TypeFormation typeFormation2 = new TypeFormation();
        typeFormation2.setId(typeFormation1.getId());
        assertThat(typeFormation1).isEqualTo(typeFormation2);
        typeFormation2.setId(2L);
        assertThat(typeFormation1).isNotEqualTo(typeFormation2);
        typeFormation1.setId(null);
        assertThat(typeFormation1).isNotEqualTo(typeFormation2);
    }
}
