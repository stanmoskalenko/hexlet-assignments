package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private String path;
    private Map<String, String> storage;

    public Map<String, String> mergeData(String path, Map<String, String> initialValue) {
        Map<String, String> storage = new HashMap<>(initialValue);
        // Get data from file
        String content = Utils.readFile(path);
        var data = Utils.unserialize(content);
        storage.putAll(data);
        // Write data to file, with initialValue
        String updatedData = Utils.serialize(storage);
        Utils.writeFile(path, updatedData);

        return storage;
    }

    public FileKV(String path, Map<String, String> initialValue) {
        this.path = path;
        this.storage = mergeData(path, initialValue);
    }

    @Override
     public void set(String key, String value) {
        storage.put(key, value);
        var content = Utils.serialize(storage);
        Utils.writeFile(path, content);
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        var content = Utils.serialize(storage);
        Utils.writeFile(path, content);
    }

    @Override
    public Map<String, String> toMap() {
       var content = Utils.readFile(path);
        return Utils.unserialize(content);
    }
}
// END
