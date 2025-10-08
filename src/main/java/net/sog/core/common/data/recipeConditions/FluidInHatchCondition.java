package net.sog.core.common.data.recipeConditions;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.sog.core.common.data.SoGMaterialRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
public class FluidInHatchCondition extends RecipeCondition {

    public static final Codec<FluidInHatchCondition> CODEC = RecordCodecBuilder.create(instance -> RecipeCondition
            .isReverse(instance).and(
                    Codec.STRING.fieldOf("fluidString").forGetter(FluidInHatchCondition::getFluidString))
            .and(
                    Codec.STRING.fieldOf("displayName").forGetter(FluidInHatchCondition::getDisplayName))
            .apply(instance, FluidInHatchCondition::new));

    @Getter
    private @NotNull String fluidString = "";
    @Getter
    private @NotNull String displayName = "Unknown Plasma";

    private @Nullable Fluid cachedFluid = null;

    public FluidInHatchCondition(boolean isReverse, @NotNull String fluidString, @NotNull String displayName) {
        super(isReverse);
        this.fluidString = fluidString;
        this.displayName = displayName;
    }

    public static FluidInHatchCondition of(@NotNull Fluid fluid) {
        ResourceLocation id = ForgeRegistries.FLUIDS.getKey(fluid);
        if (id == null) throw new IllegalArgumentException("Fluid has no registry ID: " + fluid);
        // Data generator safe - only stores the ID
        return new FluidInHatchCondition(false, id.toString(), "");
    }

    public static FluidInHatchCondition of(@NotNull Material material) {
        Fluid fluid = material.getFluid();
        if (fluid == null || fluid == Fluids.EMPTY) {
            throw new IllegalArgumentException("Material " + material + " does not have a valid plasma fluid.");
        }
        return of(fluid);
    }

    public static FluidInHatchCondition of(@NotNull String fluidId) {
        // Data generator safe - only stores the ID
        return new FluidInHatchCondition(false, fluidId, "");
    }

    public Fluid getFluid() {
        if (cachedFluid == null) {
            cachedFluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidString));
        }
        return cachedFluid;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Component getTooltips() {
        Fluid fluid = getFluid();
        if (fluid != null) {
            Material material = SoGMaterialRegistry.getMaterial(fluid);
            String localizedName = material != null ? I18n.get(material.getDefaultTranslation()) :
                    new ResourceLocation(fluidString).getPath().replace('_', ' ');

            if (material == null) {
                localizedName = Arrays.stream(localizedName.split(" "))
                        .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                        .collect(Collectors.joining(" "));
            }

            int color = material != null ? material.getMaterialARGB() : 0xFFFFFF;

            Component nameComponent = Component.literal(localizedName)
                    .withStyle(style -> style.withColor(TextColor.fromRgb(color)));
            return Component.translatable("phoenixcore.tooltip.requires_fluid", nameComponent);
        }
        return Component.literal("Requires an unknown fluid.");
    }

    @Override
    protected boolean testCondition(@NotNull GTRecipe recipe, @NotNull RecipeLogic recipeLogic) {
        Fluid requiredFluid = getFluid();
        if (requiredFluid == null) return false;
        var machine = recipeLogic.getMachine();
        if (!(machine instanceof WorkableElectricMultiblockMachine controller)) return false;
        var fluidHandlers = controller.getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP);
        for (var fluidHandler : fluidHandlers) {
            if (!(fluidHandler instanceof NotifiableFluidTank fluidTank)) continue;
            for (int i = 0; i < fluidTank.getTanks(); i++) {
                var fluidStack = fluidTank.getFluidInTank(i);
                if (!fluidStack.isEmpty() && fluidStack.getFluid().isSame(requiredFluid)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new FluidInHatchCondition();
    }

    @Override
    public RecipeConditionType<?> getType() {
        if (TYPE == null) {
            throw new IllegalStateException("FluidInHatchCondition.TYPE not registered yet!");
        }
        return TYPE;
    }

    public static RecipeConditionType<FluidInHatchCondition> TYPE;
}
