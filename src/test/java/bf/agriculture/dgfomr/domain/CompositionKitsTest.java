package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class CompositionKitsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompositionKits.class);
        CompositionKits compositionKits1 = new CompositionKits();
        compositionKits1.setId(1L);
        CompositionKits compositionKits2 = new CompositionKits();
        compositionKits2.setId(compositionKits1.getId());
        assertThat(compositionKits1).isEqualTo(compositionKits2);
        compositionKits2.setId(2L);
        assertThat(compositionKits1).isNotEqualTo(compositionKits2);
        compositionKits1.setId(null);
        assertThat(compositionKits1).isNotEqualTo(compositionKits2);
    }
}
