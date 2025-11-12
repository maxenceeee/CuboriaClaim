package net.xamence.cuboriaclaim.claim;

import java.util.List;
import java.util.UUID;

public class ClaimRegion {

    private UUID claimOwner;
    private List<Claim> claimList;


    public List<Claim> getClaimList() {
        return claimList;
    }

    public UUID getClaimOwner() {
        return claimOwner;
    }
}
