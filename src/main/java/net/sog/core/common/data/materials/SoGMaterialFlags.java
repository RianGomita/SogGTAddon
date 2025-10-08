package net.sog.core.common.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_DENSE;

public class SoGMaterialFlags {

    public static final MaterialFlag GENERATE_NANITES = new MaterialFlag.Builder("generate_nanites")
            .requireFlags(GENERATE_DENSE).requireProps(PropertyKey.DUST).build();

    public static final TagPrefix nanites = new TagPrefix("nanites")
            .idPattern("%s_nanites")
            .defaultTagPath("nanites/%s")
            .unformattedTagPath("nanites")
            .langValue("%s Nanites")
            .materialAmount(GTValues.M / 4)
            .unificationEnabled(true)
            .generateItem(true)
            .materialIconType(SoGMaterialSet.nanites)
            .generationCondition(mat -> mat.hasFlag(SoGMaterialFlags.GENERATE_NANITES));

    public static void init() {}
}
