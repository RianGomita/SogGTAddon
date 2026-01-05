package net.sog.core.common.data;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.sog.core.common.registry.SoGRegistration;

import static net.sog.core.sogcore.SOG_CREATIVE_TAB;

public class SoGBlocks {

    static {
        SoGRegistration.REGISTRATE.creativeModeTab(() -> SOG_CREATIVE_TAB);
    }

    public static final BlockEntry<Block> RED_BLOCK_EZLYCH = SoGRegistration.REGISTRATE
            .block("red_block_ezlych", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .lang("Ezlych's Red Block")
            .blockstate((ctx, prov) -> {
                prov.simpleBlock(ctx.getEntry(),
                        prov.models().cubeAll(ctx.getName(),
                                prov.modLoc("block/casings/redblock")));
            })
            .simpleItem()
            .register();
    public static final BlockEntry<Block> COLLIDER_STRUCTURAL_CASING = SoGRegistration.REGISTRATE
            .block("collider_structural_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .lang("Collider Structural Casing")
            .blockstate((ctx, prov) -> {
                prov.simpleBlock(ctx.getEntry(),
                        prov.models().cubeAll(ctx.getName(),
                                prov.modLoc("block/casings/collider_structural_casing")));
            })
            .simpleItem()
            .register();
    public static final BlockEntry<Block> HADRONIC_COMPRESSION_CASING = SoGRegistration.REGISTRATE
            .block("hadronic_compression_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .lang("Hadronic Compression Casing")
            .blockstate((ctx, prov) -> {
                prov.simpleBlock(ctx.getEntry(),
                        prov.models().cubeAll(ctx.getName(),
                                prov.modLoc("block/casings/hadronic_compression_casing")));
            })
            .simpleItem()
            .register();
    public static final BlockEntry<Block> HIGH_ENERGY_COLLIDER_CASING = SoGRegistration.REGISTRATE
            .block("high_energy_collider_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .lang("High Energy Collider Casing")
            .blockstate((ctx, prov) -> {
                prov.simpleBlock(ctx.getEntry(),
                        prov.models().cubeAll(ctx.getName(),
                                prov.modLoc("block/casings/high_energy_collider_casing")));
            })
            .simpleItem()
            .register();
    public static final BlockEntry<Block> QUANTUM_FIELD_STABILIZATION_CASING = SoGRegistration.REGISTRATE
            .block("quantum_field_stabilization_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .lang("Quantum Field Stabilization Casing")
            .blockstate((ctx, prov) -> {
                prov.simpleBlock(ctx.getEntry(),
                        prov.models().cubeAll(ctx.getName(),
                                prov.modLoc("block/casings/quantum_field_stabilization_casing")));
            })
            .simpleItem()
            .register();
    public static final BlockEntry<Block> ULTRA_DENSE_COLLIDER_CASING = SoGRegistration.REGISTRATE
            .block("ultra_dense_collider_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .lang("Ultra Dense Collider Casing")
            .blockstate((ctx, prov) -> {
                prov.simpleBlock(ctx.getEntry(),
                        prov.models().cubeAll(ctx.getName(),
                                prov.modLoc("block/casings/ultra_dense_collider_casing")));
            })
            .simpleItem()
            .register();

    public static void init() {
    }
}