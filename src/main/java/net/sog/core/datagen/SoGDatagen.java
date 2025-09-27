package net.sog.core.datagen;


import com.tterrag.registrate.providers.ProviderType;
import net.sog.core.datagen.lang.SoGLangHandler;

import static net.sog.core.common.registry.SoGRegistration.REGISTRATE;


public class SoGDatagen {

    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, SoGLangHandler::init);
    }
}
