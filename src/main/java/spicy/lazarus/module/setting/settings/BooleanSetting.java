package spicy.lazarus.module.setting.settings;

import spicy.lazarus.module.setting.Setting;

public class BooleanSetting extends Setting {
    public boolean value, defaultValue;

    public BooleanSetting(String name, boolean value) {
        this.value = defaultValue = value;
        this.name = name;
    }

    @Override
    public String getStringValue() {
        return "" + value;
    }

    @Override
    public void setStringValue(String value) {
        setValue(Boolean.parseBoolean(value));
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) { this.value = value; }

    @Override
    public String getStringDefaultValue() {
        return "" + defaultValue;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
