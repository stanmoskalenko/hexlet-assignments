package exercise;

// BEGIN

class LabelTag implements TagInterface {
    
    private final String labelText;
    private final String nestedTag;

    public LabelTag(String labelText, TagInterface nestedTag) {
        this.labelText = labelText;
        this.nestedTag = nestedTag.render();
    }

    @Override
    public String render() {
        return "<label>" + labelText + nestedTag + "</label>";
    }
}

// END
