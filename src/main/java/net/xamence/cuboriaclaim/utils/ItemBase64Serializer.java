package net.xamence.cuboriaclaim.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemBase64Serializer {

    public static String toBase64(ItemStack item) {
        if(item == null) return null;

        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(arrayOutputStream);

            bukkitObjectOutputStream.writeObject(item);
            return Base64.getEncoder().encodeToString(arrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ItemStack fromBase64(String base64) {
        if (base64 == null) return null;

        try {

            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(base64));
            BukkitObjectInputStream ois = new BukkitObjectInputStream(bais);

            return (ItemStack) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
