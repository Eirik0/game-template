package gt.ecomponent;

import java.awt.Color;

import gt.component.ComponentCreator;
import gt.settings.ColorSetting;
import gt.settings.GameSetting;

public interface EComponentColors {
    /* Button */
    String BUTTON_ARROW_COLOR = "ecomponent.button.arrow";
    GameSetting<Color> BUTTON_ARROW_COLOR_DEFAULT = new ColorSetting(ComponentCreator.foregroundColor());
    String BUTTON_BACKGROUND_COLOR = "ecomponent.button.background";
    GameSetting<Color> BUTTON_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String BUTTON_BORDER_COLOR = "ecomponent.button.border";
    GameSetting<Color> BUTTON_BORDER_COLOR_DEFAULT = new ColorSetting(Color.RED);
    String BUTTON_BORDER_HIGHLIGHT_COLOR = "ecomponent.button.border.highlight";
    GameSetting<Color> BUTTON_BORDER_HIGHLIGHT_COLOR_DEFAULT = new ColorSetting(Color.GREEN);
    String BUTTON_PRESSED_COLOR = "ecomponent.button.pressed";
    GameSetting<Color> BUTTON_PRESSED_COLOR_DEFAULT = new ColorSetting(Color.CYAN);

    /* Check Box */
    String CHECK_BOX_BACKGROUND_COLOR = "ecomponent.checkbox.background";
    GameSetting<Color> CHECK_BOX_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String CHECK_BOX_BORDER_COLOR = "ecomponent.checkbox.border";
    GameSetting<Color> CHECK_BOX_BORDER_COLOR_DEFAULT = new ColorSetting(Color.RED);
    String CHECK_BOX_BORDER_HIGHLIGHT_COLOR = "ecomponent.checkbox.border.highlight";
    GameSetting<Color> CHECK_BOX_BORDER_HIGHLIGHT_COLOR_DEFAULT = new ColorSetting(Color.GREEN);
    String CHECK_BOX_PRESSED_COLOR = "ecomponent.checkbox.pressed";
    GameSetting<Color> CHECK_BOX_PRESSED_COLOR_DEFAULT = new ColorSetting(Color.CYAN);
    String CHECK_BOX_SELECTED_COLOR = "ecomponent.checkbox.selected";
    GameSetting<Color> CHECK_BOX_SELECTED_COLOR_DEFAULT = new ColorSetting(ComponentCreator.foregroundColor());

    /* Combo Box */
    String COMBO_BOX_BACKGROUND_COLOR = "ecomponent.combobox.background";
    GameSetting<Color> COMBO_BOX_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String COMBO_BOX_BORDER_COLOR = "ecomponent.combobox.border";
    GameSetting<Color> COMBO_BOX_BORDER_COLOR_DEFAULT = new ColorSetting(Color.RED);
    String COMBO_BOX_BORDER_HIGHLIGHT_COLOR = "ecomponent.combobox.border.highlight";
    GameSetting<Color> COMBO_BOX_BORDER_HIGHLIGHT_COLOR_DEFAULT = new ColorSetting(Color.GREEN);
    String COMBO_BOX_TEXT_COLOR = "ecomponent.combobox.text";
    GameSetting<Color> COMBO_BOX_TEXT_COLOR_DEFAULT = new ColorSetting(ComponentCreator.foregroundColor());

    /* List */
    String LIST_BORDER_COLOR = "ecomponent.list.border";
    GameSetting<Color> LIST_BORDER_COLOR_DEFAULT = new ColorSetting(Color.RED);
    String LIST_BORDER_HIGHLIGHT_COLOR = "ecomponent.list.border.highlight";
    GameSetting<Color> LIST_BORDER_HIGHLIGHT_COLOR_DEFAULT = new ColorSetting(Color.GREEN);

    /* List Viewport */
    String LIST_VIEWPORT_BACKGROUND_COLOR = "ecomponent.listviewport.background";
    GameSetting<Color> LIST_VIEWPORT_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String LIST_VIEWPORT_PRESSED_COLOR = "ecomponent.listviewport.pressed";
    GameSetting<Color> LIST_VIEWPORT_PRESSED_COLOR_DEFAULT = new ColorSetting(Color.GREEN);
    String LIST_VIEWPORT_SELECTED_COLOR = "ecomponent.listviewport.selected";
    GameSetting<Color> LIST_VIEWPORT_SELECTED_COLOR_DEFAULT = new ColorSetting(Color.CYAN);
    String LIST_VIEWPORT_TEXT_COLOR = "ecomponent.listviewport.text";
    GameSetting<Color> LIST_VIEWPORT_TEXT_COLOR_DEFAULT = new ColorSetting(ComponentCreator.foregroundColor());

    /* Scroll Bar */
    String SCROLL_BAR_BACKGROUND_COLOR = "ecomponent.scrollbar.background";
    GameSetting<Color> SCROLL_BAR_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String SCROLL_BAR_BORDER_COLOR = "ecomponent.scrollbar.border";
    GameSetting<Color> SCROLL_BAR_BORDER_COLOR_DEFAULT = new ColorSetting(Color.CYAN);
    String SCROLL_BAR_THUMB_COLOR = "ecomponent.scrollbar.thumb";
    GameSetting<Color> SCROLL_BAR_THUMB_COLOR_DEFAULT = new ColorSetting(Color.RED);
    String SCROLL_BAR_THUMB_HIGHLIGHT_COLOR = "ecomponent.scrollbar.thumb.highlight";
    GameSetting<Color> SCROLL_BAR_THUMB_HIGHLIGHT_COLOR_DEFAULT = new ColorSetting(Color.GREEN);

    /* Scroll Pane */
    String SCROLL_PANE_BACKGROUND_COLOR = "ecomponent.scrollpane.background";
    GameSetting<Color> SCROLL_PANE_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());

    /* Slider */
    String SLIDER_BACKGROUND_COLOR = "ecomponent.slider.background";
    GameSetting<Color> SLIDER_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String SLIDER_BAR_COLOR = "ecomponent.slider.bar";
    GameSetting<Color> SLIDER_BAR_COLOR_DEFAULT = new ColorSetting(Color.CYAN);
    String SLIDER_KNOB_COLOR = "ecomponent.slider.knob";
    GameSetting<Color> SLIDER_KNOB_COLOR_DEFAULT = new ColorSetting(Color.RED);
    String SLIDER_KNOB_HIGHLIGHT_COLOR = "ecomponent.slider.knob.highlight";
    GameSetting<Color> SLIDER_KNOB_HIGHLIGHT_COLOR_DEFAULT = new ColorSetting(Color.GREEN);
    String SLIDER_TICK_COLOR = "ecomponent.slider.tick"; // XXX Rename
    GameSetting<Color> SLIDER_TICK_COLOR_DEFAULT = new ColorSetting(ComponentCreator.foregroundColor());

    /* Text Label */
    String TEXT_LABEL_BACKGROUND_COLOR = "ecomponent.textlabel.background";
    GameSetting<Color> TEXT_LABEL_BACKGROUND_COLOR_DEFAULT = new ColorSetting(ComponentCreator.backgroundColor());
    String TEXT_LABEL_TEXT_COLOR = "ecomponent.textlabel.text";
    GameSetting<Color> TEXT_LABEL_TEXT_COLOR_DEFAULT = new ColorSetting(ComponentCreator.foregroundColor());
}
