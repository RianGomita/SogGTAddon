package net.sog.core.client;

import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderManager;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.sog.core.client.renderer.machine.ArtificialStarRender;
import net.sog.core.client.renderer.machine.EyeOfHarmonyRender;
import net.sog.core.client.renderer.machine.PlasmaArcFurnaceRender;
import net.sog.core.sogcore;

@Mod.EventBusSubscriber(modid = sogcore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SoGClient {

    private SoGClient() {}

    public static void init(IEventBus modBus) {
        // You can remove this line as the @Mod.EventBusSubscriber annotation handles registration
        // modBus.register(sogClient.class);
        DynamicRenderManager.register(sogcore.id("eye_of_harmony"), EyeOfHarmonyRender.TYPE);
        DynamicRenderManager.register(sogcore.id("artificial_star"), ArtificialStarRender.TYPE);
        DynamicRenderManager.register(sogcore.id("plasma_arc_furnace"), PlasmaArcFurnaceRender.TYPE);
    }

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(EyeOfHarmonyRender.SPACE_SHELL_MODEL_RL);
        event.register(EyeOfHarmonyRender.STAR_MODEL_RL);
        EyeOfHarmonyRender.ORBIT_OBJECTS_RL.forEach(event::register);
        event.register(ArtificialStarRender.ARTIFICIAL_STAR_MODEL_RL);
        event.register(PlasmaArcFurnaceRender.RINGS_MODEL_RL);
        event.register(PlasmaArcFurnaceRender.SPHERE_MODEL_RL);
    }

}
