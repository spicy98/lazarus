package spicy.lazarus.module.setting.settings;

import spicy.lazarus.module.setting.Setting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModeSetting extends Setting {
    public String value, defaultValue;
    public String[] values;

    public ModeSetting(String name, String value, String... values) {
        this.value = defaultValue = value;
        this.name = name;
        this.values = values;
    }

    @Override
    public String getStringValue() {
        return value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void setStringValue(String value) {
        setValue(value);
    }

    public void setValue(String value) { this.value = value; }

    public String getStringDefaultValue() {
        return defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String[] getValues() {
        return values;
    }

    public boolean validValue(String value) {
        return Arrays.stream(getValues()).map(String::toLowerCase).toList().contains(value.toLowerCase());
    }
}