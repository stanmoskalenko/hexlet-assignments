package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
public class App {
    public static CompletableFuture<String> unionFiles(
            String firstFileName,
            String secondFileName,
            String resultFileName
    ) {
        CompletableFuture<String> futureReadFirstFile = CompletableFuture.supplyAsync(() -> {
            try {
                var path = Path.of(firstFileName);
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> futureReadSecondFile = CompletableFuture.supplyAsync(() -> {
            try {
                var path = Path.of(secondFileName);
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return futureReadFirstFile.thenCombine(
                futureReadSecondFile, (firstValue, secondValue) -> {
                    try {
                        var resultPath = Path.of(resultFileName);
                        var resultValue = firstValue + " " + secondValue;
                        Files.writeString(resultPath, resultValue);
                        return resultValue;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String dir) {
        Path path = Path.of(dir);
        return CompletableFuture.supplyAsync(() -> {
            try {
                try (var listPath = Files.list(path)) {
                    return listPath.filter(Files::isRegularFile)
                            .mapToLong(file -> {
                                try {
                                    return Files.size(file);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .sum();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

