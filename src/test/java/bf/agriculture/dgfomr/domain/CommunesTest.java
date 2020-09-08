package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class CommunesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Communes.class);
        Communes communes1 = new Communes();
        communes1.setId(1L);
        Communes communes2 = new Communes();
        communes2.setId(communes1.getId());
        assertThat(communes1).isEqualTo(communes2);
        communes2.setId(2L);
        assertThat(communes1).isNotEqualTo(communes2);
        communes1.setId(null);
        assertThat(communes1).isNotEqualTo(communes2);
    }
}
