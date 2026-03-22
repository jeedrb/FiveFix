package jeed.fivefix;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Logger;

@Mod(modid = "fivefix", name = "FiveFix", version = "0.2.1")
public class FiveFix {
    public static final Logger LOGGER = Logger.getLogger("fivefix");

    @SidedProxy(clientSide = "jeed.fivefix.FiveFixClientProxy", serverSide = "jeed.fivefix.FiveFixCommonProxy")
    public static FiveFixCommonProxy proxy;

    public FiveFix() {
        LOGGER.setParent(FMLLog.getLogger());
        LOGGER.info("Hello, constructor!");
    }

    @Mod.PreInit
    public void preinit(FMLPreInitializationEvent e) {
        LOGGER.info("Hello, preinit!");
        proxy.hi();
    }
}