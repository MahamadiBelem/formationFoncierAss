package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ProvincesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Provinces.class);
        Provinces provinces1 = new Provinces();
        provinces1.setId(1L);
        Provinces provinces2 = new Provinces();
        provinces2.setId(provinces1.getId());
        assertThat(provinces1).isEqualTo(provinces2);
        provinces2.setId(2L);
        assertThat(provinces1).isNotEqualTo(provinces2);
        provinces1.setId(null);
        assertThat(provinces1).isNotEqualTo(provinces2);
    }
}
