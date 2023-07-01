package exercise;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();
    private String initKey = "key";
    private String initValue = "value";
    private Map<String, String> initMap = Map.of(initKey, initValue);

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
        // Clear test storage before each test
        Utils.writeFile(filepath.toString(), "");
    }

    // BEGIN
   @Test
   void getFullStorage() {
    KeyValueStorage storage = new FileKV(filepath.toString(), initMap);
    var expected = Map.of(initKey, initValue);

    assertEquals(storage.toMap(), expected);
    assertEquals(storage.get(initKey, "hexlet"), "value");
    assertEquals(storage.get("key1", "hexlet"), "hexlet");
   }

   @Test
   void fillStorage() {
    KeyValueStorage storage = new FileKV(filepath.toString(), initMap);
    var newKey = "key1";
    var newValue = "value1";
    var fillStorage = Map.of(initKey, initValue,
                             newKey, newValue);

    storage.set(newKey, newValue);
    assertEquals(storage.get(initKey, "hexlet"), "value");
    assertEquals(storage.get(newKey, "hexlet"), "value1");
    assertEquals(storage.get("testDefaultValue", "hexlet"), "hexlet");
    assertEquals(storage.toMap(), fillStorage);

    storage.unset(newKey);
    assertEquals(storage.get(initKey, "hexlet"), "value");
    assertEquals(storage.get(newKey, "hexlet"), "hexlet");
    assertEquals(storage.toMap(), initMap);
   }
    // END
}
