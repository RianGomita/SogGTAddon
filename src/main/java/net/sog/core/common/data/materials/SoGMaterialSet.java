package net.sog.core.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;

public class SoGMaterialSet {

    public static final MaterialIconType nanites = new MaterialIconType("nanites");
    public static final MaterialIconSet ALMOST_PURE_NEVONIAN_STEEL = new MaterialIconSet("almost_pure_nevonian_steel",
            SHINY);

    public static void init() {}
}
