package net.xamence.cuboriaclaim.claim;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClaimManager {

    private final List<ClaimRegion> claimRegionList;

    public ClaimManager() {
        this.claimRegionList = new ArrayList<>();
    }


    public ClaimRegion getClaimRegionByOwner(UUID owner) {
        return this.claimRegionList.stream().filter(claimRegion -> claimRegion.getClaimOwner().equals(owner)).findAny().orElse(null);
    }
}
