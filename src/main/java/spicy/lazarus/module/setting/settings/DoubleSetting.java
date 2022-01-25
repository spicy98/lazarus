package spicy.lazarus.module.setting.settings;

import spicy.lazarus.module.setting.Setting;

public class DoubleSetting extends Setting {
    public double min, max, value, defaultValue;

    public DoubleSetting(String name, double value, double min, double max) {
        this.value = defaultValue = value;
        this.max = max;
        this.min = min;
        this.name = name;
    }

    @Override
    public String getStringValue() {
        return "" + Math.round(value * 100.0) / 100.0;
    }

    public double getValue() {
        return value;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    @Override
    public void setStringValue(String value) {
        setValue(Double.parseDouble(value));
    }

    public void setValue(double value) { this.value = value; }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String getStringDefaultValue() {
        return "" + defaultValue;
    }

    public double getDefaultValue() {
        return defaultValue;
    }
}