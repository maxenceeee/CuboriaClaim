package net.xamence.cuboriaclaim.claim;

import net.xamence.cuboriaclaim.permissions.PermissionFlag;
import net.xamence.cuboriaclaim.permissions.PermissionService;
import net.xamence.cuboriaclaim.region.Region;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ClaimListener implements Listener {

    private final ClaimIndex claimIndex;
    private final PermissionService permissionService;

    public ClaimListener(ClaimIndex claimIndex, PermissionService permissionService) {
        this.claimIndex = claimIndex;
        this.permissionService = permissionService;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Claim currentClaim = getClaimAt(event.getBlock().getWorld().getName(), event.getBlock().getChunk().getX(), event.getBlock().getChunk().getZ());

        if (currentClaim == null) return;
        Region region = currentClaim.getRegion();

        if (!region.isActive()) return;

        if (!permissionService.hasPermission(region, player.getUniqueId(), PermissionFlag.BUILD.getFlag())) {
            event.setCancelled(true);
            //TODO: send message
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Claim currentClaim = getClaimAt(event.getBlock().getWorld().getName(), event.getBlock().getChunk().getX(), event.getBlock().getChunk().getZ());

        if (currentClaim == null) return;
        Region region = currentClaim.getRegion();

        if (!region.isActive()) return;

        if (!permissionService.hasPermission(region, player.getUniqueId(), PermissionFlag.BUILD.getFlag())) {
            event.setCancelled(true);
            //TODO: send message
        }
    }


    private boolean isContainer(Material material) {
        return switch (material) {
            case CHEST, BARREL, SHULKER_BOX, TRAPPED_CHEST, DISPENSER, DROPPER, HOPPER, FURNACE, BLAST_FURNACE, SMOKER, BREWING_STAND, CHEST_MINECART -> true;
            default -> false;
        };
    }

    private boolean isDoor(Material material) {
        return material.name().endsWith("_DOOR") || material.name().endsWith("_GATE") || material.name().contains("TRAPDOOR");
    }

    private boolean isRedstoneInteract(Material material) {
        boolean redstoneWithoutButtonAndPlate =  switch (material) {
            case LEVER, REDSTONE_WIRE, REPEATER, COMPARATOR -> true;
            default -> false;
        };
        if(!redstoneWithoutButtonAndPlate) {
            return material.name().endsWith("_BUTTON") || material.name().endsWith("_PRESSURE_PLATE");
        }
        return true;
    }

    private Claim getClaimAt(String world, int cx, int cz) {
        return claimIndex.get(world, cx, cz);
    }
}
