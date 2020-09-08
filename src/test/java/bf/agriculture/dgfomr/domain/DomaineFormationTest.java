package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class DomaineFormationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DomaineFormation.class);
        DomaineFormation domaineFormation1 = new DomaineFormation();
        domaineFormation1.setId(1L);
        DomaineFormation domaineFormation2 = new DomaineFormation();
        domaineFormation2.setId(domaineFormation1.getId());
        assertThat(domaineFormation1).isEqualTo(domaineFormation2);
        domaineFormation2.setId(2L);
        assertThat(domaineFormation1).isNotEqualTo(domaineFormation2);
        domaineFormation1.setId(null);
        assertThat(domaineFormation1).isNotEqualTo(domaineFormation2);
    }
}
