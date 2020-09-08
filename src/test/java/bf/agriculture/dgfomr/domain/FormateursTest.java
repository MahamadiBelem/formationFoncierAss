package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class FormateursTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Formateurs.class);
        Formateurs formateurs1 = new Formateurs();
        formateurs1.setId(1L);
        Formateurs formateurs2 = new Formateurs();
        formateurs2.setId(formateurs1.getId());
        assertThat(formateurs1).isEqualTo(formateurs2);
        formateurs2.setId(2L);
        assertThat(formateurs1).isNotEqualTo(formateurs2);
        formateurs1.setId(null);
        assertThat(formateurs1).isNotEqualTo(formateurs2);
    }
}
