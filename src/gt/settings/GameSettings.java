package gt.settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gt.io.FileUtilities;
import gt.util.EMath;

public class GameSettings {
    private static final String SETTINGS_FILE_NAME = "settings.txt";

    private static final Map<String, GameSetting<?>> settingsMap = new HashMap<>();

    public static void loadSettings(String projectName) {
        String settingsFilePath = FileUtilities.getProjectDirectory(projectName) + SETTINGS_FILE_NAME;
        FileUtilities.initFromFile(settingsFilePath,
                lines -> {
                    for (String line : lines) {
                        try {
                            String[] settingStr = line.split("=");
                            GameSetting<?> setting = parseSetting(settingStr[1]);
                            if (setting != null) {
                                settingsMap.put(settingStr[0], setting);
                            }
                        } catch (Exception e) {
                        }
                    }
                }, () -> {
                });
    }

    public static GameSetting<?> parseSetting(String s) {
        if (s.startsWith("color(") && s.endsWith(")")) {
            String[] colors = s.substring(6, s.length() - 1).split(",");
            return new ColorSetting(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
        }
        try {
            return new DoubleSetting(Double.valueOf(Double.parseDouble(s)));
        } catch (Exception e) {
        }
        return null;
    }

    public static void saveSettings(String projectName) {
        String settingsFilePath = FileUtilities.getProjectDirectory(projectName) + SETTINGS_FILE_NAME;
        File settingsFile = new File(settingsFilePath);
        settingsFile.getParentFile().mkdirs();
        List<Entry<String, GameSetting<?>>> entryList = new ArrayList<>(settingsMap.entrySet());
        Collections.sort(entryList, (s1, s2) -> s1.getKey().compareTo(s2.getKey()));
        try {
            FileUtilities.collectionToFile(settingsFile, entryList,
                    entry -> entry.getKey() + "=" + entry.getValue().toFileString() + System.lineSeparator());
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String settingName, GameSetting<T> defaultValue) {
        GameSetting<?> value = settingsMap.get(settingName);
        if (value == null) {
            settingsMap.put(settingName, defaultValue);
            return defaultValue.getValue();
        }
        try {
            return (T) value.getValue();
        } catch (Exception e) {
            settingsMap.put(settingName, defaultValue);
            return defaultValue.getValue();
        }
    }

    public static double getDouble(String settingName, GameSetting<Double> defaultValue) {
        return getValue(settingName, defaultValue).doubleValue();
    }

    public static int getInt(String settingName, GameSetting<Double> defaultValue) {
        return EMath.round(getValue(settingName, defaultValue).doubleValue());
    }
}
