package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class PromoteursTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Promoteurs.class);
        Promoteurs promoteurs1 = new Promoteurs();
        promoteurs1.setId(1L);
        Promoteurs promoteurs2 = new Promoteurs();
        promoteurs2.setId(promoteurs1.getId());
        assertThat(promoteurs1).isEqualTo(promoteurs2);
        promoteurs2.setId(2L);
        assertThat(promoteurs1).isNotEqualTo(promoteurs2);
        promoteurs1.setId(null);
        assertThat(promoteurs1).isNotEqualTo(promoteurs2);
    }
}
