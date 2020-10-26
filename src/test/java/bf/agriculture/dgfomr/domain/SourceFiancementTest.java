package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class SourceFiancementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SourceFiancement.class);
        SourceFiancement sourceFiancement1 = new SourceFiancement();
        sourceFiancement1.setId(1L);
        SourceFiancement sourceFiancement2 = new SourceFiancement();
        sourceFiancement2.setId(sourceFiancement1.getId());
        assertThat(sourceFiancement1).isEqualTo(sourceFiancement2);
        sourceFiancement2.setId(2L);
        assertThat(sourceFiancement1).isNotEqualTo(sourceFiancement2);
        sourceFiancement1.setId(null);
        assertThat(sourceFiancement1).isNotEqualTo(sourceFiancement2);
    }
}
