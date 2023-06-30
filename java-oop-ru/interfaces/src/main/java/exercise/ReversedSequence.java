package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String str;

    public ReversedSequence(String str) {
        this.str = str;
    }

    public char[] getReversedSeq() {
        var reversedStr = new StringBuilder(this.str).reverse().toString();
        char[] reversedSeq = reversedStr.toCharArray();
        return reversedSeq;
    }

    public String getReversedStr() {
        return new StringBuilder(str).reverse().toString();
    }

    @Override
    public char charAt(int idx) {
        return getReversedSeq()[idx];
    }

    @Override
    public int length() {
        return getReversedSeq().length;
    }

    @Override
    public String subSequence(int start, int end) {
        return getReversedStr().substring(start, end);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var elem : getReversedSeq()) {
            sb.append(elem);
        }
        return sb.toString();
    }
}
// END
