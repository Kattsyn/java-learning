package kattsyn.task1;

public class NegativeTextAnalyzer extends KeywordAnalyzer implements TextAnalyzer {

    private final String[] keyWords = new String[]{":(", ":|", "=("};

    public NegativeTextAnalyzer(){}

    @Override
    protected String[] getKeywords() {
        return this.keyWords;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }

    @Override
    public Label processText(String text) {
        return super.processText(text);
    }
}
