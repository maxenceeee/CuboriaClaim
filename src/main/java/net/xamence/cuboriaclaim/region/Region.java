package net.xamence.cuboriaclaim.region;


import net.xamence.cuboriaclaim.claim.Claim;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Region implements InventoryHolder {

    private final int id;
    private final Set<Claim> claims;
    private final UUID owner;

    private RegionInventory regionInventory;
    private long points;

    private Map<UUID, Integer> permissions;

    private boolean active;

    public Region(int id, long points, UUID owner) {
        this.id = id;
        this.points = points;

        this.claims = new HashSet<>();
        this.regionInventory = new RegionInventory(this);
        this.owner = owner;

        this.permissions = new HashMap<>();

        this.active = true;
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

    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }

    public void removeClaim(Claim claim) {
        this.claims.remove(claim);
    }

    public UUID getOwner() {
        return owner;
    }

    public Map<UUID, Integer> getPermissions() {
        return permissions;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.regionInventory.getBukkitInventory();
    }
}
;