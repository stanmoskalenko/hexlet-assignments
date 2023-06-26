package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void enlargeEmptyArrayImage() {
        String[][] image = new String[0][];
        String[][] actual = App.enlargeArrayImage(image);
        assertThat(actual).isEmpty();
    }

    @Test
    void enlargeArrayImage() {
        String[][] image = {
            {"*", "*", "*"},
            {"*", "0", "*"},
            {"*", "0", "*"},
            {"*", "*", "*"},
        };

        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
            {"*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*"},
            {"*", "*", "0", "0", "*", "*"},
            {"*", "*", "0", "0", "*", "*"},
            {"*", "*", "0", "0", "*", "*"},
            {"*", "*", "0", "0", "*", "*"},
            {"*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*"}
        };

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void enlargeArrayImageWithOneSymbol() {
        String[][] image = {{"*"}};
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
            {"*", "*"},
            {"*", "*"}
        };

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void enlargeArrayImageWithSmile() {
        String[][] image = {
            {"👻", "👻", "👻"},
            {"👻", "👹", "👻"},
            {"👻", "👹", "👻"},
            {"👻", "👻", "👻"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
            {"👻", "👻", "👻", "👻", "👻", "👻"},
            {"👻", "👻", "👻", "👻", "👻", "👻"},
            {"👻", "👻", "👹", "👹", "👻", "👻"},
            {"👻", "👻", "👹", "👹", "👻", "👻"},
            {"👻", "👻", "👹", "👹", "👻", "👻"},
            {"👻", "👻", "👹", "👹", "👻", "👻"},
            {"👻", "👻", "👻", "👻", "👻", "👻"},
            {"👻", "👻", "👻", "👻", "👻", "👻"}
        };
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void enlargeArrayImageWithSymbolsSeq() {
        String[][] image = {
            {"oo", "oo", "oo"},
            {"oo", "++", "oo"},
            {"oo", "++", "oo"},
            {"oo", "oo", "oo"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
            {"oo", "oo", "oo", "oo", "oo", "oo"},
            {"oo", "oo", "oo", "oo", "oo", "oo"},
            {"oo", "oo", "++", "++", "oo", "oo"},
            {"oo", "oo", "++", "++", "oo", "oo"},
            {"oo", "oo", "++", "++", "oo", "oo"},
            {"oo", "oo", "++", "++", "oo", "oo"},
            {"oo", "oo", "oo", "oo", "oo", "oo"},
            {"oo", "oo", "oo", "oo", "oo", "oo"}
        };
        assertThat(actual).isEqualTo(expected);
    }
}
// END
