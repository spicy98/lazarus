package spicy.lazarus.module.setting.settings;

import spicy.lazarus.module.setting.Setting;

public class StringSetting extends Setting {
    public String value, defaultValue;

    public StringSetting(String name, String value) {
        this.value = defaultValue = value;
        this.name = name;
    }

    @Override
    public String getStringValue() {
        return value;
    }

    @Override
    public void setStringValue(String value) {
        setValue(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStringDefaultValue() {
        return defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}