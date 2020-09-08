package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class SortiePromotionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SortiePromotion.class);
        SortiePromotion sortiePromotion1 = new SortiePromotion();
        sortiePromotion1.setId(1L);
        SortiePromotion sortiePromotion2 = new SortiePromotion();
        sortiePromotion2.setId(sortiePromotion1.getId());
        assertThat(sortiePromotion1).isEqualTo(sortiePromotion2);
        sortiePromotion2.setId(2L);
        assertThat(sortiePromotion1).isNotEqualTo(sortiePromotion2);
        sortiePromotion1.setId(null);
        assertThat(sortiePromotion1).isNotEqualTo(sortiePromotion2);
    }
}
