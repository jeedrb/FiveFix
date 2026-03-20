package jeed.fivefix;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.EnumSet;

public class FiveFixUtilKeyHandler extends KeyHandler {

    public FiveFixUtilKeyHandler(KeyBinding[] utilBinds) { super(utilBinds, new boolean[utilBinds.length]); }

    @Override
    public String getLabel() {
        return "FiveFixUtilKeyHandler";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding pressed, boolean tickEnd, boolean isRepeat) {
        Minecraft theCraft = Minecraft.getMinecraft();

        if (tickEnd && theCraft.thePlayer != null && theCraft.currentScreen == null) { // if we can
            if (pressed == FiveFixClientProxy.bindF5 && pressed.keyCode != 63) // cycle between three perspective states if original F5 key isn't pressed
                theCraft.gameSettings.thirdPersonView = (theCraft.gameSettings.thirdPersonView + 1) % 3;

            if (pressed == FiveFixClientProxy.bindF3 && pressed.keyCode != 61) // toggle debug info if original F3 isn't pressed
                theCraft.gameSettings.showDebugInfo = !theCraft.gameSettings.showDebugInfo;
        }
    }

    @Override public void keyUp(EnumSet<TickType> t, KeyBinding k, boolean te) {}
    @Override public EnumSet<TickType> ticks() { return EnumSet.of(TickType.CLIENT); }
}