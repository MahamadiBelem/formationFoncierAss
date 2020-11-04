package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ProfilPersonnelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilPersonnel.class);
        ProfilPersonnel profilPersonnel1 = new ProfilPersonnel();
        profilPersonnel1.setId(1L);
        ProfilPersonnel profilPersonnel2 = new ProfilPersonnel();
        profilPersonnel2.setId(profilPersonnel1.getId());
        assertThat(profilPersonnel1).isEqualTo(profilPersonnel2);
        profilPersonnel2.setId(2L);
        assertThat(profilPersonnel1).isNotEqualTo(profilPersonnel2);
        profilPersonnel1.setId(null);
        assertThat(profilPersonnel1).isNotEqualTo(profilPersonnel2);
    }
}
