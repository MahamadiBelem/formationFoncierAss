package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ActiviteInstallationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActiviteInstallation.class);
        ActiviteInstallation activiteInstallation1 = new ActiviteInstallation();
        activiteInstallation1.setId(1L);
        ActiviteInstallation activiteInstallation2 = new ActiviteInstallation();
        activiteInstallation2.setId(activiteInstallation1.getId());
        assertThat(activiteInstallation1).isEqualTo(activiteInstallation2);
        activiteInstallation2.setId(2L);
        assertThat(activiteInstallation1).isNotEqualTo(activiteInstallation2);
        activiteInstallation1.setId(null);
        assertThat(activiteInstallation1).isNotEqualTo(activiteInstallation2);
    }
}
