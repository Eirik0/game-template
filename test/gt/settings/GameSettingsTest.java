package gt.settings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class GameSettingsTest {
    @Test
    public void testParseColor() {
        assertEquals(new Color(1, 2, 3), GameSettings.parseSetting("color(1,2,3)").getValue());
    }
}
