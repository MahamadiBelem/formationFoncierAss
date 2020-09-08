package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class NiveauInstructionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NiveauInstruction.class);
        NiveauInstruction niveauInstruction1 = new NiveauInstruction();
        niveauInstruction1.setId(1L);
        NiveauInstruction niveauInstruction2 = new NiveauInstruction();
        niveauInstruction2.setId(niveauInstruction1.getId());
        assertThat(niveauInstruction1).isEqualTo(niveauInstruction2);
        niveauInstruction2.setId(2L);
        assertThat(niveauInstruction1).isNotEqualTo(niveauInstruction2);
        niveauInstruction1.setId(null);
        assertThat(niveauInstruction1).isNotEqualTo(niveauInstruction2);
    }
}
