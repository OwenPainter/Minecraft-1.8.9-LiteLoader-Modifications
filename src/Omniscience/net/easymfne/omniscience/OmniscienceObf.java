package net.easymfne.omniscience;

import com.mumfrey.liteloader.core.runtime.Obf;

public class OmniscienceObf
extends Obf {
    public static OmniscienceObf ENTITY = new OmniscienceObf("net.minecraft.entity.Entity", "pk");
    public static OmniscienceObf ENTITY_PLAYER = new OmniscienceObf("net.minecraft.entity.player.EntityPlayer", "wn");
    public static OmniscienceObf IS_INVISIBLE_TO_PLAYER = new OmniscienceObf("func_98034_c", "f", "isInvisibleToPlayer");
    public static OmniscienceObf IS_INVISIBLE = new OmniscienceObf("func_82150_aj", "ax", "isInvisible");
    public static OmniscienceObf ENTITY_LIVING_BASE = new OmniscienceObf("net.minecraft.entity.EntityLivingBase", "pr");
    public static OmniscienceObf RENDERER_LIVING_ENTITY = new OmniscienceObf("net.minecraft.client.renderer.entity.RendererLivingEntity", "bjl");
    public static OmniscienceObf RENDER_MODEL = new OmniscienceObf("func_177093_a", "a", "renderModel");
    public static OmniscienceObf DO_RENDER = new OmniscienceObf("func_76986_a", "a", "doRender");
    public static OmniscienceObf MODEL_BASE = new OmniscienceObf("net.minecraft.client.model.ModelBase", "bbo");
    public static OmniscienceObf MODEL_RENDER = new OmniscienceObf("func_78088_a", "a", "render");

    protected OmniscienceObf(String string, String string2) {
        super(string, string2);
    }

    protected OmniscienceObf(String string, String string2, String string3) {
        super(string, string2, string3);
    }
}

