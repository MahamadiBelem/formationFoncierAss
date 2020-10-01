package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class FormateurCentreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormateurCentre.class);
        FormateurCentre formateurCentre1 = new FormateurCentre();
        formateurCentre1.setId(1L);
        FormateurCentre formateurCentre2 = new FormateurCentre();
        formateurCentre2.setId(formateurCentre1.getId());
        assertThat(formateurCentre1).isEqualTo(formateurCentre2);
        formateurCentre2.setId(2L);
        assertThat(formateurCentre1).isNotEqualTo(formateurCentre2);
        formateurCentre1.setId(null);
        assertThat(formateurCentre1).isNotEqualTo(formateurCentre2);
    }
}
