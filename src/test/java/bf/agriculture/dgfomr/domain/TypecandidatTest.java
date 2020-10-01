package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class TypecandidatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Typecandidat.class);
        Typecandidat typecandidat1 = new Typecandidat();
        typecandidat1.setId(1L);
        Typecandidat typecandidat2 = new Typecandidat();
        typecandidat2.setId(typecandidat1.getId());
        assertThat(typecandidat1).isEqualTo(typecandidat2);
        typecandidat2.setId(2L);
        assertThat(typecandidat1).isNotEqualTo(typecandidat2);
        typecandidat1.setId(null);
        assertThat(typecandidat1).isNotEqualTo(typecandidat2);
    }
}
