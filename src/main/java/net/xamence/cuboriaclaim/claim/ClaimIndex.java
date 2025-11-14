package net.xamence.cuboriaclaim.claim;

import net.xamence.cuboriaclaim.utils.KeyUtils;

import java.util.HashMap;
import java.util.Map;

public class ClaimIndex {

    private final Map<Long, Claim> claimIndexMap = new HashMap<>();

    public void add(Claim claim) {
        claimIndexMap.put(KeyUtils.key(claim.getWorld(), claim.getCx(), claim.getCz()), claim);
    }

    public Claim get(String world, int cx, int cz) {
        return claimIndexMap.get(KeyUtils.key(world, cx, cz));
    }

    public boolean exist(String world, int cx, int cz) {
        return claimIndexMap.containsKey(KeyUtils.key(world, cx, cz));
    }

    public void remove(String world, int cx, int cz) {
        claimIndexMap.remove(KeyUtils.key(world, cx, cz));
    }

    public Map<Long, Claim> getClaimIndexMap() {
        return claimIndexMap;
    }
}
