package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class SpecialitesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specialites.class);
        Specialites specialites1 = new Specialites();
        specialites1.setId(1L);
        Specialites specialites2 = new Specialites();
        specialites2.setId(specialites1.getId());
        assertThat(specialites1).isEqualTo(specialites2);
        specialites2.setId(2L);
        assertThat(specialites1).isNotEqualTo(specialites2);
        specialites1.setId(null);
        assertThat(specialites1).isNotEqualTo(specialites2);
    }
}
