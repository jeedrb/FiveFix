package jeed.fivefix;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "fivefix", name = "FiveFix", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FiveFix {
    @Mod.Instance("fivefix")
    public static FiveFix instance;

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        System.out.println("i had such a great time getting this environment working!");
    }
}
