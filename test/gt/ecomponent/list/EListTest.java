package gt.ecomponent.list;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gt.component.ComponentCreator;
import gt.ecomponent.EComponentSettings;
import gt.ecomponent.location.EFixedLocation;
import gt.gameentity.TestGameImage;
import gt.gameentity.TestGameImageDrawer;
import gt.gameentity.TestGraphics;
import gt.gameentity.TestGraphicsObject;
import gt.gameentity.TestGraphicsRectangle;
import gt.gameentity.TestGraphicsString;
import gt.util.EMath;

public class EListTest {
    private static final Color BORDER_COLOR = EComponentSettings.LIST_BORDER_COLOR_DEFAULT.getValue();
    private static final Color BACKGROUND_COLOR = EComponentSettings.LIST_VIEWPORT_BACKGROUND_COLOR_DEFAULT.getValue();
    private static final Color TEXT_COLOR = EComponentSettings.LIST_VIEWPORT_TEXT_COLOR_DEFAULT.getValue();
    private static final Color SELECTED_COLOR = EComponentSettings.LIST_VIEWPORT_SELECTED_COLOR_DEFAULT.getValue();

    private static void checkGraphics(TestGameImage image, TestGraphicsObject... graphicsObjects) {
        List<TestGraphicsObject> gos = image.getGraphicsObjects();
        List<TestGraphicsObject> missing = new ArrayList<>();
        for (TestGraphicsObject graphicsObject : graphicsObjects) {
            if (!gos.contains(graphicsObject)) {
                missing.add(graphicsObject);
            }
        }
        assertTrue(missing.isEmpty(), "Drawn: " + gos.toString() + "\nMissing: " + missing.toString());
    }

    @Test
    public void testDrawList() {
        EFixedLocation cl = new EFixedLocation(0, 0, 99, 99);
        TestGameImageDrawer imageDrawer = new TestGameImageDrawer();
        EList list = new EList(cl, imageDrawer, new String[] { "1", "2", "3" }, 1, i -> {
        });
        TestGameImage testGameImage = imageDrawer.newGameImage();
        list.drawOn(testGameImage.getGraphics());
        int listItemX = EMath.round(1 + EListViewport.ITEM_PADDING);
        int listItemY = EMath.round(1 + (EListViewport.ITEM_HEIGHT + TestGraphics.CHAR_HEIGHT) / 2.0);
        checkGraphics(testGameImage,
                new TestGraphicsRectangle(BORDER_COLOR, 0, 0, 100, 100, false),
                new TestGraphicsRectangle(BACKGROUND_COLOR, 1, 1, 98, 98, true),
                new TestGraphicsString(TEXT_COLOR, ComponentCreator.DEFAULT_FONT_SMALL, listItemX, listItemY, "1"),
                new TestGraphicsString(TEXT_COLOR, ComponentCreator.DEFAULT_FONT_SMALL, listItemX, listItemY + EListViewport.ITEM_HEIGHT, "2"),
                new TestGraphicsString(TEXT_COLOR, ComponentCreator.DEFAULT_FONT_SMALL, listItemX, listItemY + 2 * EListViewport.ITEM_HEIGHT, "3"),
                new TestGraphicsRectangle(SELECTED_COLOR, 1, 21, 98, 20, false));
    }
}
