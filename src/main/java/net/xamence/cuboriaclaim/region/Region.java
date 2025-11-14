package net.xamence.cuboriaclaim.region;


import net.xamence.cuboriaclaim.claim.Claim;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Region implements InventoryHolder {

    private final int id;
    private final Set<Claim> claims;
    private RegionInventory regionInventory;
    private long points;


    public Region(int id, long points) {
        this.id = id;
        this.points = points;

        this.claims = new HashSet<>();
        this.regionInventory = new RegionInventory(this);
    }

    public int getId() {
        return id;
    }

    public Set<Claim> getClaims() {
        return claims;
    }

    public RegionInventory getRegionInventory() {
        return regionInventory;
    }

    public long getPoints() {
        return points;
    }

    public void addPoints(long points) {
        this.points += points;
    }

    public void removePoints(long points) {
        this.points -= points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.regionInventory.getBukkitInventory();
    }
}
;