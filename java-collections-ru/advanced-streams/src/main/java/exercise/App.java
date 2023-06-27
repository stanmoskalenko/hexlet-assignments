package exercise;

import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    private static final String ENV_COMMAND = "environment=";
    private static final String ENV_PREFIX = "X_FORWARDED_";

    public static String getForwardedVariables(String config) {
        if (config.isEmpty()) {
            return new String();
        }

        Predicate<String> isEnv = env -> env.startsWith(ENV_COMMAND);
        Predicate<String> isForwarded = env -> env.contains(ENV_PREFIX);
        Function<String, String> normalizeEnvs = env -> env.substring(ENV_COMMAND.length()).replace("\"","");

        return config.lines()
                     .filter(isEnv)
                     .map(normalizeEnvs)
                     .flatMap(env -> Stream.of(env.trim().split(",")))
                     .filter(isForwarded)
                     .map(env -> env.replace(ENV_PREFIX, ""))
                     .peek(env -> System.out.println(env))
                     .collect(Collectors.joining(","));
    }
}
//END
