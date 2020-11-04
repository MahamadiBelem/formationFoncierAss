package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class CaracteristiqueSfrTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaracteristiqueSfr.class);
        CaracteristiqueSfr caracteristiqueSfr1 = new CaracteristiqueSfr();
        caracteristiqueSfr1.setId(1L);
        CaracteristiqueSfr caracteristiqueSfr2 = new CaracteristiqueSfr();
        caracteristiqueSfr2.setId(caracteristiqueSfr1.getId());
        assertThat(caracteristiqueSfr1).isEqualTo(caracteristiqueSfr2);
        caracteristiqueSfr2.setId(2L);
        assertThat(caracteristiqueSfr1).isNotEqualTo(caracteristiqueSfr2);
        caracteristiqueSfr1.setId(null);
        assertThat(caracteristiqueSfr1).isNotEqualTo(caracteristiqueSfr2);
    }
}
