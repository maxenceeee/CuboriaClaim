package net.xamence.cuboriaclaim.claim;

import net.xamence.cuboriaclaim.region.Region;
import net.xamence.cuboriaclaim.utils.ClaimNeighborUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClaimManager {

    private final ClaimIndex index;
    private final RegionManager regionManager;

    public ClaimManager(ClaimIndex index, RegionManager regionManager) {
        this.index = index;
        this.regionManager = regionManager;
    }

    public Claim createClaim(UUID owner, String world, int cx, int cz) {
        if(index.exist(world, cx, cz)) return null;

        Claim newClaim = new Claim(owner, world, cx, cz);

        Set<Region> neighbors = new HashSet<>();
        for(int[] offset : ClaimNeighborUtils.CLAIM_OFFSET) {
            int nx = cx + offset[0];
            int nz = cz + offset[1];

            Claim neighborClaim = index.get(world, nx, nz);
            if(!neighborClaim.getRegion().getOwner().equals(owner)) continue;

            if (neighborClaim != null && neighborClaim.getRegion() != null) {
                neighbors.add(neighborClaim.getRegion());
            }
        }

        Region finalRegion = null;

        if (neighbors.isEmpty()) {
            finalRegion = regionManager.createRegion();
        } else if (neighbors.size() == 1) {
            finalRegion = neighbors.iterator().next();
        } else {
            // TODO: Check if region are owned by the same player
            finalRegion = regionManager.merge(neighbors);
        }

        newClaim.setRegion(finalRegion);
        finalRegion.addClaim(newClaim);

        index.add(newClaim);

        return newClaim;
    }

    public void removeClaim(String world, int cx, int cz) {
        Claim claim = index.get(world, cx, cz);
        if(claim == null) return;

        Region region = claim.getRegion();
        region.removeClaim(claim);

        index.remove(world, cx, cz);

        if(region.getClaims().isEmpty()) {
            regionManager.deleteRegion(region);
        }
    }
}
