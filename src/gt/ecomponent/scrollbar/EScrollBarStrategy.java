package gt.ecomponent.scrollbar;

import gt.ecomponent.button.EButton;
import gt.ecomponent.list.EComponentLocation;

public interface EScrollBarStrategy {
    EComponentLocation getComponentLocation();

    EButton getButton1();

    EButton getButton2();

    EComponentLocation getTrackLocation();

    EComponentLocation getThumbLocation();

    void setOtherBarVisible(boolean visible);

    void checkMousePressed();

    void setMouseOverImpl(double screenX, double screenY);

    void setMouseScrolledImpl(double wheelDelta);
}
