package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void testTake() {
        // BEGIN
        List<Integer> sampleList = List.of(1, 2, 3);

        // returns the correct number and order of the elements
        assertThat(App.take(sampleList, 0)).isEmpty();
        assertThat(App.take(sampleList, 2)).isEqualTo(List.of(1, 2));
        assertThat(App.take(sampleList, 3)).isEqualTo(sampleList);
        assertThat(App.take(sampleList, 9999)).isEqualTo(sampleList);
        // END
    }
}
