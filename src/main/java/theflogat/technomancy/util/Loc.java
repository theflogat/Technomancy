package theflogat.technomancy.util;

import cpw.mods.fml.common.FMLCommonHandler;

public final class Loc {

    private Loc() {}
    
    public static boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide().isServer();
    }

}
