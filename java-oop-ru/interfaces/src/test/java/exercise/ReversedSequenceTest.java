package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ReversedSequenceTest {

    @Test
    void reversedSequenceByStr() {
        var sample = "sample";
        var actual = new ReversedSequence(sample);

        assertThat(actual.toString()).isEqualTo("elpmas");
        assertThat(actual.charAt(1)).isEqualTo('l');
        assertThat(actual.length()).isEqualTo(6);
        assertThat(actual.subSequence(1, 4)).isEqualTo("lpm");
    }
}
