package net.xamence.cuboriaclaim.utils;

public class KeyUtils {

    public static long key(String world, int cx, int cz) {
        int worldHash = world.hashCode();
        return (((long) worldHash) << 32) | ((long) (cx & 0xFFFF) << 16) | (long) (cz & 0xFFFF);
    }
}
