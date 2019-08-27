package gt.ecomponent;

import java.util.ArrayList;
import java.util.List;

import gt.component.IMouseTracker;

public class EComponentPanelBuilder {
    private final IMouseTracker mouseTracker;
    private final List<List<EComponent>> componentLists = new ArrayList<>();

    public EComponentPanelBuilder(IMouseTracker mouseTracker) {
        this.mouseTracker = mouseTracker;
    }

    public EComponentPanelBuilder add(int layer, EComponent component) {
        while (componentLists.size() <= layer) {
            componentLists.add(new ArrayList<>());
        }
        componentLists.get(layer).add(component);
        return this;
    }

    public EComponentPanel build() {
        EComponent[][] components = new EComponent[componentLists.size()][];
        for (int i = 0; i < componentLists.size(); ++i) {
            components[i] = componentLists.get(i).toArray(new EComponent[0]);
        }
        return new EComponentPanel(mouseTracker, components);
    }
}
