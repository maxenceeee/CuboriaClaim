package net.xamence.cuboriaclaim.region;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class RegionInventory {

    private final Inventory bukkitInventory;

    public RegionInventory(Region owner) {
        this.bukkitInventory = Bukkit.createInventory(owner, InventoryType.CHEST);
    }

    public Inventory getBukkitInventory() {
        return bukkitInventory;
    }
}
