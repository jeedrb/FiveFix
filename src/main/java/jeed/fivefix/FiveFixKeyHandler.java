package jeed.fivefix;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import java.util.EnumSet;

public class FiveFixKeyHandler extends KeyHandler {

    public FiveFixKeyHandler() {
        super(FiveFixClientProxy.hotbarBinds, new boolean[9]);
    }

    @Override
    public String getLabel() {
        return "FiveFixHandler";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        Minecraft theCraft = Minecraft.getMinecraft();

        // only run when possible and once per game bla bla bla
        if (tickEnd && theCraft.thePlayer != null && theCraft.currentScreen == null) {
            for (int i = 0; i < 9; i++) {
                if (kb == FiveFixClientProxy.hotbarBinds[i]) {
                    theCraft.thePlayer.inventory.currentItem = i;
                    break;
                }
            }
        }
    }

    @Override public void keyUp(EnumSet<TickType> t, KeyBinding k, boolean te) {}
    @Override public EnumSet<TickType> ticks() { return EnumSet.of(TickType.CLIENT); }
}