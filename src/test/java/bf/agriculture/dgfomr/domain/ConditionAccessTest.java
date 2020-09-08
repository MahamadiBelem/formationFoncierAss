package bf.agriculture.dgfomr.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import bf.agriculture.dgfomr.web.rest.TestUtil;

public class ConditionAccessTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConditionAccess.class);
        ConditionAccess conditionAccess1 = new ConditionAccess();
        conditionAccess1.setId(1L);
        ConditionAccess conditionAccess2 = new ConditionAccess();
        conditionAccess2.setId(conditionAccess1.getId());
        assertThat(conditionAccess1).isEqualTo(conditionAccess2);
        conditionAccess2.setId(2L);
        assertThat(conditionAccess1).isNotEqualTo(conditionAccess2);
        conditionAccess1.setId(null);
        assertThat(conditionAccess1).isNotEqualTo(conditionAccess2);
    }
}
