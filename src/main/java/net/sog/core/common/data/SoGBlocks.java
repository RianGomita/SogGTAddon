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

    public static void init() {
    }
}