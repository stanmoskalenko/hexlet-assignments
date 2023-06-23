package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    private static final List<String> FREE_DOMAINS = Arrays.asList("gmail.com",
                                                                   "yandex.ru",
                                                                   "hotmail.com");
    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                     .filter(email -> FREE_DOMAINS.contains(email.split("@")[1]))
                     .count();
    }
}
// END