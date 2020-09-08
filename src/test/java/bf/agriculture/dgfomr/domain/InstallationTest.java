package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class InstallationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Installation.class);
        Installation installation1 = new Installation();
        installation1.setId(1L);
        Installation installation2 = new Installation();
        installation2.setId(installation1.getId());
        assertThat(installation1).isEqualTo(installation2);
        installation2.setId(2L);
        assertThat(installation1).isNotEqualTo(installation2);
        installation1.setId(null);
        assertThat(installation1).isNotEqualTo(installation2);
    }
}
