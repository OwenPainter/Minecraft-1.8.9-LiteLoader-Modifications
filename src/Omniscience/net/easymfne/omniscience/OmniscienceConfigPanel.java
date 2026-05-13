package net.easymfne.omniscience;

import com.mumfrey.liteloader.client.gui.GuiCheckbox;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.liteloader.modconfig.Exposable;
import net.easymfne.omniscience.LiteModOmniscience;
import net.easymfne.omniscience.OmnisciencePermissions;

public class OmniscienceConfigPanel
extends avp
implements ConfigPanel {
    private static final int SPACING = 16;
    private static final int CURSOR_OFFSET_X = 6;
    private static final int CURSOR_OFFSET_Y = 10;
    private ave minecraft;
    private GuiCheckbox spyEntitiesBox;
    private GuiCheckbox spyPlayersBox;
    private avs activeButton;

    public void drawPanel(ConfigPanelHost host, int mouseX, int mouseY, float partialTicks) {
        this.spyEntitiesBox.a(this.minecraft, mouseX, mouseY);
        this.spyPlayersBox.a(this.minecraft, mouseX, mouseY);
        if (this.spyEntitiesBox.a() && !this.spyEntitiesBox.l || this.spyPlayersBox.a() && !this.spyPlayersBox.l) {
            this.minecraft.k.a(bnq.a((String)"omniscience.configpanel.nopermission.text", (Object[])new Object[0]), (float)(mouseX + 6), (float)(mouseY + 10), 0xFF0000);
        }
    }

    public int getContentHeight() {
        return 48;
    }

    public String getPanelTitle() {
        return bnq.a((String)"omniscience.configpanel.title", (Object[])new Object[]{"Omniscience"});
    }

    public void keyPressed(ConfigPanelHost host, char keyChar, int keyCode) {
        if (keyCode == 1 || keyCode == 28) {
            host.close();
        }
    }

    public void mouseMoved(ConfigPanelHost host, int mouseX, int mouseY) {
    }

    public void mousePressed(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton) {
        if (this.spyEntitiesBox.c(this.minecraft, mouseX, mouseY)) {
            this.activeButton = this.spyEntitiesBox;
            this.spyEntitiesBox.checked = LiteModOmniscience.instance.spyEntities = !LiteModOmniscience.instance.spyEntities;
            this.spyEntitiesBox.a(this.minecraft.W());
        } else if (this.spyPlayersBox.c(this.minecraft, mouseX, mouseY)) {
            this.activeButton = this.spyPlayersBox;
            this.spyPlayersBox.checked = LiteModOmniscience.instance.spyPlayers = !LiteModOmniscience.instance.spyPlayers;
            this.spyPlayersBox.a(this.minecraft.W());
        }
    }

    public void mouseReleased(ConfigPanelHost host, int mouseX, int mouseY, int mouseButton) {
        if (this.activeButton != null) {
            this.activeButton.a(mouseX, mouseY);
            this.activeButton = null;
        }
    }

    public void onPanelHidden() {
        LiteLoader.getInstance().writeConfig((Exposable)LiteModOmniscience.instance);
    }

    public void onPanelResize(ConfigPanelHost host) {
    }

    public void onPanelShown(ConfigPanelHost host) {
        this.minecraft = ave.A();
        int id = 0;
        int line = 0;
        this.spyEntitiesBox = new GuiCheckbox(id++, 10, 16 * line++, bnq.a((String)"omniscience.configpanel.spyentity.text", (Object[])new Object[0]));
        this.spyEntitiesBox.checked = LiteModOmniscience.instance.spyEntities;
        this.spyPlayersBox = new GuiCheckbox(id++, 10, 16 * line++, bnq.a((String)"omniscience.configpanel.spyplayer.text", (Object[])new Object[0]));
        this.spyPlayersBox.checked = LiteModOmniscience.instance.spyPlayers;
        this.updateForPermissions();
    }

    public void onTick(ConfigPanelHost host) {
    }

    private void updateForPermissions() {
        if (this.spyEntitiesBox != null) {
            this.spyEntitiesBox.l = OmnisciencePermissions.canSpyEntity();
            this.spyEntitiesBox.checked &= this.spyEntitiesBox.l;
        }
        if (this.spyPlayersBox != null) {
            this.spyPlayersBox.l = OmnisciencePermissions.canSpyPlayer();
            this.spyPlayersBox.checked &= this.spyPlayersBox.l;
        }
    }
}

