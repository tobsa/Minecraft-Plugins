package toggleblocks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RegionManager {
    private ToggleBlocks plugin;
    private Map<String, Region> regions = new LinkedHashMap();
    private Map<Player, Region> editRegions = new LinkedHashMap();
    
    public RegionManager(ToggleBlocks plugin) {
        this.plugin = plugin;
        
        if(plugin.getConfig().contains("toggleblocks")) {
            for(String name : plugin.getConfig().getConfigurationSection("toggleblocks").getKeys(false)) {
                String playerName = plugin.getConfig().getString("toggleblocks." + name + ".name");
                
                Region region = new Region(plugin, playerName, name);
                regions.put(name, region);
                
                if(plugin.getConfig().contains("toggleblocks." + name + ".blocks")) {
                    for(String block : plugin.getConfig().getConfigurationSection("toggleblocks." + name + ".blocks").getKeys(false)) {
                        int x = plugin.getConfig().getInt("toggleblocks." + name + ".blocks." + block + ".x");
                        int y = plugin.getConfig().getInt("toggleblocks." + name + ".blocks." + block + ".y");
                        int z = plugin.getConfig().getInt("toggleblocks." + name + ".blocks." + block + ".z");
                        String material = plugin.getConfig().getString("toggleblocks." + name + ".blocks." + block + ".material");
                        
                        Location location = new Location(plugin.getServer().getWorld("world"), x, y, z);
                        
                        region.addToggleBlock(new ToggleBlock(location.getBlock(), Material.valueOf(material)));
                    }
                }
                
                if(plugin.getConfig().contains("toggleblocks." + name + ".link")) {
                    int x = plugin.getConfig().getInt("toggleblocks." + name + ".link.x");
                    int y = plugin.getConfig().getInt("toggleblocks." + name + ".link.y");
                    int z = plugin.getConfig().getInt("toggleblocks." + name + ".link.z");
                    
                    Location location = new Location(plugin.getServer().getWorld("world"), x, y, z);
                    
                    region.setLinkBlock(location.getBlock());
                }
            }
        }
    }
    
    public void addRegion(Region region) {
        regions.put(region.getName(), region);
        
        plugin.getConfig().set("toggleblocks." + region.getName() + ".name", region.getPlayerName());
        plugin.saveConfig();
    }
    
    public Region getRegion(String playerName, String name) {
        Region region = regions.get(name);
        if(region == null)
            return null;
        if(playerName.equalsIgnoreCase(region.getPlayerName()))
            return region;
        
        return null;        
    }
    
    public List<Region> getRegions(String playerName) {
        List<Region> list = new ArrayList();
        
        for(Region reg : regions.values()) {
            if(reg.getPlayerName().equalsIgnoreCase(playerName))
                list.add(reg);
        }
        
        return list;
    }
    
    public void removeRegion(Region region) {
        regions.remove(region.getName());
        
        plugin.getConfig().set("toggleblocks." + region.getName(), null);
        plugin.saveConfig();
    }
    
    public void setEditRegion(Player player, Region editRegion) {
        editRegions.put(player, editRegion);
    }
    
    public Region getEditRegion(Player player) {
        return editRegions.get(player);
    }
}
