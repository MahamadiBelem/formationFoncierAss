package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class TypeInfratructureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeInfratructure.class);
        TypeInfratructure typeInfratructure1 = new TypeInfratructure();
        typeInfratructure1.setId(1L);
        TypeInfratructure typeInfratructure2 = new TypeInfratructure();
        typeInfratructure2.setId(typeInfratructure1.getId());
        assertThat(typeInfratructure1).isEqualTo(typeInfratructure2);
        typeInfratructure2.setId(2L);
        assertThat(typeInfratructure1).isNotEqualTo(typeInfratructure2);
        typeInfratructure1.setId(null);
        assertThat(typeInfratructure1).isNotEqualTo(typeInfratructure2);
    }
}
