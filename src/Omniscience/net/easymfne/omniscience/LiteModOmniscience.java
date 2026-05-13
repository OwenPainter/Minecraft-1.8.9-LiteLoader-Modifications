package net.easymfne.omniscience;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.Permissible;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;
import com.mumfrey.liteloader.permissions.PermissionsManager;
import com.mumfrey.liteloader.permissions.PermissionsManagerClient;
import com.mumfrey.liteloader.transformers.event.EventInfo;
import com.mumfrey.liteloader.transformers.event.ReturnEventInfo;
import com.mumfrey.liteloader.util.log.LiteLoaderLogger;
import java.io.File;
import net.easymfne.omniscience.OmniscienceConfigPanel;
import net.easymfne.omniscience.OmnisciencePermissions;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@ExposableOptions(strategy=ConfigStrategy.Unversioned, filename="omniscience.config.json")
public class LiteModOmniscience
implements LiteMod,
Configurable,
Permissible {
    public static LiteModOmniscience instance;
    public static final String MOD_NAME = "Omniscience";
    public static final String MOD_VERSION = "3.3.0";
    public static final float MOD_VERSION_NUMBER = 3.3f;
    @Expose
    @SerializedName(value="spy_entities")
    protected boolean spyEntities = false;
    @Expose
    @SerializedName(value="spy_players")
    protected boolean spyPlayers = true;
    public static boolean isChams;

    public static void adjustIsInvisible(ReturnEventInfo returnEventInfo) {
        if (isChams) {
            returnEventInfo.setReturnValue((Object)Boolean.FALSE);
        }
    }

    public static void adjustEntityVisibility(ReturnEventInfo returnEventInfo, wn wn2) {
        if (isChams) {
            returnEventInfo.setReturnValue((Object)Boolean.FALSE);
        }
    }

    public static void adjustPlayerVisibility(ReturnEventInfo returnEventInfo, wn wn2) {
        if (isChams) {
            returnEventInfo.setReturnValue((Object)Boolean.FALSE);
        }
    }

    public static void preDoRender(EventInfo eventInfo, pr pr2, double d, double d2, double d3, float f, float f2) {
        boolean bl = LiteModOmniscience.isPlayerClass(pr2);
        boolean bl2 = Keyboard.isKeyDown((int)48);
        boolean bl3 = false;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        if (bl2 && bl) {
            bl3 = true;
            f3 = 0.4f;
            f4 = 0.6f;
            f5 = 1.0f;
        } else if (LiteModOmniscience.checkInvisible(pr2)) {
            if (bl && LiteModOmniscience.instance.spyPlayers && OmnisciencePermissions.canSpyPlayer()) {
                bl3 = true;
                f3 = 0.0f;
                f4 = 1.0f;
                f5 = 0.0f;
            } else if (!bl && LiteModOmniscience.instance.spyEntities && OmnisciencePermissions.canSpyEntity()) {
                bl3 = true;
                f3 = 0.0f;
                f4 = 1.0f;
                f5 = 0.0f;
            }
        }
        if (bl3) {
            isChams = true;
            GL11.glPushAttrib((int)1048575);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)1.0f);
        }
    }

    public static void postDoRender(EventInfo eventInfo, pr pr2, double d, double d2, double d3, float f, float f2) {
        if (isChams) {
            GL11.glPopAttrib();
            isChams = false;
        }
    }

    private static boolean isPlayerClass(Object object) {
        try {
            return Class.forName("net.minecraft.entity.player.EntityPlayer").isInstance(object) || Class.forName("wn").isInstance(object);
        }
        catch (Exception exception) {
            return false;
        }
    }

    private static boolean checkInvisible(Object object) {
        String[] stringArray;
        for (String string : stringArray = new String[]{"ax", "isInvisible", "func_82150_aj"}) {
            try {
                return (Boolean)object.getClass().getMethod(string, new Class[0]).invoke(object, new Object[0]);
            }
            catch (Exception exception) {
            }
        }
        return false;
    }

    public LiteModOmniscience() {
        if (instance != null) {
            LiteLoaderLogger.severe((String)"Attempted to instantiate Omniscience twice.", (Object[])new Object[0]);
            throw new RuntimeException("Double instantiation of Omniscience");
        }
        instance = this;
    }

    public Class<? extends ConfigPanel> getConfigPanelClass() {
        return OmniscienceConfigPanel.class;
    }

    public String getName() {
        return MOD_NAME;
    }

    public String getPermissibleModName() {
        return MOD_NAME.toLowerCase();
    }

    public float getPermissibleModVersion() {
        return 3.3f;
    }

    public String getVersion() {
        return MOD_VERSION;
    }

    public void init(File file) {
    }

    public void onPermissionsChanged(PermissionsManager permissionsManager) {
        OmnisciencePermissions.refresh();
    }

    public void onPermissionsCleared(PermissionsManager permissionsManager) {
        OmnisciencePermissions.clear();
    }

    public void registerPermissions(PermissionsManagerClient permissionsManagerClient) {
        OmnisciencePermissions.init(this, permissionsManagerClient);
    }

    public void upgradeSettings(String string, File file, File file2) {
    }

    static {
        isChams = false;
    }
}

