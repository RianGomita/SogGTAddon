package net.sog.core.client.renderer.machine.multiblock;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;

import net.sog.core.client.renderer.machine.ArtificialStarRender;
import net.sog.core.client.renderer.machine.EyeOfHarmonyRender;
import net.sog.core.client.renderer.machine.PlasmaArcFurnaceRender;

public class SoGDynamicRenderHelpers {

    public static DynamicRender<?, ?> getEyeOfHarmonyRender() {
        return EyeOfHarmonyRender.INSTANCE;
    }

    public static DynamicRender<?, ?> getArtificialStarRender() {
        return ArtificialStarRender.INSTANCE;
    }

    public static DynamicRender<?, ?> getPlasmaArcFurnaceRenderer() {
        return PlasmaArcFurnaceRender.INSTANCE;
    }
}
