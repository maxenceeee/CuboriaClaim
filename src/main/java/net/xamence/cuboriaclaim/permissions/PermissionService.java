package net.xamence.cuboriaclaim.permissions;

import net.xamence.cuboriaclaim.region.Region;

import java.util.UUID;

public class PermissionService {

    public void addPermission(Region region, UUID player, int permFlag) {
        int current = region.getPermissions().getOrDefault(player, 0);
        region.getPermissions().put(player, current | permFlag);
    }

    public void removePermission(Region region, UUID player, int permFlag) {
        int current = region.getPermissions().getOrDefault(player, 0);
        region.getPermissions().put(player, current & ~permFlag);
    }

    public boolean hasPermission(Region region, UUID player, int permFlag) {
        int flags = region.getPermissions().getOrDefault(player, 0);
        return (flags & permFlag) != 0;
    }
}
