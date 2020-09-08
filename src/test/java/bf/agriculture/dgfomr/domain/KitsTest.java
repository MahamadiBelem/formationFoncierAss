package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class KitsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kits.class);
        Kits kits1 = new Kits();
        kits1.setId(1L);
        Kits kits2 = new Kits();
        kits2.setId(kits1.getId());
        assertThat(kits1).isEqualTo(kits2);
        kits2.setId(2L);
        assertThat(kits1).isNotEqualTo(kits2);
        kits1.setId(null);
        assertThat(kits1).isNotEqualTo(kits2);
    }
}
