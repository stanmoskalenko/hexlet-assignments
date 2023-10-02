package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {

    private final String name;
    private final Map<String, String> attribute;

    public Tag(String name, Map<String, String> attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public String stringifyAttributes() {
        var result = new StringBuilder();

        for (var entry : attribute.entrySet()) {
            result.append(" ")
                    .append(entry.getKey())
                    .append("=\"")
                    .append(entry.getValue())
                    .append("\"");
        }
        return result.toString();
    }

    @Override
    public abstract String toString();
}
// END
