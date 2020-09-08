package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class FormationsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Formations.class);
        Formations formations1 = new Formations();
        formations1.setId(1L);
        Formations formations2 = new Formations();
        formations2.setId(formations1.getId());
        assertThat(formations1).isEqualTo(formations2);
        formations2.setId(2L);
        assertThat(formations1).isNotEqualTo(formations2);
        formations1.setId(null);
        assertThat(formations1).isNotEqualTo(formations2);
    }
}
