package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class FormationCentreFormationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormationCentreFormation.class);
        FormationCentreFormation formationCentreFormation1 = new FormationCentreFormation();
        formationCentreFormation1.setId(1L);
        FormationCentreFormation formationCentreFormation2 = new FormationCentreFormation();
        formationCentreFormation2.setId(formationCentreFormation1.getId());
        assertThat(formationCentreFormation1).isEqualTo(formationCentreFormation2);
        formationCentreFormation2.setId(2L);
        assertThat(formationCentreFormation1).isNotEqualTo(formationCentreFormation2);
        formationCentreFormation1.setId(null);
        assertThat(formationCentreFormation1).isNotEqualTo(formationCentreFormation2);
    }
}
