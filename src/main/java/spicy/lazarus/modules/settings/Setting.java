package spicy.lazarus.modules.settings;

import java.util.ArrayList;

public abstract class Setting {
    public String name;
    public ArrayList<BooleanSetting> dependencies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public abstract String getStringValue();

    public abstract void setStringValue(String value) throws Exception;

    public abstract String getStringDefaultValue();

    public final void depend(BooleanSetting s) {
        dependencies.add(s);
    }

    public final boolean depends(BooleanSetting s) {
        return dependencies.contains(s);
    }

    public final ArrayList<BooleanSetting> getDependencies() {
        return dependencies;
    }

    public final boolean dependenciesSatisfied() {
        int i = 0;
        for (BooleanSetting s : dependencies)
            if (s.getValue()) i++;
        return i == dependencies.size();
    }
}
