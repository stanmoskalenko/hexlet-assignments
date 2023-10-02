package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
class Validator {
    public static final String ERROR_MSG_NOT_NULL = "can not be null";
    public static final String ERROR_MSG_TOO_SMALL_LENGTH = "length less than ";

    private static Map<String, List<String>> getInvalidField(Field field, Object object) {
        Map<String, List<String>> invalidField = new HashMap<>();
        field.setAccessible(true);
        List<String> errorMsgAcc = new ArrayList<>();
        var fieldName = field.getName();

        if (Objects.nonNull(field.getDeclaredAnnotation(NotNull.class))) {
            try {
                var value = field.get(object);
                if (value == null) {
                    errorMsgAcc.add(ERROR_MSG_NOT_NULL);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        if (Objects.nonNull(field.getDeclaredAnnotation(MinLength.class))) {
            var minLength = field.getDeclaredAnnotation(MinLength.class).minLength();
            try {
                String value = "";
                if (Objects.nonNull(field.get(object))) {
                    value = field.get(object).toString();
                }
                if (value.length() < minLength) {
                    errorMsgAcc.add(ERROR_MSG_TOO_SMALL_LENGTH + minLength);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        if (errorMsgAcc.isEmpty()) {
            return new HashMap<>();
        } else {
            invalidField.put(fieldName, errorMsgAcc);
            return invalidField;
        }
    }

    public static List<String> validate(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        return Arrays.stream(fields)
                .map(field -> {
                            if (field.getDeclaredAnnotation(NotNull.class) != null) {
                                field.setAccessible(true);
                                try {
                                    var value = field.get(object);
                                    if (value == null) {
                                        return field.getName();
                                    }
                                } catch (IllegalArgumentException | IllegalAccessException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            return null;
                        }
                )
                .filter(Objects::nonNull)
                .toList();
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Map<String, List<String>> invalidFields = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (var field : fields) {
            invalidFields.putAll(getInvalidField(field, object));
        }

        return invalidFields;
    }
}
// END
