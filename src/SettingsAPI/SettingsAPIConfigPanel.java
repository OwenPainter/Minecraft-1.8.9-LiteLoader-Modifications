import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.liteloader.modconfig.Exposable;
import java.util.ArrayList;
import java.util.List;
import net.easymfne.settingsapi.LiteModSettingsAPI;

public class SettingsAPIConfigPanel
implements ConfigPanel {
    private ave minecraft;
    private List<avs> buttons = new ArrayList<avs>();
    private avs activeButton = null;

    public String getPanelTitle() {
        return "SettingsAPI Configuration";
    }

    public int getContentHeight() {
        return 120;
    }

    public void onPanelShown(ConfigPanelHost configPanelHost) {
        this.minecraft = ave.A();
        this.buttons.clear();
        int n = 10;
        int n2 = 22;
        this.buttons.add(new avs(0, 10, n, 150, 20, "Vanilla (3.0)"));
        this.buttons.add(new avs(1, 10, n + n2, 150, 20, "Reach: 3.1 (+0.1)"));
        this.buttons.add(new avs(2, 10, n + n2 * 2, 150, 20, "Reach: 3.2 (+0.2)"));
        this.buttons.add(new avs(3, 10, n + n2 * 3, 150, 20, "Reach: 3.3 (+0.3)"));
        this.buttons.add(new avs(4, 10, n + n2 * 4, 150, 20, "Reach: 3.4 (+0.4)"));
    }

    public void onPanelResize(ConfigPanelHost configPanelHost) {
        this.onPanelShown(configPanelHost);
    }

    public void drawPanel(ConfigPanelHost configPanelHost, int n, int n2, float f) {
        for (avs avs2 : this.buttons) {
            avs2.a(this.minecraft, n, n2);
        }
        String string = "Current Value: " + LiteModSettingsAPI.instance.combatReach;
        this.minecraft.k.a(string, 170.0f, 15.0f, 0xFFFFFF);
    }

    public void mousePressed(ConfigPanelHost configPanelHost, int n, int n2, int n3) {
        for (avs avs2 : this.buttons) {
            if (!avs2.c(this.minecraft, n, n2)) continue;
            this.activeButton = avs2;
            if (avs2.k == 0) {
                LiteModSettingsAPI.instance.combatReach = 3.0;
            } else if (avs2.k == 1) {
                LiteModSettingsAPI.instance.combatReach = 3.1;
            } else if (avs2.k == 2) {
                LiteModSettingsAPI.instance.combatReach = 3.2;
            } else if (avs2.k == 3) {
                LiteModSettingsAPI.instance.combatReach = 3.3;
            } else if (avs2.k == 4) {
                LiteModSettingsAPI.instance.combatReach = 3.4;
            }
            LiteLoader.getInstance().writeConfig((Exposable)LiteModSettingsAPI.instance);
            avs2.a(this.minecraft.W());
            break;
        }
    }

    public void mouseReleased(ConfigPanelHost configPanelHost, int n, int n2, int n3) {
        if (this.activeButton != null) {
            this.activeButton.a(n, n2);
            this.activeButton = null;
        }
    }

    public void mouseMoved(ConfigPanelHost configPanelHost, int n, int n2) {
    }

    public void keyPressed(ConfigPanelHost configPanelHost, char c, int n) {
        if (n == 1 || n == 28) {
            configPanelHost.close();
        }
    }

    public void onTick(ConfigPanelHost configPanelHost) {
    }

    public void onPanelHidden() {
        LiteLoader.getInstance().writeConfig((Exposable)LiteModSettingsAPI.instance);
    }
}

