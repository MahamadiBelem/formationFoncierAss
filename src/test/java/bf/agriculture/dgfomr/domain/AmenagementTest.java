package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class AmenagementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Amenagement.class);
        Amenagement amenagement1 = new Amenagement();
        amenagement1.setId(1L);
        Amenagement amenagement2 = new Amenagement();
        amenagement2.setId(amenagement1.getId());
        assertThat(amenagement1).isEqualTo(amenagement2);
        amenagement2.setId(2L);
        assertThat(amenagement1).isNotEqualTo(amenagement2);
        amenagement1.setId(null);
        assertThat(amenagement1).isNotEqualTo(amenagement2);
    }
}
