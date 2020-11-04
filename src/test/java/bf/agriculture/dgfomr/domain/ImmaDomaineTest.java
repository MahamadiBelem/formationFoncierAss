package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ImmaDomaineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImmaDomaine.class);
        ImmaDomaine immaDomaine1 = new ImmaDomaine();
        immaDomaine1.setId(1L);
        ImmaDomaine immaDomaine2 = new ImmaDomaine();
        immaDomaine2.setId(immaDomaine1.getId());
        assertThat(immaDomaine1).isEqualTo(immaDomaine2);
        immaDomaine2.setId(2L);
        assertThat(immaDomaine1).isNotEqualTo(immaDomaine2);
        immaDomaine1.setId(null);
        assertThat(immaDomaine1).isNotEqualTo(immaDomaine2);
    }
}
