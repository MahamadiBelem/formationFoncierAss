package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ApprenantesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Apprenantes.class);
        Apprenantes apprenantes1 = new Apprenantes();
        apprenantes1.setId(1L);
        Apprenantes apprenantes2 = new Apprenantes();
        apprenantes2.setId(apprenantes1.getId());
        assertThat(apprenantes1).isEqualTo(apprenantes2);
        apprenantes2.setId(2L);
        assertThat(apprenantes1).isNotEqualTo(apprenantes2);
        apprenantes1.setId(null);
        assertThat(apprenantes1).isNotEqualTo(apprenantes2);
    }
}
