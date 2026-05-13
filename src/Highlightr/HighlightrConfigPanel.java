import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.liteloader.modconfig.Exposable;
import java.util.ArrayList;
import java.util.List;

public class HighlightrConfigPanel
implements ConfigPanel {
    private ave mc;
    private avw textField;
    private avw radiusField;
    private List<avs> buttons = new ArrayList<avs>();
    private avs toggleButton;

    public String getPanelTitle() {
        return "Highlightr Settings";
    }

    public int getContentHeight() {
        return 150;
    }

    public void onPanelShown(ConfigPanelHost configPanelHost) {
        this.mc = ave.A();
        this.buttons.clear();
        this.toggleButton = new avs(0, 10, 10, 100, 20, this.getToggleText());
        this.buttons.add(this.toggleButton);
        this.textField = new avw(1, this.mc.k, 10, 50, 200, 20);
        this.textField.a(LiteModHighlightr.instance.targetBlockId);
        this.textField.f(256);
        this.radiusField = new avw(2, this.mc.k, 10, 90, 50, 20);
        this.radiusField.a(String.valueOf(LiteModHighlightr.instance.scanRadius));
        this.buttons.add(new avs(3, 10, 120, 100, 20, "Save & Close"));
    }

    private String getToggleText() {
        return "Enabled: " + (LiteModHighlightr.instance.enabled ? "ON" : "OFF");
    }

    public void onPanelResize(ConfigPanelHost configPanelHost) {
        this.onPanelShown(configPanelHost);
    }

    public void drawPanel(ConfigPanelHost configPanelHost, int n, int n2, float f) {
        this.mc.k.a("Block ID (e.g. minecraft:diamond_ore or 56):", 10, 35, 0xFFFFFF);
        this.textField.g();
        this.mc.k.a("Scan Radius:", 10, 75, 0xFFFFFF);
        this.radiusField.g();
        for (avs avs2 : this.buttons) {
            avs2.a(this.mc, n, n2);
        }
    }

    public void mousePressed(ConfigPanelHost configPanelHost, int n, int n2, int n3) {
        this.textField.a(n, n2, n3);
        this.radiusField.a(n, n2, n3);
        for (avs avs2 : this.buttons) {
            if (!avs2.c(this.mc, n, n2)) continue;
            if (avs2.k == 0) {
                LiteModHighlightr.instance.enabled = !LiteModHighlightr.instance.enabled;
                avs2.j = this.getToggleText();
            } else if (avs2.k == 3) {
                this.saveSettings();
                configPanelHost.close();
            }
            avs2.a(this.mc.W());
        }
    }

    private void saveSettings() {
        LiteModHighlightr.instance.targetBlockId = this.textField.b();
        try {
            LiteModHighlightr.instance.scanRadius = Integer.parseInt(this.radiusField.b());
            if (LiteModHighlightr.instance.scanRadius > 128) {
                LiteModHighlightr.instance.scanRadius = 128;
            }
            if (LiteModHighlightr.instance.scanRadius < 1) {
                LiteModHighlightr.instance.scanRadius = 1;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        LiteLoader.getInstance().writeConfig((Exposable)LiteModHighlightr.instance);
    }

    public void mouseReleased(ConfigPanelHost configPanelHost, int n, int n2, int n3) {
    }

    public void mouseMoved(ConfigPanelHost configPanelHost, int n, int n2) {
    }

    public void keyPressed(ConfigPanelHost configPanelHost, char c, int n) {
        if (this.textField.a(c, n)) {
            return;
        }
        if (this.radiusField.a(c, n)) {
            return;
        }
        if (n == 1 || n == 28) {
            this.saveSettings();
            configPanelHost.close();
        }
    }

    public void onTick(ConfigPanelHost configPanelHost) {
    }

    public void onPanelHidden() {
        this.saveSettings();
    }
}

