package net.easymfne.omniscience;

import com.mumfrey.liteloader.Permissible;
import com.mumfrey.liteloader.permissions.PermissionsManagerClient;
import net.easymfne.omniscience.LiteModOmniscience;

public class OmnisciencePermissions {
    public static final String STAR = "*";
    public static final String ENTITY_STAR = "entity.*";
    public static final String ENTITY_TRANSLUCENT = "entity.translucent";
    public static final String PLAYER_STAR = "player.*";
    public static final String PLAYER_TRANSLUCENT = "player.translucent";
    public static final String RENDER_HIGHLIGHT = "render.highlight";
    private static PermissionsManagerClient permissionsManager;
    private static Permissible mod;
    private static boolean initDone;
    private static boolean canRenderHighlight;
    private static boolean canSpyEntity;
    private static boolean canSpyPlayer;

    public static boolean canRenderHighlight() {
        return canRenderHighlight;
    }

    public static boolean canSpyEntity() {
        return canSpyEntity;
    }

    public static boolean canSpyPlayer() {
        return canSpyPlayer;
    }

    public static void clear() {
        canRenderHighlight = true;
        canSpyEntity = true;
        canSpyPlayer = true;
    }

    private static boolean hasPermission(String permission) {
        permissionsManager.tamperCheck();
        return permissionsManager.getModPermission(mod, permission, false);
    }

    public static void init(LiteModOmniscience mod, PermissionsManagerClient permissionsManager) {
        if (!initDone || OmnisciencePermissions.permissionsManager != permissionsManager) {
            initDone = true;
            OmnisciencePermissions.mod = mod;
            OmnisciencePermissions.permissionsManager = permissionsManager;
            OmnisciencePermissions.initPermissions();
        }
    }

    private static void initPermissions() {
        OmnisciencePermissions.registerPermission(STAR);
        OmnisciencePermissions.registerPermission(ENTITY_STAR);
        OmnisciencePermissions.registerPermission(ENTITY_TRANSLUCENT);
        OmnisciencePermissions.registerPermission(PLAYER_STAR);
        OmnisciencePermissions.registerPermission(PLAYER_TRANSLUCENT);
        OmnisciencePermissions.registerPermission(RENDER_HIGHLIGHT);
        OmnisciencePermissions.queryPermissions();
    }

    public static void queryPermissions() {
        try {
            permissionsManager.tamperCheck();
            permissionsManager.sendPermissionQuery(mod);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public static void refresh() {
        canRenderHighlight = OmnisciencePermissions.hasPermission(STAR) || OmnisciencePermissions.hasPermission(RENDER_HIGHLIGHT);
        canSpyEntity = OmnisciencePermissions.hasPermission(STAR) || OmnisciencePermissions.hasPermission(ENTITY_STAR) || OmnisciencePermissions.hasPermission(ENTITY_TRANSLUCENT);
        canSpyPlayer = OmnisciencePermissions.hasPermission(STAR) || OmnisciencePermissions.hasPermission(PLAYER_STAR) || OmnisciencePermissions.hasPermission(PLAYER_TRANSLUCENT);
    }

    private static void registerPermission(String permission) {
        permissionsManager.tamperCheck();
        permissionsManager.registerModPermission(mod, permission);
    }

    static {
        canRenderHighlight = true;
        canSpyEntity = true;
        canSpyPlayer = true;
    }
}

