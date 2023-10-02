package exercise;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


// BEGIN
class Validator {
    public static final String ERROR_MSG_NOT_NULL = "can not be null";
    public static final String ERROR_MSG_TOO_SMALL_LENGTH = "length less than ";

    private static String getFieldValue(Field field, Object object) {
        try {
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Map<String, List<String>> getInvalidField(Field field, Object object) {
        Map<String, List<String>> invalidField = new HashMap<>();
        field.setAccessible(true);
        List<String> errorMsgAcc = new ArrayList<>();
        var fieldName = field.getName();
        String fieldValue = getFieldValue(field, object);

        if (field.isAnnotationPresent(NotNull.class)) {
            if (fieldValue == null) {
                errorMsgAcc.add(ERROR_MSG_NOT_NULL);
            }
        }

        if (field.isAnnotationPresent(MinLength.class)) {
            var minLength = field.getDeclaredAnnotation(MinLength.class).minLength();
            if (fieldValue == null || fieldValue.length() < minLength) {
                errorMsgAcc.add(ERROR_MSG_TOO_SMALL_LENGTH + minLength);
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
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .map(field -> {
                            if (getFieldValue(field, object) == null) {
                                return field.getName();
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
