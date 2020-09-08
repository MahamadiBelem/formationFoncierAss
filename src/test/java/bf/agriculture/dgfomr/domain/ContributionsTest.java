package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ContributionsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contributions.class);
        Contributions contributions1 = new Contributions();
        contributions1.setId(1L);
        Contributions contributions2 = new Contributions();
        contributions2.setId(contributions1.getId());
        assertThat(contributions1).isEqualTo(contributions2);
        contributions2.setId(2L);
        assertThat(contributions1).isNotEqualTo(contributions2);
        contributions1.setId(null);
        assertThat(contributions1).isNotEqualTo(contributions2);
    }
}
