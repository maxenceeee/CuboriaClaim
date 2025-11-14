package net.xamence.cuboriaclaim.region;

import net.xamence.cuboriaclaim.utils.ItemBase64Serializer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.*;

public class RegionInventoryStorage {

    private final Connection connection;

    public RegionInventoryStorage(Connection connection) throws SQLException {
        this.connection = connection;
        this.createTables();
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS region_inventory (region_id INTEGER NOT NULL, slot INTEGER NOT NULL, item TEXT NOT NULL, PRIMARY KEY(region_id, slot))");
    }

    public void saveInventory(Region region) throws SQLException {
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM region_inventory WHERE region_id=?");
        deleteStatement.setInt(1, region.getId());
        deleteStatement.executeUpdate();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO region_inventory(region_id, slot, item) VALUES (?, ?, ?)");
        Inventory inventory = region.getInventory();

        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack itemStack = inventory.getItem(slot);
            if (itemStack == null) continue;

            String serialized = ItemBase64Serializer.toBase64(itemStack);

            preparedStatement.setInt(1, region.getId());
            preparedStatement.setInt(2, slot);
            preparedStatement.setString(3, serialized);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    public void loadInventory(Region region) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM region_inventory WHERE region_id=?");
        preparedStatement.setInt(1, region.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        Inventory inventory = region.getInventory();
        while (resultSet.next()) {
            int slot = resultSet.getInt("slot");
            String base64 = resultSet.getString("item");

            inventory.setItem(slot, ItemBase64Serializer.fromBase64(base64));
        }
    }
}
