package jeed.fivefix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.EnumOptions;

public class FiveFixClientProxy extends FiveFixCommonProxy {
    public static final EnumOptions[] target;

    static {
        target = EnumOptions.values();
    }

    @Override
    public void hi() {
        FiveFix.LOGGER.info("Hello from ClientProxy");

        FiveFix.LOGGER.info("Minecraft's class name is: " + Minecraft.class.getName());
        FiveFix.LOGGER.info("Minecraft mcDataDir: " + Minecraft.getMinecraft().mcDataDir);
    }
}