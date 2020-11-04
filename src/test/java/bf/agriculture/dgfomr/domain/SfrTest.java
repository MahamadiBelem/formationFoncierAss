package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class SfrTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sfr.class);
        Sfr sfr1 = new Sfr();
        sfr1.setId(1L);
        Sfr sfr2 = new Sfr();
        sfr2.setId(sfr1.getId());
        assertThat(sfr1).isEqualTo(sfr2);
        sfr2.setId(2L);
        assertThat(sfr1).isNotEqualTo(sfr2);
        sfr1.setId(null);
        assertThat(sfr1).isNotEqualTo(sfr2);
    }
}
