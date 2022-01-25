package spicy.lazarus.module.setting.settings;

import spicy.lazarus.module.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting {

    public int red, green, blue, alpha, defaultRed, defaultGreen, defaultBlue, defaultAlpha;

    public ColorSetting(String name, Color color) {
        this.red = defaultRed = color.getRed();
        this.green = defaultGreen = color.getGreen();
        this.blue = defaultBlue = color.getBlue();
        this.alpha = defaultAlpha = color.getAlpha();
        this.name = name;
    }

    public ColorSetting(String name, int red, int green, int blue) {
        this(name, new Color(red, green, blue));
    }

    public ColorSetting(String name, int red, int green, int blue, int alpha) {
        this(name, new Color(red, green, blue, alpha));
    }

    public String getStringValue(boolean a) {
        if (a) return red + ", " + green + ", " + blue + ", " + alpha;
        else return red + ", " + green + ", " + blue;
    }

    @Override
    public String getStringValue() {
        return getStringValue(true);
    }

    public int[] getValues(boolean a) {
        if (a) return new int[]{red, green, blue, alpha};
        return new int[]{red, green, blue};
    }

    public String[] getStringValues(boolean a) {
        if (a) return new String[]{String.valueOf(red), String.valueOf(green), String.valueOf(blue), String.valueOf(alpha)};
        return new String[]{String.valueOf(red), String.valueOf(green), String.valueOf(blue)};
    }

    @Override
    public void setStringValue(String value) throws Exception {
        String[] strings = value.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].strip();
        }
        if (strings.length == 3) {
            setStringValue(strings[0], strings[1], strings[2]);
        } else if (strings.length == 4) {
            setStringValue(strings[0], strings[1], strings[2], strings[3]);
        } else {
            throw new Exception("String must have 3 or 4 values");
        }
    }

    public String getStringDefaultValue(boolean a) {
        if (a) return red + ", " + green + ", " + blue + ", " + alpha;
        return red + ", " + green + ", " + blue;
    }

    @Override
    public String getStringDefaultValue() {
        return getStringDefaultValue(false);
    }

    public Color getValue() {
        return new Color(red, green, blue, alpha);
    }

    public void setStringValue(String r, String g, String b, String a) {
        setValue(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b), Integer.parseInt(a));
    }

    public void setStringValue(String r, String g, String b) {
        setValue(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b), 255);
    }

    public void setStringValue(int color, String value) {
        setValue(color, Integer.parseInt(value));
    }

    public void setValue(int r, int g, int b, int a) {
        red = r;
        green = g;
        blue = b;
        alpha = a;
    }

    public void setRed(int r) { red = r; }

    public void setGreen(int g) { green = g; }

    public void setBlue(int b) { blue = b; }

    public void setAlpha(int a) { alpha = a; }

    private void setValue(int r, int g, int b) {
        setValue(r, g, b, 255);
    }

    public void setValue(int color, int value) {
        switch (color) {
            case 0 -> this.red = value;
            case 1 -> this.green = value;
            case 2 -> this.blue = value;
            case 3 -> this.alpha = value;
        }
    }
}