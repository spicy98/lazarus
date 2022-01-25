package spicy.lazarus.module.setting.settings;

import spicy.lazarus.module.setting.Setting;

public class IntSetting extends Setting {
    public int min, max, value, defaultValue;

    public IntSetting(String name, int value, int min, int max) {
        this.value = defaultValue = value;
        this.max = max;
        this.min = min;
        this.name = name;
    }

    @Override
    public String getStringValue() { return "" + value; }

    public int getValue() {
        return value;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void setStringValue(String value) {
        setValue(Integer.parseInt(value));
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String getStringDefaultValue() {
        return "" + defaultValue;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}