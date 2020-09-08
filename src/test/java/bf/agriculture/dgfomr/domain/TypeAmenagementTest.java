package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class TypeAmenagementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAmenagement.class);
        TypeAmenagement typeAmenagement1 = new TypeAmenagement();
        typeAmenagement1.setId(1L);
        TypeAmenagement typeAmenagement2 = new TypeAmenagement();
        typeAmenagement2.setId(typeAmenagement1.getId());
        assertThat(typeAmenagement1).isEqualTo(typeAmenagement2);
        typeAmenagement2.setId(2L);
        assertThat(typeAmenagement1).isNotEqualTo(typeAmenagement2);
        typeAmenagement1.setId(null);
        assertThat(typeAmenagement1).isNotEqualTo(typeAmenagement2);
    }
}
