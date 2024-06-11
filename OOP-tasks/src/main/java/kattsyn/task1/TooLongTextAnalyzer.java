package kattsyn.task1;

public class TooLongTextAnalyzer implements TextAnalyzer {

    private int maxLength;

    public TooLongTextAnalyzer(int maxLength) {
        this.maxLength = maxLength;
    }

    private Label getLabel() {
        return Label.TOO_LONG;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > maxLength) {
            return getLabel();
        }
        return Label.OK;
    }
}
