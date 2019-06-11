package gt.settings;

public interface GameSetting<T> {
    T getValue();

    String toFileString();
}
