package net.sog.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.ToolProperty;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;

import net.sog.core.common.data.SoGMaterialRegistry;
import net.sog.core.sogcore;

import static net.minecraft.world.item.enchantment.Enchantments.SILK_TOUCH;

public class SoGMaterials {

    public static Material QuantumCoolant;
    public static Material ExtremelyModifiedSpaceGradeSteel;
    public static Material EightyFivePercentPureNevvonianSteel;
    public static Material ENRICHED_TRITANIUM;
    public static Material SILLY_ENRICHED_NAQUADAH;
    public static Material ALUMINFROST;

    public static void register() {
        ALUMINFROST = new Material.Builder(
                sogcore.id("aluminfrost"))
                .color(0xadd8e6).secondaryColor(0xc0c0c0).iconSet(MaterialIconSet.DULL)
                .toolStats(ToolProperty.Builder.of(1.8F, 1.7F, 700, 3)
                        .types(
                                GTToolType.SWORD,
                                GTToolType.PICKAXE,
                                GTToolType.SHOVEL)
                        .unbreakable()
                        .enchantment(SILK_TOUCH, 1)
                        .build())
                .buildAndRegister();
        QuantumCoolant = new Material.Builder(
                sogcore.id("quantum_coolant"))
                .plasma()
                .buildAndRegister();
        SoGMaterialRegistry.register(QuantumCoolant);
        EightyFivePercentPureNevvonianSteel = new Material.Builder(
                sogcore.id("eighty_five_percent_pure_nevvonian_steel"))
                .ingot()
                .flags(SoGMaterialFlags.GENERATE_NANITES)
                .element(SoGElements.APNS)
                .formula("APNS")
                .secondaryColor(593856)
                .toolStats(new ToolProperty(12.0F, 7.0F, 3072, 6,
                        new GTToolType[] { GTToolType.DRILL_IV, GTToolType.MINING_HAMMER }))
                .buildAndRegister();
        ENRICHED_TRITANIUM = new Material.Builder(
                sogcore.id("enriched_tritanium"))
                .ingot()
                .color(0xFF0000)
                .secondaryColor(0x840707)
                .flags(MaterialFlags.GENERATE_FRAME, SoGMaterialFlags.GENERATE_NANITES)
                .formula("PET")
                .iconSet(SoGMaterialSet.ALMOST_PURE_NEVONIAN_STEEL)
                .buildAndRegister();
        SILLY_ENRICHED_NAQUADAH = new Material.Builder(
                sogcore.id("silly_enriched_naquadah"))
                .ingot()
                .color(0xFFA500)
                .secondaryColor(0x000000)
                .flags(MaterialFlags.GENERATE_FRAME, SoGMaterialFlags.GENERATE_NANITES)
                .formula("PENaq")
                .iconSet(MaterialIconSet.SHINY)
                .buildAndRegister();
    }

    public static void modifyMaterials() {}
}
