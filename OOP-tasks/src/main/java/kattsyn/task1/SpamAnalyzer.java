package kattsyn.task1;

public class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer {

    private String[] keywords;

    public SpamAnalyzer(String[] keyWords) {
        this.keywords = keyWords;
    }

    @Override
    protected String[] getKeywords() {
        return this.keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }

    @Override
    public Label processText(String text) {
        return super.processText(text);
    }
}
