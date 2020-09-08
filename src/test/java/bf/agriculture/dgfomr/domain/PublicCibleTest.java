package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class PublicCibleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PublicCible.class);
        PublicCible publicCible1 = new PublicCible();
        publicCible1.setId(1L);
        PublicCible publicCible2 = new PublicCible();
        publicCible2.setId(publicCible1.getId());
        assertThat(publicCible1).isEqualTo(publicCible2);
        publicCible2.setId(2L);
        assertThat(publicCible1).isNotEqualTo(publicCible2);
        publicCible1.setId(null);
        assertThat(publicCible1).isNotEqualTo(publicCible2);
    }
}
