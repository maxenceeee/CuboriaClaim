package net.xamence.cuboriaclaim.permissions;

public enum PermissionFlag {
    BUILD(1),
    BREAK(1<<1),
    INTERACT(1<<2),
    CONTAINER(1<<3),
    DOOR(1<<4),
    REDSTONE(1<<5),
    PVP(1<<6),
    EXPAND(1<<7),
    ADMIN(1<<9) // ALL PERM + MANAGE PERM
    ;


    private int flag;

    PermissionFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
