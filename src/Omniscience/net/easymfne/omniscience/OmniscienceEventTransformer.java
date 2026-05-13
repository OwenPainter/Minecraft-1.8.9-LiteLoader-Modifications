package net.easymfne.omniscience;

import com.mumfrey.liteloader.core.runtime.Obf;
import com.mumfrey.liteloader.transformers.event.Event;
import com.mumfrey.liteloader.transformers.event.EventInjectionTransformer;
import com.mumfrey.liteloader.transformers.event.InjectionPoint;
import com.mumfrey.liteloader.transformers.event.MethodInfo;
import com.mumfrey.liteloader.transformers.event.inject.BeforeReturn;
import com.mumfrey.liteloader.transformers.event.inject.MethodHead;
import net.easymfne.omniscience.OmniscienceObf;

public class OmniscienceEventTransformer
extends EventInjectionTransformer {
    private void addIsInvisibleEvent() {
        this.addEvent(Event.getOrCreate((String)"Omniscience_Entity_isInvisible", (boolean)true), new MethodInfo((Obf)OmniscienceObf.ENTITY, (Obf)OmniscienceObf.IS_INVISIBLE, Boolean.TYPE, new Object[0]), (InjectionPoint)new MethodHead()).addListener(new MethodInfo("net.easymfne.omniscience.LiteModOmniscience", "adjustIsInvisible"));
    }

    private void addEntityVisibilityCheckEvent() {
        this.addEvent(Event.getOrCreate((String)"Omniscience_Entity_isInvisibleToPlayer", (boolean)true), new MethodInfo((Obf)OmniscienceObf.ENTITY, (Obf)OmniscienceObf.IS_INVISIBLE_TO_PLAYER, Boolean.TYPE, new Object[]{OmniscienceObf.ENTITY_PLAYER}), (InjectionPoint)new MethodHead()).addListener(new MethodInfo("net.easymfne.omniscience.LiteModOmniscience", "adjustEntityVisibility"));
    }

    private void addPlayerVisibilityCheckEvent() {
        this.addEvent(Event.getOrCreate((String)"Omniscience_EntityPlayer_isInvisibleToPlayer", (boolean)true), new MethodInfo((Obf)OmniscienceObf.ENTITY_PLAYER, (Obf)OmniscienceObf.IS_INVISIBLE_TO_PLAYER, Boolean.TYPE, new Object[]{OmniscienceObf.ENTITY_PLAYER}), (InjectionPoint)new MethodHead()).addListener(new MethodInfo("net.easymfne.omniscience.LiteModOmniscience", "adjustPlayerVisibility"));
    }

    private void addDoRenderEvent() {
        this.addEvent(Event.getOrCreate((String)"Omniscience_PreDoRender", (boolean)false), new MethodInfo((Obf)OmniscienceObf.RENDERER_LIVING_ENTITY, (Obf)OmniscienceObf.DO_RENDER, Void.TYPE, new Object[]{OmniscienceObf.ENTITY_LIVING_BASE, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE}), (InjectionPoint)new MethodHead()).addListener(new MethodInfo("net.easymfne.omniscience.LiteModOmniscience", "preDoRender"));
        this.addEvent(Event.getOrCreate((String)"Omniscience_PostDoRender", (boolean)false), new MethodInfo((Obf)OmniscienceObf.RENDERER_LIVING_ENTITY, (Obf)OmniscienceObf.DO_RENDER, Void.TYPE, new Object[]{OmniscienceObf.ENTITY_LIVING_BASE, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE}), (InjectionPoint)new BeforeReturn()).addListener(new MethodInfo("net.easymfne.omniscience.LiteModOmniscience", "postDoRender"));
    }

    private void addPostRenderModelEvent() {
        this.addEvent(Event.getOrCreate((String)"Omniscience_PostRenderModel", (boolean)false), new MethodInfo((Obf)OmniscienceObf.RENDERER_LIVING_ENTITY, (Obf)OmniscienceObf.RENDER_MODEL, Void.TYPE, new Object[]{OmniscienceObf.ENTITY_LIVING_BASE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE}), (InjectionPoint)new BeforeReturn()).addListener(new MethodInfo("net.easymfne.omniscience.LiteModOmniscience", "postRenderModel"));
    }

    protected void addEvents() {
        this.addIsInvisibleEvent();
        this.addEntityVisibilityCheckEvent();
        this.addPlayerVisibilityCheckEvent();
        this.addDoRenderEvent();
    }
}

