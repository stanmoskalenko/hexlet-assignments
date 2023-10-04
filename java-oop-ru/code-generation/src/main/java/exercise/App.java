package exercise;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {
        var json = Car.serialize(car);
        Files.writeString(path, json, StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws IOException {
        var json = Files.readString(path);

        return Car.unserialize(json);
    }
}
// END
