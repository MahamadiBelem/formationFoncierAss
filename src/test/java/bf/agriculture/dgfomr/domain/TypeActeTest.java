package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class TypeActeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeActe.class);
        TypeActe typeActe1 = new TypeActe();
        typeActe1.setId(1L);
        TypeActe typeActe2 = new TypeActe();
        typeActe2.setId(typeActe1.getId());
        assertThat(typeActe1).isEqualTo(typeActe2);
        typeActe2.setId(2L);
        assertThat(typeActe1).isNotEqualTo(typeActe2);
        typeActe1.setId(null);
        assertThat(typeActe1).isNotEqualTo(typeActe2);
    }
}
