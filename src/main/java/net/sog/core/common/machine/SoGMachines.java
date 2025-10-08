package net.sog.core.common.machine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.*;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.sog.core.client.renderer.machine.multiblock.SoGDynamicRenderHelpers;
import net.sog.core.sogcore;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.*;
import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;

@SuppressWarnings("all")
public class SoGMachines {

    static {
        REGISTRATE.creativeModeTab(() -> sogcore.SOG_CREATIVE_TAB);
    }

    public static final MultiblockMachineDefinition HONEY_CRYSTALLIZATION_CHAMBER = REGISTRATE
            .multiblock("honey_crystallization_chamber", WorkableElectricMultiblockMachine::new)
            .langValue("Honey Crystallization Chamber")
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, GTRecipeModifiers.PARALLEL_HATCH)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.EXTRUDER_RECIPES)
            .appearanceBlock(CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBCBBBBB",
                            "BBBBBCBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB")
                    .aisle("BDDDDDDDDDB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBCBBBBB", "BBBBEEEBBBB",
                            "BBBBEEEBBBB", "BBBBBCBBBBB", "BBBBBBBBBBB")
                    .aisle("BDDDDDDDDDB", "BBFBBBBBFBB", "BBFBBBBBFBB", "BBFBBCBBFBB", "BBFGGGGGFBB", "BBGGAAAGGBB",
                            "BBGGAAAGGBB", "BBBGGGGGBBB", "BBBBBCBBBBB")
                    .aisle("BDDDDDDDDDB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBCBCBCBBB", "BBGGGGGGGBB", "BBGAAAAAGBB",
                            "BBGAAAAAGBB", "BBGGGGGGGBB", "BBBBBCBBBBB")
                    .aisle("BDDDDDDDDDB", "BBBBBCBBBBB", "BBBBBCBBBBB", "BBBBCCCBBBB", "BBGGAAAGGBB", "BEAAAAAAAEB",
                            "BEAAAAAAAEB", "BBGGGAGGGBB", "BBBBBCBBBBB")
                    .aisle("BDDDDDDDDDB", "BBBBCDCBBBB", "BBBBCDCBBBB", "BBCCCCCCCBB", "BCGAACAAGCB", "CEAAACAAAEC",
                            "CEAAACAAAEC", "BCGGACAGGCB", "BBCCCCCCCBB")
                    .aisle("BDDDDDDDDDB", "BBBBBCBBBBB", "BBBBBCBBBBB", "BBBBCCCBBBB", "BBGGAAAGGBB", "BEAAAAAAAEB",
                            "BEAAAAAAAEB", "BBGGGAGGGBB", "BBBBBCBBBBB")
                    .aisle("BDDDDDDDDDB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBCBCBCBBB", "BBGGGGGGGBB", "BBGAAAAAGBB",
                            "BBGAAAAAGBB", "BBGGGGGGGBB", "BBBBBCBBBBB")
                    .aisle("BDDDDDDDDDB", "BBFBBBBBFBB", "BBFBBBBBFBB", "BBFBBCBBFBB", "BBFGGGGGFBB", "BBGGAAAGGBB",
                            "BBGGAAAGGBB", "BBBGGGGGBBB", "BBBBBCBBBBB")
                    .aisle("BDDDDDDDDDB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBCCCBBBB", "BBBCEHECBBB",
                            "BBBCEEECBBB", "BBBBCCCBBBB", "BBBBBBBBBBB")
                    .aisle("BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB",
                            "BBBBBBBBBBB", "BBBBBBBBBBB", "BBBBBBBBBBB")
                    .where("A", Predicates.air())
                    .where("B", Predicates.any())
                    .where("C",
                            blocks(ForgeRegistries.BLOCKS
                                    .getValue(ResourceLocation.fromNamespaceAndPath("gtceu",
                                            "steel_frame"))))
                    .where('D', blocks(CASING_BRONZE_BRICKS.get()))
                    .where('E', blocks(CASING_LAMINATED_GLASS.get()))
                    .where('F', blocks(CASING_STEEL_SOLID.get()))
                    .where("G", blocks(CASING_STAINLESS_CLEAN.get())
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2)
                                    .setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
                            .or(autoAbilities(true, false, true)))
                    .where('H', controller(blocks(definition.getBlock())))
                    .build())
            .model(
                    createWorkableCasingMachineModel(
                            GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                            .andThen(d -> d
                                    .addDynamicRenderer(
                                            SoGDynamicRenderHelpers::getPlasmaArcFurnaceRenderer)))
            .hasBER(true)
            .register();

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static void init() {}
}
