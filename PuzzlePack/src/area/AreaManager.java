package area;

import area.teleportarea.TeleportArea;
import area.secretarea.SecretArea;
import area.clearinventoryarea.ClearInventoryArea;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import puzzlepack.PuzzlePack;


public class AreaManager {
    private PuzzlePack plugin;
    private List<SecretArea> secretAreas = new ArrayList();
    private List<TeleportArea> teleportAreas = new ArrayList();
    private List<ClearInventoryArea> clearInventoryAreas = new ArrayList();
    
    public AreaManager(PuzzlePack plugin) {
        this.plugin = plugin;
        
        if(plugin.getConfig().contains("secretareas")) {
            for(String name : plugin.getConfig().getConfigurationSection("secretareas").getKeys(false)) {
                String playerName = plugin.getConfig().getString("secretareas." + name + ".name");
                double x1 = plugin.getConfig().getDouble("secretareas." + name + ".x1");
                double y1 = plugin.getConfig().getDouble("secretareas." + name + ".y1");
                double z1 = plugin.getConfig().getDouble("secretareas." + name + ".z1");
                double x2 = plugin.getConfig().getDouble("secretareas." + name + ".x2");
                double y2 = plugin.getConfig().getDouble("secretareas." + name + ".y2");
                double z2 = plugin.getConfig().getDouble("secretareas." + name + ".z2");

                Location location1 = new Location(plugin.getServer().getWorld("world"), x1, y1, z1);
                Location location2 = new Location(plugin.getServer().getWorld("world"), x2, y2, z2);

                secretAreas.add(new SecretArea(playerName, name, location1.getBlock(), location2.getBlock()));
            }
        }
        
        if(plugin.getConfig().contains("teleportareas")) {
            for(String name : plugin.getConfig().getConfigurationSection("teleportareas").getKeys(false)) {
                String playerName = plugin.getConfig().getString("teleportareas." + name + ".name");
                double x1 = plugin.getConfig().getDouble("teleportareas." + name + ".x1");
                double y1 = plugin.getConfig().getDouble("teleportareas." + name + ".y1");
                double z1 = plugin.getConfig().getDouble("teleportareas." + name + ".z1");
                double x2 = plugin.getConfig().getDouble("teleportareas." + name + ".x2");
                double y2 = plugin.getConfig().getDouble("teleportareas." + name + ".y2");
                double z2 = plugin.getConfig().getDouble("teleportareas." + name + ".z2");
                double x3 = plugin.getConfig().getDouble("teleportareas." + name + ".x3");
                double y3 = plugin.getConfig().getDouble("teleportareas." + name + ".y3");
                double z3 = plugin.getConfig().getDouble("teleportareas." + name + ".z3");
                float pitch = (float)plugin.getConfig().getDouble("teleportareas." + name + ".pitch");
                float yaw   = (float)plugin.getConfig().getDouble("teleportareas." + name + ".yaw");
                String message = plugin.getConfig().getString("teleportareas." + name + ".message");
                
                Location location1 = new Location(plugin.getServer().getWorld("world"), x1, y1, z1);
                Location location2 = new Location(plugin.getServer().getWorld("world"), x2, y2, z2);
                Location location3 = new Location(plugin.getServer().getWorld("world"), x3, y3, z3);

                teleportAreas.add(new TeleportArea(playerName, name, message, location1.getBlock(), location2.getBlock(), location3, pitch, yaw));
            }
        }
        
        if(plugin.getConfig().contains("clearinvareas")) {
            for(String name : plugin.getConfig().getConfigurationSection("clearinvareas").getKeys(false)) {
                String playerName = plugin.getConfig().getString("clearinvareas." + name + ".name");
                double x1 = plugin.getConfig().getDouble("clearinvareas." + name + ".x1");
                double y1 = plugin.getConfig().getDouble("clearinvareas." + name + ".y1");
                double z1 = plugin.getConfig().getDouble("clearinvareas." + name + ".z1");
                double x2 = plugin.getConfig().getDouble("clearinvareas." + name + ".x2");
                double y2 = plugin.getConfig().getDouble("clearinvareas." + name + ".y2");
                double z2 = plugin.getConfig().getDouble("clearinvareas." + name + ".z2");
                String message = plugin.getConfig().getString("clearinvareas." + name + ".message");
                
                Location location1 = new Location(plugin.getServer().getWorld("world"), x1, y1, z1);
                Location location2 = new Location(plugin.getServer().getWorld("world"), x2, y2, z2);

                clearInventoryAreas.add(new ClearInventoryArea(playerName, name, message, location1.getBlock(), location2.getBlock()));
            }
        }
    }
        
    public SecretArea getSecretArea(String playerName, String name) {
        for(SecretArea area : secretAreas) {
            if(area.getName().equalsIgnoreCase(name) && playerName.equalsIgnoreCase(area.getPlayerName()))
                return area;
        }
        
        return null;
    }
    
    public TeleportArea getTeleportArea(String playerName, String name) {
        for(TeleportArea area : teleportAreas) {
            if(area.getName().equalsIgnoreCase(name) && playerName.equalsIgnoreCase(area.getPlayerName()))
                return area;
        }
        
        return null;
    }
    
    public ClearInventoryArea getClearInventoryArea(String playerName, String name) {
        for(ClearInventoryArea area : clearInventoryAreas) {
            if(area.getName().equalsIgnoreCase(name) && playerName.equalsIgnoreCase(area.getPlayerName()))
                return area;
        }
        
        return null;
    }
    
    public void addSecretArea(String playerName, String name, Block block1, Block block2) {
        SecretArea area = new SecretArea(playerName, name, block1, block2);
                
        plugin.getConfig().set("secretareas." + area.getName() + ".name", playerName);
        plugin.getConfig().set("secretareas." + area.getName() + ".x1", block1.getLocation().getX());
        plugin.getConfig().set("secretareas." + area.getName() + ".y1", block1.getLocation().getY());
        plugin.getConfig().set("secretareas." + area.getName() + ".z1", block1.getLocation().getZ());
        plugin.getConfig().set("secretareas." + area.getName() + ".x2", block2.getLocation().getX());
        plugin.getConfig().set("secretareas." + area.getName() + ".y2", block2.getLocation().getY());
        plugin.getConfig().set("secretareas." + area.getName() + ".z2", block2.getLocation().getZ());
        plugin.saveConfig();
        
        secretAreas.add(new SecretArea(playerName, name, block1, block2));
    }
        
    public void addTeleportArea(String playerName, String name, String message, Block block1, Block block2, Location teleport, float pitch, float yaw) {
        TeleportArea area = new TeleportArea(playerName, name, message, block1, block2, teleport, pitch, yaw);
        
        plugin.getConfig().set("teleportareas." + name + ".name", playerName);
        plugin.getConfig().set("teleportareas." + name + ".x1", block1.getLocation().getX());
        plugin.getConfig().set("teleportareas." + name + ".y1", block1.getLocation().getY());
        plugin.getConfig().set("teleportareas." + name + ".z1", block1.getLocation().getZ());
        plugin.getConfig().set("teleportareas." + name + ".x2", block2.getLocation().getX());
        plugin.getConfig().set("teleportareas." + name + ".y2", block2.getLocation().getY());
        plugin.getConfig().set("teleportareas." + name + ".z2", block2.getLocation().getZ());
        plugin.getConfig().set("teleportareas." + name + ".x3", teleport.getX());
        plugin.getConfig().set("teleportareas." + name + ".y3", teleport.getY());
        plugin.getConfig().set("teleportareas." + name + ".z3", teleport.getZ());
        plugin.getConfig().set("teleportareas." + name + ".pitch", pitch);
        plugin.getConfig().set("teleportareas." + name + ".yaw", yaw);
        plugin.getConfig().set("teleportareas." + name + ".message", area.getMessage());
        plugin.saveConfig();
        
        teleportAreas.add(area);
    }
    
    public void addClearInventoryArea(String playerName, String name, String message, Block block1, Block block2) {
        ClearInventoryArea area = new ClearInventoryArea(playerName, name, message, block1, block2);
        
        plugin.getConfig().set("clearinvareas." + name + ".name", playerName);
        plugin.getConfig().set("clearinvareas." + name + ".x1", block1.getLocation().getX());
        plugin.getConfig().set("clearinvareas." + name + ".y1", block1.getLocation().getY());
        plugin.getConfig().set("clearinvareas." + name + ".z1", block1.getLocation().getZ());
        plugin.getConfig().set("clearinvareas." + name + ".x2", block2.getLocation().getX());
        plugin.getConfig().set("clearinvareas." + name + ".y2", block2.getLocation().getY());
        plugin.getConfig().set("clearinvareas." + name + ".z2", block2.getLocation().getZ());
        plugin.getConfig().set("clearinvareas." + name + ".message", message);
        plugin.saveConfig();
        
        clearInventoryAreas.add(area);
    }
    
    public List<SecretArea> getSecretAreas() {
        return secretAreas;
    }
    
    public List<TeleportArea> getTeleportAreas() {
        return teleportAreas;
    }
    
    public List<ClearInventoryArea> getClearInventoryAreas() {
        return clearInventoryAreas;
    }
    
    public void removeSecretArea(SecretArea area) {
        secretAreas.remove(area);
        
        plugin.getConfig().set("secretareas." + area.getName(), null);
        plugin.saveConfig();
    }
    
    public void removeTeleportArea(TeleportArea area) {
        teleportAreas.remove(area);
        
        plugin.getConfig().set("teleportareas." + area.getName(), null);
        plugin.saveConfig();
    }
    
    public void removeClearInventoryArea(ClearInventoryArea area) {
        clearInventoryAreas.remove(area);
        
        plugin.getConfig().set("clearinvareas." + area.getName(), null);
        plugin.saveConfig();
    }
}
