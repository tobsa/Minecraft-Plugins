package telepads;

import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.Location;

public class FileManager {
    private static Telepads plugin;
    
    public static void setPlugin(Telepads plugin) {
        FileManager.plugin = plugin;
    }
        
    public static Map<String, Telepad> load() {
        Map<String, Telepad> telepads = new LinkedHashMap();
        
        if(!plugin.getConfig().contains("telepads"))
            return telepads;
        
        for(String name : plugin.getConfig().getConfigurationSection("telepads").getKeys(false)) {
            String playerName = plugin.getConfig().getString("telepads." + name + ".name");
            double fx = plugin.getConfig().getDouble("telepads." + name + ".fx");
            double fy = plugin.getConfig().getDouble("telepads." + name + ".fy");
            double fz = plugin.getConfig().getDouble("telepads." + name + ".fz");
            double tx = plugin.getConfig().getDouble("telepads." + name + ".tx");
            double ty = plugin.getConfig().getDouble("telepads." + name + ".ty");
            double tz = plugin.getConfig().getDouble("telepads." + name + ".tz");
            float yaw   = (float)plugin.getConfig().getDouble("telepads." + name + ".yaw");
            float pitch = (float)plugin.getConfig().getDouble("telepads." + name + ".pitch");

            Location from = new Location(plugin.getServer().getWorld("world"), fx, fy, fz);
            Location to   = new Location(plugin.getServer().getWorld("world"), tx, ty, tz);

            Telepad telepad = new Telepad(name, playerName);
            telepad.setFrom(from);
            telepad.setTo(to, yaw, pitch); 
            telepads.put(name, telepad);
        }
        
        return telepads;
    }

    public static void remove(Telepad telepad) {
        plugin.getConfig().set("telepads." + telepad.getName(), null);
        plugin.saveConfig();
    }

    public static void saveFrom(Telepad telepad) {
        plugin.getConfig().set("telepads." + telepad.getName() + ".name", telepad.getPlayerName());
        plugin.getConfig().set("telepads." + telepad.getName() + ".fx", telepad.getFrom().getBlockX());
        plugin.getConfig().set("telepads." + telepad.getName() + ".fy", telepad.getFrom().getBlockY());
        plugin.getConfig().set("telepads." + telepad.getName() + ".fz", telepad.getFrom().getBlockZ());
        plugin.saveConfig();
    }

    public static void saveTo(Telepad telepad) {
        plugin.getConfig().set("telepads." + telepad.getName() + ".name", telepad.getPlayerName());
        plugin.getConfig().set("telepads." + telepad.getName() + ".tx", telepad.getTo().getX());
        plugin.getConfig().set("telepads." + telepad.getName() + ".ty", telepad.getTo().getY());
        plugin.getConfig().set("telepads." + telepad.getName() + ".tz", telepad.getTo().getZ());
        plugin.getConfig().set("telepads." + telepad.getName() + ".yaw",   telepad.getYaw());
        plugin.getConfig().set("telepads." + telepad.getName() + ".pitch", telepad.getPitch());
        plugin.saveConfig();
    }

    public static void removeFrom(Telepad telepad) {
        plugin.getConfig().set("telepads." + telepad.getName() + ".fx", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".fy", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".fz", null);
        plugin.saveConfig();
    }

    public static void removeTo(Telepad telepad) {
        plugin.getConfig().set("telepads." + telepad.getName() + ".tx", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".ty", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".tz", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".yaw", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".pitch", null);
        plugin.saveConfig();
    }
    
}
