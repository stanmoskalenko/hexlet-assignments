package exercise;

import java.util.Map;

// BEGIN
public final class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attribute) {
        super(name, attribute);
    }

    @Override
    public String toString() {
        return "<" + getName() + getAttribute() + ">";
    }
}
// END
