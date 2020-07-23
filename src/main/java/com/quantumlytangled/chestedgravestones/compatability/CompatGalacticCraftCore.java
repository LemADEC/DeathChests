package com.quantumlytangled.chestedgravestones.compatability;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import micdoodle8.mods.galacticraft.api.inventory.AccessInventoryGC;
import micdoodle8.mods.galacticraft.api.inventory.IInventoryGC;

public class CompatGalacticCraftCore implements ICompatInventory {
  
  private static final CompatGalacticCraftCore INSTANCE = new CompatGalacticCraftCore();
  
  public static CompatGalacticCraftCore getInstance() {
      return INSTANCE;
  }
  
  @Override
  public NonNullList<ItemStack> getAllContents(EntityPlayerMP player) {
    IInventoryGC inventory = AccessInventoryGC.getGCInventoryForPlayer(player);
    
    NonNullList<ItemStack> invContents = NonNullList
        .withSize(inventory.getSizeInventory(), ItemStack.EMPTY);
    for (int i = 0; i < inventory.getSizeInventory(); i++) {
      invContents.set(i, inventory.getStackInSlot(i));
    }
    
    return invContents;
  }
  
  @Override
  public void setItem(int slot, ItemStack item, EntityPlayerMP player) {
    IInventoryGC inventory = AccessInventoryGC.getGCInventoryForPlayer(player);
    inventory.setInventorySlotContents(slot, item);
  }
  
  @Override
  public boolean isSlotEmpty(int slot, EntityPlayerMP player) {
    IInventoryGC inventory = AccessInventoryGC.getGCInventoryForPlayer(player);
    return inventory.getStackInSlot(slot).isEmpty();
  }
  
  @Override
  public void clearInventory(EntityPlayerMP player) {
    IInventoryGC inventory = AccessInventoryGC.getGCInventoryForPlayer(player);
    
    for (int i = 0; i < inventory.getSizeInventory(); ++i) {
      inventory.setInventorySlotContents(i, ItemStack.EMPTY);
    }
  }
}
