package telepad;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import puzzlepack.PuzzlePack;


public class TelepadManager {
    private PuzzlePack plugin;
    private Map<String, Telepad> telepads = new LinkedHashMap();
    
    public TelepadManager(PuzzlePack plugin) {
        this.plugin = plugin;
        
        if(plugin.getConfig().contains("telepads")) {
            for(String name : plugin.getConfig().getConfigurationSection("telepads").getKeys(false)) {
                String playerName = plugin.getConfig().getString("telepads." + name + ".name");
                double fx = plugin.getConfig().getInt("telepads." + name + ".fx");
                double fy = plugin.getConfig().getInt("telepads." + name + ".fy");
                double fz = plugin.getConfig().getInt("telepads." + name + ".fz");
                double tx = plugin.getConfig().getInt("telepads." + name + ".tx");
                double ty = plugin.getConfig().getInt("telepads." + name + ".ty");
                double tz = plugin.getConfig().getInt("telepads." + name + ".tz");
                float yaw   = (float)plugin.getConfig().getDouble("telepads." + name + ".yaw");
                float pitch = (float)plugin.getConfig().getDouble("telepads." + name + ".pitch");
                
                Location from = new Location(plugin.getServer().getWorld("world"), fx, fy, fz);
                Location to   = new Location(plugin.getServer().getWorld("world"), tx, ty, tz);
                
                Telepad telepad = new Telepad(name, playerName);
                telepad.setFrom(from);
                telepad.setTo(to, yaw, pitch); 
                telepads.put(name, telepad);
            }
        }
    }
    
    public void createTelepad(String name, String playerName) {
        telepads.put(name, new Telepad(name, playerName));
    }
    
    public boolean isAvailable(String name) {
        return !telepads.containsKey(name);
    }
    
    public Telepad getTelepad(String playerName, String name) {
        Telepad telepad = telepads.get(name);
        if(telepad == null)
            return null;
        if(telepad.getPlayerName().equals(playerName))
            return telepad;
        
        return null;        
    }
    
    public List<Telepad> getTelepads() {
        return new ArrayList(telepads.values());
    }
    
    public void removeTelepad(Telepad telepad) {
        telepads.remove(telepad.getName());
        
        plugin.getConfig().set("telepads." + telepad.getName(), null);
        plugin.saveConfig();
    }       
    
    public void saveFrom(Telepad telepad) {
        plugin.getConfig().set("telepads." + telepad.getName() + ".name", telepad.getPlayerName());
        plugin.getConfig().set("telepads." + telepad.getName() + ".fx", telepad.getFrom().getBlockX());
        plugin.getConfig().set("telepads." + telepad.getName() + ".fy", telepad.getFrom().getBlockY());
        plugin.getConfig().set("telepads." + telepad.getName() + ".fz", telepad.getFrom().getBlockZ());
        plugin.saveConfig();
    }
    
    public void saveTo(Telepad telepad) {        
        plugin.getConfig().set("telepads." + telepad.getName() + ".name", telepad.getPlayerName());
        plugin.getConfig().set("telepads." + telepad.getName() + ".tx", telepad.getTo().getBlockX());
        plugin.getConfig().set("telepads." + telepad.getName() + ".ty", telepad.getTo().getBlockY());
        plugin.getConfig().set("telepads." + telepad.getName() + ".tz", telepad.getTo().getBlockZ());
        plugin.getConfig().set("telepads." + telepad.getName() + ".yaw",   telepad.getYaw());
        plugin.getConfig().set("telepads." + telepad.getName() + ".pitch", telepad.getPitch());
        plugin.saveConfig();
    }
    
    public void removeFrom(Telepad telepad) {
        telepad.setFrom(null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".fx", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".fy", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".fz", null);
        plugin.saveConfig();
    }
    
    public void removeTo(Telepad telepad) {
        telepad.setTo(null, 0.f, 0.f);
        plugin.getConfig().set("telepads." + telepad.getName() + ".tx", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".ty", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".tz", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".yaw", null);
        plugin.getConfig().set("telepads." + telepad.getName() + ".pitch", null);
        plugin.saveConfig();
    }
}
