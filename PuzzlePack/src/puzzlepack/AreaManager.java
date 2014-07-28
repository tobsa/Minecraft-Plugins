package puzzlepack;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;


public class AreaManager {
    private PuzzlePack plugin;
    private List<AreaSecretRoom> secretRooms = new ArrayList();
    private List<AreaTeleportRoom> teleportRooms = new ArrayList();
    
    public AreaManager(PuzzlePack plugin) {
        this.plugin = plugin;
        
        if(plugin.getConfig().contains("secretrooms")) {
            for(String name : plugin.getConfig().getConfigurationSection("secretrooms").getKeys(false)) {
                String playerName = plugin.getConfig().getString("secretrooms." + name + ".name");
                double x1 = plugin.getConfig().getDouble("secretrooms." + name + ".x1");
                double y1 = plugin.getConfig().getDouble("secretrooms." + name + ".y1");
                double z1 = plugin.getConfig().getDouble("secretrooms." + name + ".z1");
                double x2 = plugin.getConfig().getDouble("secretrooms." + name + ".x2");
                double y2 = plugin.getConfig().getDouble("secretrooms." + name + ".y2");
                double z2 = plugin.getConfig().getDouble("secretrooms." + name + ".z2");

                Location location1 = new Location(plugin.getServer().getWorld("world"), x1, y1, z1);
                Location location2 = new Location(plugin.getServer().getWorld("world"), x2, y2, z2);

                secretRooms.add(new AreaSecretRoom(playerName, name, location1.getBlock(), location2.getBlock()));
            }
        }
        
        if(plugin.getConfig().contains("teleportrooms")) {
            for(String name : plugin.getConfig().getConfigurationSection("teleportrooms").getKeys(false)) {
                String playerName = plugin.getConfig().getString("teleportrooms." + name + ".name");
                double x1 = plugin.getConfig().getDouble("teleportrooms." + name + ".x1");
                double y1 = plugin.getConfig().getDouble("teleportrooms." + name + ".y1");
                double z1 = plugin.getConfig().getDouble("teleportrooms." + name + ".z1");
                double x2 = plugin.getConfig().getDouble("teleportrooms." + name + ".x2");
                double y2 = plugin.getConfig().getDouble("teleportrooms." + name + ".y2");
                double z2 = plugin.getConfig().getDouble("teleportrooms." + name + ".z2");
                double x3 = plugin.getConfig().getDouble("teleportrooms." + name + ".x3");
                double y3 = plugin.getConfig().getDouble("teleportrooms." + name + ".y3");
                double z3 = plugin.getConfig().getDouble("teleportrooms." + name + ".z3");
                float pitch = (float)plugin.getConfig().getDouble("teleportrooms." + name + ".pitch");
                float yaw   = (float)plugin.getConfig().getDouble("teleportrooms." + name + ".yaw");

                Location location1 = new Location(plugin.getServer().getWorld("world"), x1, y1, z1);
                Location location2 = new Location(plugin.getServer().getWorld("world"), x2, y2, z2);
                Location location3 = new Location(plugin.getServer().getWorld("world"), x3, y3, z3);

                teleportRooms.add(new AreaTeleportRoom(playerName, name, location1.getBlock(), location2.getBlock(), location3, pitch, yaw));
            }
        }
    }
        
    public AreaSecretRoom getSecretRoom(String playerName, String name) {
        for(AreaSecretRoom area : secretRooms) {
            if(area.getName().equalsIgnoreCase(name) && playerName.equalsIgnoreCase(area.getPlayerName()))
                return area;
        }
        
        return null;
    }
    
    public AreaTeleportRoom getTeleportRoom(String playerName, String name) {
        for(AreaTeleportRoom area : teleportRooms) {
            if(area.getName().equalsIgnoreCase(name) && playerName.equalsIgnoreCase(area.getPlayerName()))
                return area;
        }
        
        return null;
    }
    
    public void addSecretRoom(String playerName, String name, Block block1, Block block2) {
        AreaSecretRoom area = new AreaSecretRoom(playerName, name, block1, block2);
                
        plugin.getConfig().set("secretrooms." + area.getName() + ".name", playerName);
        plugin.getConfig().set("secretrooms." + area.getName() + ".x1", block1.getLocation().getX());
        plugin.getConfig().set("secretrooms." + area.getName() + ".y1", block1.getLocation().getY());
        plugin.getConfig().set("secretrooms." + area.getName() + ".z1", block1.getLocation().getZ());
        plugin.getConfig().set("secretrooms." + area.getName() + ".x2", block2.getLocation().getX());
        plugin.getConfig().set("secretrooms." + area.getName() + ".y2", block2.getLocation().getY());
        plugin.getConfig().set("secretrooms." + area.getName() + ".z2", block2.getLocation().getZ());
        plugin.saveConfig();
        
        secretRooms.add(new AreaSecretRoom(playerName, name, block1, block2));
    }
    
    public void addTeleportRoom(String playerName, String name, Block block1, Block block2, Location teleport, float pitch, float yaw) {
        AreaTeleportRoom teleportRoom = new AreaTeleportRoom(playerName, name, block1, block2, teleport, pitch, yaw);
        
        plugin.getConfig().set("teleportrooms." + name + ".name", playerName);
        plugin.getConfig().set("teleportrooms." + name + ".x1", block1.getLocation().getX());
        plugin.getConfig().set("teleportrooms." + name + ".y1", block1.getLocation().getY());
        plugin.getConfig().set("teleportrooms." + name + ".z1", block1.getLocation().getZ());
        plugin.getConfig().set("teleportrooms." + name + ".x2", block2.getLocation().getX());
        plugin.getConfig().set("teleportrooms." + name + ".y2", block2.getLocation().getY());
        plugin.getConfig().set("teleportrooms." + name + ".z2", block2.getLocation().getZ());
        plugin.getConfig().set("teleportrooms." + name + ".x3", teleport.getX());
        plugin.getConfig().set("teleportrooms." + name + ".y3", teleport.getY());
        plugin.getConfig().set("teleportrooms." + name + ".z3", teleport.getZ());
        plugin.getConfig().set("teleportrooms." + name + ".pitch", pitch);
        plugin.getConfig().set("teleportrooms." + name + ".yaw", yaw);
        plugin.saveConfig();
        
        teleportRooms.add(teleportRoom);
    }
    
    public List<AreaSecretRoom> getSecretRooms() {
        return secretRooms;
    }
    
    public List<AreaTeleportRoom> getTeleportRooms() {
        return teleportRooms;
    }
}
