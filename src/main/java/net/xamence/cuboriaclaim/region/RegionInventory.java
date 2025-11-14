package net.xamence.cuboriaclaim.region;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RegionInventory {

    private final Inventory bukkitInventory;
    private final Region owner;

    public RegionInventory(Region owner) {
        this.owner = owner;
        this.bukkitInventory = Bukkit.createInventory(owner, 54, "Resource for region: " + owner.getId());
    }

    public void open(Player player) {
        player.openInventory(bukkitInventory);
    }

    // Return false if no items can be used
    public boolean consumePoints() {
        for (int slot = 0; slot < bukkitInventory.getSize(); slot++) {
            ItemStack itemStack = bukkitInventory.getItem(slot);
            if(itemStack == null) continue;

            int points = getPointsValue(itemStack);

            this.owner.addPoints(points);

            bukkitInventory.setItem(slot, null);
            return true;
        }

        return false;
    }

    public int getPointsValue(ItemStack itemStack) {
        Material material = itemStack.getType();
        int amount = itemStack.getAmount();

        // TODO: Adding some value
        return 1;
    }

    // To view the chest points
    public int computeStoredPointsValue() {
        int total = 0;

        for (ItemStack itemStack : bukkitInventory.getContents()) {
            if (itemStack == null) continue;
            total += getPointsValue(itemStack);
        }

        return total;
    }

    public Inventory getBukkitInventory() {
        return bukkitInventory;
    }
}
