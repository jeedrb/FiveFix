package jeed.fivefix;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.EnumOptions;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.security.Key;

public class FiveFixClientProxy extends FiveFixCommonProxy {
    public static final EnumOptions[] target;

    static {
        target = EnumOptions.values();
    }

    public static KeyBinding[] hotbarBinds = new KeyBinding[9]; // hotbar bind array
    public static KeyBinding bindF5, bindF3; // perspective and debug binds

    @Override
    public void hi() {
        FiveFix.LOGGER.info("Hello from ClientProxy");

        FiveFix.LOGGER.info("Minecraft's class name is: " + Minecraft.class.getName());
        FiveFix.LOGGER.info("Minecraft mcDataDir: " + Minecraft.getMinecraft().mcDataDir);


//        int[] defaultHotBinds = {
//            Keyboard.KEY_1,
//            Keyboard.KEY_2,
//            Keyboard.KEY_3,
//            Keyboard.KEY_4,
//            Keyboard.KEY_5,
//            Keyboard.KEY_6,
//            Keyboard.KEY_7,
//            Keyboard.KEY_8,
//            Keyboard.KEY_9
//        };

        for (int i = 0; i < 9; i++) // loop to set default hotbar binds
            hotbarBinds[i] = new KeyBinding("Hotbar Slot " + (i + 1), (i + 2));

        // register hotbar bind stuff and use the classes and whatever
        KeyBindingRegistry.instance().registerKeyBinding(new FiveFixHotbarKeyHandler());
        TickRegistry.registerTickHandler(new FiveFixTickHandler(), Side.CLIENT);

        // set default F5 and F3 binds
        bindF5 = new KeyBinding("Perspective", 63);
        bindF3 = new KeyBinding("Debug", 61);

        KeyBinding[] utilBinds = {bindF5, bindF3};
        KeyBindingRegistry.instance().registerKeyBinding(new FiveFixUtilKeyHandler(utilBinds));
    }
}