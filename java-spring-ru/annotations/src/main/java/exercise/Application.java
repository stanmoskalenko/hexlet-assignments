package exercise;

import exercise.annotation.Inspect;
import exercise.model.Address;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        var methodInfo = Arrays.stream(Address.class.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Inspect.class))
                .peek(method -> {
                    try {
                        method.invoke(address);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(method -> String.format(
                        "Method %s returns a value of type %s"
                        , method.getName()
                        , method.getReturnType().getSimpleName()))
                .toList();

        System.out.println(methodInfo);
    }
    // END
}
