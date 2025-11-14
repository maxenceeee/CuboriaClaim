package net.xamence.cuboriaclaim.claim;

import net.xamence.cuboriaclaim.region.Region;

import java.io.File;
import java.sql.*;

public class ClaimStorage {

    private final ClaimIndex index;
    private final RegionManager regionManager;
    private final Connection connection;

    public ClaimStorage(ClaimIndex index, RegionManager regionManager, File dataFolder) throws SQLException {
        this.index = index;
        this.regionManager = regionManager;

        File dbFile = new File(dataFolder, "claims.db");
        this.connection = DriverManager.getConnection("jdbc:sqlite:"+dbFile.getAbsolutePath());

        createTables();
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXIST region ( id INTEGER PRIMARY KEY AUTOINCREMENT, owner TEXT NOT NULL, points INTEGER NOT NULL DEFAULT 0);");
        statement.execute("CREATE TABLE IF NOT EXIST claim ( id INTEGER PRIMARY KEY AUTOINCREMENT, owner TEXT NOT NULL, world TEXT NOT NULL, cx INTEGER NOT NULL, cz INTEGER NOT NULL, region_id INTEGER NOT NULL);");

    }

    public void saveClaim(Claim claim) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO claim(owner, world, cx, cz, region_id) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, claim.getOwner().toString());
        preparedStatement.setString(2, claim.getWorld());
        preparedStatement.setInt(3, claim.getCx());
        preparedStatement.setInt(4, claim.getCz());
        preparedStatement.setInt(5, claim.getRegion().getId());
        preparedStatement.executeUpdate();
    }

    public void deleteClaim(Claim claim) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM claim WHERE world=? AND cx=? AND cz=?");
        preparedStatement.setString(1, claim.getWorld());
        preparedStatement.setInt(2, claim.getCx());
        preparedStatement.setInt(3, claim.getCz());
        preparedStatement.executeUpdate();
    }

    public void saveRegion(Region region) throws SQLException {
        //TODO !
    }
}
