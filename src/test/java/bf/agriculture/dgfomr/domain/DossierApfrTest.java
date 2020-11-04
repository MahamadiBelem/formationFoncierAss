package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class DossierApfrTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DossierApfr.class);
        DossierApfr dossierApfr1 = new DossierApfr();
        dossierApfr1.setId(1L);
        DossierApfr dossierApfr2 = new DossierApfr();
        dossierApfr2.setId(dossierApfr1.getId());
        assertThat(dossierApfr1).isEqualTo(dossierApfr2);
        dossierApfr2.setId(2L);
        assertThat(dossierApfr1).isNotEqualTo(dossierApfr2);
        dossierApfr1.setId(null);
        assertThat(dossierApfr1).isNotEqualTo(dossierApfr2);
    }
}
