package exercise;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var entrySet = storage.toMap().entrySet();

        for (var entry : entrySet) {
            storage.unset(entry.getKey());
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
