package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private String storagePath;
    private Map<String, String> fileStorage;

    public Map<String, String> mergeData(String storagePath,
                                         Map<String, String> initialValue) {
        Map<String, String> fileStorage = new HashMap<>(initialValue);
        // Get data from file
        String content = Utils.readFile(storagePath);
        var data = Utils.unserialize(content);
        fileStorage.putAll(data);
        // Write data to file, with initialValue
        String updatedData = Utils.serialize(fileStorage);
        Utils.writeFile(storagePath, updatedData);

        return fileStorage;
    }

    public FileKV(String storagePath, Map<String, String> initialValue) {
        this.storagePath = storagePath;
        this.fileStorage = mergeData(storagePath, initialValue);
    }

    @Override
     public void set(String key, String value) {
        fileStorage.put(key, value);
        var content = Utils.serialize(fileStorage);
        Utils.writeFile(storagePath, content);
    }

    @Override
    public String get(String key, String defaultValue) {
        return fileStorage.getOrDefault(key, defaultValue);
    }

    @Override
    public void unset(String key) {
        fileStorage.remove(key);
        var content = Utils.serialize(fileStorage);
        Utils.writeFile(storagePath, content);
    }

    @Override
    public Map<String, String> toMap() {
       var content = Utils.readFile(storagePath);
        return Utils.unserialize(content);
    }
}
// END
