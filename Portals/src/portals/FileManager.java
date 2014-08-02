package portals;

import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class FileManager {
    private static Portals plugin;
    
    public static void setPlugin(Portals plugin) {
        FileManager.plugin = plugin;
    }

    public static Map<String, Portal> load() {
        Map<String, Portal> portals = new LinkedHashMap();
        
        if(!plugin.getConfig().contains("portals"))
            return portals;
        
        for(String name : plugin.getConfig().getConfigurationSection("portals").getKeys(false)) {
            String playerName = plugin.getConfig().getString("portals." + name + ".playerName");
            int minX = plugin.getConfig().getInt("portals." + name + ".minX");
            int minY = plugin.getConfig().getInt("portals." + name + ".minY");
            int minZ = plugin.getConfig().getInt("portals." + name + ".minZ");
            
            int maxX = plugin.getConfig().getInt("portals." + name + ".maxX");
            int maxY = plugin.getConfig().getInt("portals." + name + ".maxY");
            int maxZ = plugin.getConfig().getInt("portals." + name + ".maxZ");
            
            double teleportX = plugin.getConfig().getDouble("portals." + name + ".teleportX");
            double teleportY = plugin.getConfig().getDouble("portals." + name + ".teleportY");
            double teleportZ = plugin.getConfig().getDouble("portals." + name + ".teleportZ");
            
            float teleportYaw   = (float)plugin.getConfig().getDouble("portals." + name + ".teleportYaw");
            float teleportPitch = (float)plugin.getConfig().getDouble("portals." + name + ".teleportPitch");
            
            Block minimumBlock = Bukkit.getWorld("world").getBlockAt(minX, minY, minZ);
            Block maximumBlock = Bukkit.getWorld("world").getBlockAt(maxX, maxY, maxZ);
            Location teleportLocation = new Location(Bukkit.getWorld("world"), teleportX, teleportY, teleportZ, teleportYaw, teleportPitch);
            
            portals.put(name, new Portal(name, playerName, minimumBlock, maximumBlock, teleportLocation));
        }
        
        return portals;
    }

     public static void savePortal(Portal portal) {
        plugin.getConfig().set("portals." + portal.getName() + ".playerName", portal.getPlayerName());
        
        plugin.getConfig().set("portals." + portal.getName() + ".minX", portal.getMinimumBlock().getX());
        plugin.getConfig().set("portals." + portal.getName() + ".minY", portal.getMinimumBlock().getY());
        plugin.getConfig().set("portals." + portal.getName() + ".minZ", portal.getMinimumBlock().getZ());
        
        plugin.getConfig().set("portals." + portal.getName() + ".maxX", portal.getMaximumBlock().getX());
        plugin.getConfig().set("portals." + portal.getName() + ".maxY", portal.getMaximumBlock().getY());
        plugin.getConfig().set("portals." + portal.getName() + ".maxZ", portal.getMaximumBlock().getZ());
        
        plugin.getConfig().set("portals." + portal.getName() + ".teleportX", portal.getTeleportLocation().getX());
        plugin.getConfig().set("portals." + portal.getName() + ".teleportY", portal.getTeleportLocation().getY());
        plugin.getConfig().set("portals." + portal.getName() + ".teleportZ", portal.getTeleportLocation().getZ());
        plugin.getConfig().set("portals." + portal.getName() + ".teleportYaw", portal.getTeleportLocation().getYaw());
        plugin.getConfig().set("portals." + portal.getName() + ".teleportPitch", portal.getTeleportLocation().getPitch());
        plugin.saveConfig();
    }

    public static void saveTeleportLocation(String name, Location location) {
        plugin.getConfig().set("portals." + name + ".teleportX", location.getX());
        plugin.getConfig().set("portals." + name + ".teleportY", location.getY());
        plugin.getConfig().set("portals." + name + ".teleportZ", location.getZ());
        plugin.getConfig().set("portals." + name + ".teleportYaw", location.getYaw());
        plugin.getConfig().set("portals." + name + ".teleportPitch", location.getPitch());
        plugin.saveConfig();
    }

    public static void removePortal(Portal portal) {
        plugin.getConfig().set("portals." + portal.getName(), null);
        plugin.saveConfig();
    }
}
