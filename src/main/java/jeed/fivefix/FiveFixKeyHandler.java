package jeed.fivefix;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
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
    public void keyDown(EnumSet<TickType> types, KeyBinding bind, boolean tickEnd, boolean isRepeat) {
        Minecraft theCraft = Minecraft.getMinecraft();

        // only run when possible and once per game bla bla bla
        if (tickEnd && theCraft.thePlayer != null && theCraft.currentScreen == null) {

            // not in a menu
            if (theCraft.currentScreen == null) {
                for (int i = 0; i < 9; i++) {
                    if (bind == FiveFixClientProxy.hotbarBinds[i]) {
                        theCraft.thePlayer.inventory.currentItem = i;
                        break;
                    }
                }
            }

            // in a menu/inventory/container (doesn't work lol)
//            if (theCraft.currentScreen instanceof GuiContainer) {
//                if(bind.keyCode >=2 && bind.keyCode <= 10) return; // bail if an actual number is being pressed
//
//                GuiContainer currentGui = (GuiContainer) theCraft.currentScreen;
//
//                for  (int i = 0; i < 9; i++) {
//                    if (bind == FiveFixClientProxy.hotbarBinds[i]) {
//                        invSwap(currentGui, i);
//                        break;
//                    }
//                }
//            }
        }
    }

//    private void invSwap(GuiContainer currentGui, int slot) {
//        Minecraft theCraft = Minecraft.getMinecraft();
//
//        try {
//            // 1. Get the 'theSlot' field (the slot under the mouse)
//            // In 1.5.2 MCP mappings, this is "theSlot" or field_74192_v
//            java.lang.reflect.Field slotField;
//            try {
//                slotField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("theSlot");
//            } catch (NoSuchFieldException e) {
//                slotField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("field_74192_v");
//            }
//
//            slotField.setAccessible(true);
//            net.minecraft.inventory.Slot hoverSlot = (net.minecraft.inventory.Slot) slotField.get(currentGui);
//
//            if (hoverSlot != null) {
//                // 2. Get the 'inventorySlots' field (the Container itself)
//                // In 1.5.2 MCP, this is "inventorySlots" or field_74188_l
//                java.lang.reflect.Field containerField;
//                try {
//                    containerField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("inventorySlots");
//                } catch (NoSuchFieldException e) {
//                    containerField = net.minecraft.client.gui.inventory.GuiContainer.class.getDeclaredField("field_74188_l");
//                }
//                containerField.setAccessible(true);
//                net.minecraft.inventory.Container container = (net.minecraft.inventory.Container) containerField.get(currentGui);
//
//                // 3. Perform the window click (Mode 2 = Hotbar Swap)
//                if (container != null) {
//                    theCraft.playerController.windowClick(container.windowId, hoverSlot.slotNumber, slot, 2, theCraft.thePlayer);
//                }
//            }
//        } catch (Exception e) {
//            FiveFix.LOGGER.warning("Failed to swap item: " + e.getMessage());
//        }
//    }

    @Override public void keyUp(EnumSet<TickType> t, KeyBinding k, boolean te) {}
    @Override public EnumSet<TickType> ticks() { return EnumSet.of(TickType.CLIENT); }
}