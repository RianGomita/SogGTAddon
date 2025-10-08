package net.sog.core.common.data;

import net.minecraft.world.item.Item;


import com.tterrag.registrate.util.entry.ItemEntry;
import net.sog.core.common.registry.SoGRegistration;

import static net.sog.core.sogcore.SOG_CREATIVE_TAB;


public class SoGItems {

    static {
        SoGRegistration.REGISTRATE.creativeModeTab(() -> SOG_CREATIVE_TAB);
    }
    // Modelo explícito para quantum_anomaly
    public static ItemEntry<Item> basic_fuel_rod = SoGRegistration.REGISTRATE
            .item("basic_fuel_rod", Item::new)
            .model((ctx, prov) -> {
                prov.withExistingParent(ctx.getName(), prov.mcLoc("item/generated"))
                        .texture("layer0", prov.modLoc("item/" + ctx.getName()));
            })
            .register();

    public static void init() {}
}
