package net.xamence.cuboriaclaim.claim;

import org.bukkit.Location;

public class Claim {

    private final Location claimLocation;
    private final ClaimRegion claimRegion;

    public Claim(Location claimLocation, ClaimRegion claimRegion) {
        this.claimLocation = claimLocation;
        this.claimRegion = claimRegion;
    }

    public Location getClaimLocation() {
        return claimLocation;
    }

    public ClaimRegion getClaimRegion() {
        return claimRegion;
    }
}
