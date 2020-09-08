package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class NiveauRecrutementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NiveauRecrutement.class);
        NiveauRecrutement niveauRecrutement1 = new NiveauRecrutement();
        niveauRecrutement1.setId(1L);
        NiveauRecrutement niveauRecrutement2 = new NiveauRecrutement();
        niveauRecrutement2.setId(niveauRecrutement1.getId());
        assertThat(niveauRecrutement1).isEqualTo(niveauRecrutement2);
        niveauRecrutement2.setId(2L);
        assertThat(niveauRecrutement1).isNotEqualTo(niveauRecrutement2);
        niveauRecrutement1.setId(null);
        assertThat(niveauRecrutement1).isNotEqualTo(niveauRecrutement2);
    }
}
