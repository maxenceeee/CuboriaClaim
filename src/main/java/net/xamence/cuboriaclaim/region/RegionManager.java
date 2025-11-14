package net.xamence.cuboriaclaim.region;

import net.xamence.cuboriaclaim.claim.Claim;

import java.util.*;

public class RegionManager {

    private final Map<Integer, Region> regions;
    private int nextId;

    public RegionManager() {
        this.regions = new HashMap<>();
        this.nextId = 1;
    }

    public Region createRegion(UUID owner) {
        Region region = new Region(nextId++, 0, owner);
        this.regions.put(region.getId(), region);
        return region;
    }

    public void loadRegion(int id, UUID owner, int points) {
        Region region = new Region(id, points, owner);
        regions.put(id, region);

        if(id >= nextId)
            nextId = id + 1;
    }

    public Region getRegion(int id) {
        return regions.get(id);
    }

    public Collection<Region> getAllRegions() {
        return regions.values();
    }

    public void deleteRegion(Region region) {
        regions.remove(region.getId());
    }

    public Region merge(Set<Region> regionSet) {
        Iterator<Region> regionIterator = regionSet.iterator();
        Region base = regionIterator.next();

        while (regionIterator.hasNext()) {
            Region other = regionIterator.next();

            if(!other.getOwner().equals(base.getOwner())) continue;

            for (Claim claim : other.getClaims()) {
                base.addClaim(claim);
            }

            base.getPermissions().putAll(other.getPermissions());
            base.addPoints(other.getPoints());

            regions.remove(other.getId());
        }

        return base;
    }
}
