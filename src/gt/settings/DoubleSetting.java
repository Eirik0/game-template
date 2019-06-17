package gt.settings;

public class DoubleSetting implements GameSetting<Double> {
    private final Double value;

    public DoubleSetting(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toFileString() {
        return Double.toString(value.doubleValue());
    }
}
