package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class FormationSFRTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormationSFR.class);
        FormationSFR formationSFR1 = new FormationSFR();
        formationSFR1.setId(1L);
        FormationSFR formationSFR2 = new FormationSFR();
        formationSFR2.setId(formationSFR1.getId());
        assertThat(formationSFR1).isEqualTo(formationSFR2);
        formationSFR2.setId(2L);
        assertThat(formationSFR1).isNotEqualTo(formationSFR2);
        formationSFR1.setId(null);
        assertThat(formationSFR1).isNotEqualTo(formationSFR2);
    }
}
