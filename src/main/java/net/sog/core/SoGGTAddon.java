package net.sog.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;
import net.sog.core.common.data.materials.SoGElements;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class SoGGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return sogcore.SOG_REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return sogcore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        // CustomTagPrefixes.init();
    }
    @Override
    public void registerElements() {
        IGTAddon.super.registerElements();
        SoGElements.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        // CustomRecipes.init(provider);
    }

    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.
    /*
     * public static final ContentJS<Double> PRESSURE_IN = new ContentJS<>(NumberComponent.ANY_DOUBLE,
     * CustomRecipeCapabilities.PRESSURE, false);
     * public static final ContentJS<Double> PRESSURE_OUT = new ContentJS<>(NumberComponent.ANY_DOUBLE,
     * CustomRecipeCapabilities.PRESSURE, true);
     * 
     * @Override
     * public void registerRecipeKeys(KJSRecipeKeyEvent event) {
     * event.registerKey(CustomRecipeCapabilities.PRESSURE, Pair.of(PRESSURE_IN, PRESSURE_OUT));
     * }
     */
}
