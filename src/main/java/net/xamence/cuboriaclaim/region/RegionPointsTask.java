package net.xamence.cuboriaclaim.region;

import org.bukkit.scheduler.BukkitRunnable;

public class RegionPointsTask extends BukkitRunnable {

    private final RegionManager regionManager;

    private final int consumptionPerChunk; // Per Minutes
    private final int minPoints;

    public RegionPointsTask(RegionManager regionManager, int consumptionPerChunk, int minPoints) {
        this.regionManager = regionManager;
        this.consumptionPerChunk = consumptionPerChunk;
        this.minPoints = minPoints;
    }

    @Override
    public void run() {
        for (Region region : this.regionManager.getAllRegions()) {
            int chunks = region.getClaims().size();
            int consumed = chunks * consumptionPerChunk;

            region.removePoints(consumed);
            while (region.getPoints() <= minPoints) {
                // No item in chest
                if(!region.getRegionInventory().consumePoints()) {
                    region.setPoints(0);
                    this.disableRegion(region);
                    break;
                }
            }

            if(!region.isActive())
                this.enableRegion(region);
        }
    }

    private void enableRegion(Region region) {
        region.setActive(true);
    }

    private void disableRegion(Region region) {
        region.setActive(false);
    }
}
