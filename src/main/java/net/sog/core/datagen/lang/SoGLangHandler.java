package net.sog.core.datagen.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class SoGLangHandler {

    public static void init(RegistrateLangProvider provider) {
        provider.add("soggtaddon.tooltip.requires_fluid", "Needs: %s");
        provider.add("tagprefix.nanites", "%s Nanites");
        provider.add("material.soggtaddon.enriched_tritanium", "Enriched Tritanium");
        provider.add("material.soggtaddon.extremely_modified_space_grade_steel",
                "§cExtremely Modified Space Grade Steel");
        provider.add("material.soggtaddon.quantum_coolant",
                "§bQuantum Coolant");
        provider.add("material.soggtaddon.eighty_five_percent_pure_nevvonian_steel",
                "§6Eighty Five Percent Pure Nevvonian Steel");
        provider.add("material.soggtaddon.enriched_naquadah",
                "§6Silly Enriched Naquadah");
    }

    protected static String getSubKey(String key, int index) {
        return key + "." + index;
    }
}
