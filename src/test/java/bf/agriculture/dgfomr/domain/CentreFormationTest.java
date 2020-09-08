package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class CentreFormationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentreFormation.class);
        CentreFormation centreFormation1 = new CentreFormation();
        centreFormation1.setId(1L);
        CentreFormation centreFormation2 = new CentreFormation();
        centreFormation2.setId(centreFormation1.getId());
        assertThat(centreFormation1).isEqualTo(centreFormation2);
        centreFormation2.setId(2L);
        assertThat(centreFormation1).isNotEqualTo(centreFormation2);
        centreFormation1.setId(null);
        assertThat(centreFormation1).isNotEqualTo(centreFormation2);
    }
}
