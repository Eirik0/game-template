package gt.ecomponent;

import java.util.ArrayList;
import java.util.List;

import gt.component.MouseTracker;

public class EComponentPanelBuilder {
    private final MouseTracker mouseTracker;
    private final List<List<EComponent>> componentLists = new ArrayList<>();

    public EComponentPanelBuilder(MouseTracker mouseTracker) {
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
