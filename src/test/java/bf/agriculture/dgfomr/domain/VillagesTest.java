package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class VillagesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Villages.class);
        Villages villages1 = new Villages();
        villages1.setId(1L);
        Villages villages2 = new Villages();
        villages2.setId(villages1.getId());
        assertThat(villages1).isEqualTo(villages2);
        villages2.setId(2L);
        assertThat(villages1).isNotEqualTo(villages2);
        villages1.setId(null);
        assertThat(villages1).isNotEqualTo(villages2);
    }
}
