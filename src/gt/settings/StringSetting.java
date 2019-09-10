package gt.settings;

public class StringSetting implements GameSetting<String> {
    private final String string;

    public StringSetting(String string) {
        this.string = string;
    }

    @Override
    public String getValue() {
        return string;
    }

    @Override
    public String toFileString() {
        return "\"" + string + "\"";
    }
}
