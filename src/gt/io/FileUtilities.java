package gt.io;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import gt.component.ComponentCreator;

public class FileUtilities {
    public static String getProjectDirectory(String projectName) {
        return System.getProperty("user.home") + File.separator + "." + projectName + File.separator;
    }

    public static List<String> fileToList(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static <T> void collectionToFile(File file, Collection<T> ts, Function<T, String> toString) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (T t : ts) {
                writer.write(toString.apply(t));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static <T> T loadFromFile(String filePath, Function<List<String>, T> onSuccess, Supplier<T> onFailure) {
        File file = new File(filePath);
        if (file.exists()) {
            return onSuccess.apply(FileUtilities.fileToList(file));
        }
        return onFailure.get();
    }

    public static void initFromFile(String filePath, Consumer<List<String>> onSuccess, Runnable onFailure) {
        File file = new File(filePath);
        if (file.exists()) {
            onSuccess.accept(FileUtilities.fileToList(file));
            return;
        }
        onFailure.run();
    }

    public static Font loadFont(String fontResource, int fontType) {
        try (InputStream fontStream = FileUtilities.class.getResourceAsStream(fontResource)) {
            return Font.createFont(fontType, fontStream);
        } catch (FontFormatException | IOException e) {
            return ComponentCreator.DEFAULT_FONT_SMALL;
        }
    }
}
