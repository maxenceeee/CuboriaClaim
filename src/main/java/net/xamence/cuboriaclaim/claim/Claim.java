package net.xamence.cuboriaclaim.claim;

import net.xamence.cuboriaclaim.region.Region;

import java.util.UUID;

public class Claim {

    private UUID owner;
    private final String world;
    private final int cx;
    private final int cz;

    private Region region;

    public Claim(UUID owner, String world, int cx, int cz) {
        this.owner = owner;
        this.world = world;
        this.cx = cx;
        this.cz = cz;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getWorld() {
        return world;
    }

    public int getCx() {
        return cx;
    }

    public int getCz() {
        return cz;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
