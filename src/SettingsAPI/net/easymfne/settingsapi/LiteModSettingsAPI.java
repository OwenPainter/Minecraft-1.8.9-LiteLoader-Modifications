package net.easymfne.settingsapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import java.io.File;

@ExposableOptions(strategy=ConfigStrategy.Unversioned, filename="settingsapi.config.json")
public class LiteModSettingsAPI
implements LiteMod,
Configurable {
    public static LiteModSettingsAPI instance;
    @Expose
    @SerializedName(value="combat_reach")
    public double combatReach = 3.0;
    @Expose
    @SerializedName(value="enabled")
    public boolean enabled = true;

    public LiteModSettingsAPI() {
        instance = this;
    }

    public static double getCombatReach() {
        if (instance != null && LiteModSettingsAPI.instance.enabled) {
            return Math.min(LiteModSettingsAPI.instance.combatReach, 3.4);
        }
        return 3.0;
    }

    public static float getReach(Object object) {
        return (float)LiteModSettingsAPI.getCombatReach();
    }

    public Class<? extends ConfigPanel> getConfigPanelClass() {
        try {
            return Class.forName("SettingsAPIConfigPanel");
        }
        catch (Exception exception) {
            return null;
        }
    }

    public String getName() {
        return "SettingsAPI";
    }

    public String getVersion() {
        return "1.0.0";
    }

    public void init(File file) {
    }

    public void upgradeSettings(String string, File file, File file2) {
    }
}

