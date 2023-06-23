package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    private static final List<String> freeDomains = Arrays.asList("gmail.com",
                                                                  "yandex.ru",
                                                                  "hotmail.com");
    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                     .filter(email -> freeDomains.contains(email.split("@")[1]))
                     .count();
    }
}
// END
