package net.xamence.cuboriaclaim.permissions;

import net.xamence.cuboriaclaim.region.Region;

import java.sql.*;
import java.util.Map;
import java.util.UUID;

public class PermissionStorage {

    private final Connection connection;
    private final RegionManager regionManager;

    public PermissionStorage(Connection connection, RegionManager regionManager) throws SQLException {
        this.connection = connection;
        this.regionManager = regionManager;
        this.createTables();
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS region_perm (region_id INTEGER NOT NULL, player TEXT NOT NULL, flags INTEGER NOT NULL, PRIMARY KEY(region_id, player));");
    }

    public void saveRegionPerms(Region region) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT OR REPLACE INTO region_perm(region_id, player, flags) VALUES (?, ?, ?)");
        for (Map.Entry<UUID, Integer> entry : region.getPermissions().entrySet()) {
            preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(2, entry.getKey().toString());
            preparedStatement.setInt(3, entry.getValue());
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }

    public void loadRegionPermission() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM region_id");

        while (resultSet.next()) {
            int regionId = resultSet.getInt("region_id");
            UUID player = UUID.fromString(resultSet.getString("player"));
            int flags = resultSet.getInt("flags");

            Region region = regionManager.getRegion(regionId);
            if(region != null) {
                region.getPermissions().put(player, flags);
            }
        }
    }
}
