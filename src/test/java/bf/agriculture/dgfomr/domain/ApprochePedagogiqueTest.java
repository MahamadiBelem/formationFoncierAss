package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ApprochePedagogiqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApprochePedagogique.class);
        ApprochePedagogique approchePedagogique1 = new ApprochePedagogique();
        approchePedagogique1.setId(1L);
        ApprochePedagogique approchePedagogique2 = new ApprochePedagogique();
        approchePedagogique2.setId(approchePedagogique1.getId());
        assertThat(approchePedagogique1).isEqualTo(approchePedagogique2);
        approchePedagogique2.setId(2L);
        assertThat(approchePedagogique1).isNotEqualTo(approchePedagogique2);
        approchePedagogique1.setId(null);
        assertThat(approchePedagogique1).isNotEqualTo(approchePedagogique2);
    }
}
