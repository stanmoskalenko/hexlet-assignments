package exercise;

import java.util.List;
import java.util.Map;

// BEGIN
public final class PairedTag extends Tag {

    private final String body;
    private final List<Tag> childSimpleTags;

    public PairedTag(String name,
                     Map<String, String> attribute,
                     String body,
                     List<Tag> childSimpleTags
    ) {
        super(name, attribute);
        this.body = body;
        this.childSimpleTags = childSimpleTags;
    }

    @Override
    public String toString() {
        var childTags = new StringBuilder();
        var result = new StringBuilder();

        for (var tag : childSimpleTags) {
            childTags.append(tag.toString());
        }

        return result.append("<")
                .append(getName())
                .append(getAttribute())
                .append(">")
                .append(childTags)
                .append(body)
                .append("</")
                .append(getName())
                .append(">")
                .toString();
    }
}
// END
