package areacollider;

import area.Area;
import area.AreaResponse;
import areasecret.AreaSecretResponse;
import areateleport.AreaTeleportResponse;
import clearinventory.ClearInventoryResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;

public class FileManager {
    private static AreaCollider plugin;
    
    public static void setConfig(AreaCollider plugin) {
        FileManager.plugin = plugin;
    }
    
    public static void addArea(Area area) {
        plugin.getConfig().set("area." + area.getName(), area.getName());
        plugin.getConfig().set("area." + area.getName() + ".playerName", area.getPlayerName());
                
        plugin.getConfig().set("area." + area.getName() + ".minX", area.getMinimumBlock().getX());
        plugin.getConfig().set("area." + area.getName() + ".minY", area.getMinimumBlock().getY());
        plugin.getConfig().set("area." + area.getName() + ".minZ", area.getMinimumBlock().getZ());
        plugin.getConfig().set("area." + area.getName() + ".maxX", area.getMaximumBlock().getX());
        plugin.getConfig().set("area." + area.getName() + ".maxY", area.getMaximumBlock().getY());
        plugin.getConfig().set("area." + area.getName() + ".maxZ", area.getMaximumBlock().getZ());
                
        if(area.getResponse() instanceof AreaResponse) {
            AreaResponse response = (AreaResponse)area.getResponse();
            
            plugin.getConfig().set("area." + area.getName() + ".response.type", AreaResponse.class.toString());
            plugin.getConfig().set("area." + area.getName() + ".response.message", response.getMessage());
            plugin.getConfig().set("area." + area.getName() + ".response.sound", response.getSound());
        } 
        else if(area.getResponse() instanceof AreaSecretResponse)
            plugin.getConfig().set("area." + area.getName() + ".response.type", AreaSecretResponse.class.toString());
        else if(area.getResponse() instanceof AreaTeleportResponse) {
            AreaTeleportResponse response = (AreaTeleportResponse)area.getResponse();
            
            plugin.getConfig().set("area." + area.getName() + ".response.type", AreaTeleportResponse.class.toString());
            plugin.getConfig().set("area." + area.getName() + ".response.message", response.getMessage());
            plugin.getConfig().set("area." + area.getName() + ".response.x", response.getLocation().getX());
            plugin.getConfig().set("area." + area.getName() + ".response.y", response.getLocation().getY());
            plugin.getConfig().set("area." + area.getName() + ".response.z", response.getLocation().getZ());
            plugin.getConfig().set("area." + area.getName() + ".response.yaw", response.getYaw());
            plugin.getConfig().set("area." + area.getName() + ".response.pitch", response.getPitch());
        }
        else if(area.getResponse() instanceof ClearInventoryResponse) {
            plugin.getConfig().set("area." + area.getName() + ".response.type", ClearInventoryResponse.class.toString());
        }
        
        plugin.saveConfig();
    }
    
    public static Map<String, Area> loadAreas() {
        Map<String, Area> areas = new LinkedHashMap();
        
        if(!plugin.getConfig().contains("area")) 
            return areas;
        
        for(String name : plugin.getConfig().getConfigurationSection("area").getKeys(false)) {
            String playerName = plugin.getConfig().getString("area." + name + ".playerName");
            
            int minX = plugin.getConfig().getInt("area." + name + ".minX");
            int minY = plugin.getConfig().getInt("area." + name + ".minY");
            int minZ = plugin.getConfig().getInt("area." + name + ".minZ");
            int maxX = plugin.getConfig().getInt("area." + name + ".maxX");
            int maxY = plugin.getConfig().getInt("area." + name + ".maxY");
            int maxZ = plugin.getConfig().getInt("area." + name + ".maxZ");
            
            String responseType = plugin.getConfig().getString("area." + name + ".response.type");
            
            Block minimumBlock = Bukkit.getWorld("world").getBlockAt(minX, minY, minZ);
            Block maximumBlock = Bukkit.getWorld("world").getBlockAt(maxX, maxY, maxZ);
                        
            if(responseType.equals(AreaSecretResponse.class.toString()))
                areas.put(name, new Area(playerName, name, minimumBlock, maximumBlock, new AreaSecretResponse()));
            else if(responseType.equals(AreaResponse.class.toString())) {
                String message = plugin.getConfig().getString("area." + name + ".response.message");
                String sound   = plugin.getConfig().getString("area." + name + ".response.sound");
                
                areas.put(name, new Area(playerName, name, minimumBlock, maximumBlock, new AreaResponse(message, Sound.valueOf(sound))));
            }
            else if(responseType.equals(AreaTeleportResponse.class.toString())) {
                String message = plugin.getConfig().getString("area." + name + ".response.message");
                double x = plugin.getConfig().getDouble("area." + name + ".response.x");
                double y = plugin.getConfig().getDouble("area." + name + ".response.y");
                double z = plugin.getConfig().getDouble("area." + name + ".response.z");
                float yaw = (float)plugin.getConfig().getDouble("area." + name + ".response.yaw");
                float pitch = (float)plugin.getConfig().getDouble("area." + name + ".response.pitch");
                                
                areas.put(name, new Area(playerName, name, minimumBlock, maximumBlock, new AreaTeleportResponse(new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch), message)));
            }
            else if(responseType.equals(ClearInventoryResponse.class.toString()))
                areas.put(name, new Area(playerName, name, minimumBlock, maximumBlock, new ClearInventoryResponse()));
        }
        
        return areas;
    }

    public static void removeArea(String name) {
        plugin.getConfig().set("area." + name, null);
        plugin.saveConfig();
    }
}
