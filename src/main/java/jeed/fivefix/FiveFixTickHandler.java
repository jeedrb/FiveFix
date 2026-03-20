package jeed.fivefix;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.input.Keyboard;

import java.util.EnumSet;

public class FiveFixTickHandler implements ITickHandler {
    private final boolean[] isDown = new boolean[9];

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        // Leave this empty
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        Minecraft theCraft = Minecraft.getMinecraft();

        // check if an inventory/container gui is open
        if (theCraft.thePlayer != null && theCraft.currentScreen instanceof GuiContainer) {
            GuiContainer currentGui = (GuiContainer) theCraft.currentScreen;

            for (int i = 0; i < 9; i++) {
                int keyCode = FiveFixClientProxy.hotbarBinds[i].keyCode; // get the custom bound keycode

                if (Keyboard.isKeyDown(keyCode)) {
                    if (!isDown[i]) {
                        if (!(keyCode >= 2 && keyCode <= 10))
                            invSwap(currentGui, i);

                        isDown[i] = true;
                    }
                } else {
                    isDown[i] = false;
                }
            }
        }
    }

    private void invSwap(GuiContainer currentGui, int slot) {
        Minecraft theCraft = Minecraft.getMinecraft();

        try {
            // 1. Get the 'theSlot' field (the slot under the mouse)
            // In 1.5.2 MCP mappings, this is "theSlot" or field_74192_v
            java.lang.reflect.Field slotField;
            try {
                slotField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("theSlot");
            } catch (NoSuchFieldException e) {
                slotField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("field_74192_v");
            }

            slotField.setAccessible(true);
            net.minecraft.inventory.Slot hoverSlot = (net.minecraft.inventory.Slot) slotField.get(currentGui);

            if (hoverSlot != null) {
                // 2. Get the 'inventorySlots' field (the Container itself)
                // In 1.5.2 MCP, this is "inventorySlots" or field_74188_l
                java.lang.reflect.Field containerField;
                try {
                    containerField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("inventorySlots");
                } catch (NoSuchFieldException e) {
                    containerField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("field_74188_l");
                }
                containerField.setAccessible(true);
                net.minecraft.inventory.Container container = (net.minecraft.inventory.Container) containerField.get(currentGui);

                // 3. Perform the window click (Mode 2 = Hotbar Swap)
                if (container != null) {
                    theCraft.playerController.windowClick(container.windowId, hoverSlot.slotNumber, slot, 2, theCraft.thePlayer);
                }
            }
        } catch (Exception e) {
            FiveFix.LOGGER.warning("Failed to swap item: " + e.getMessage());
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "FiveFixTick";
    }

}
