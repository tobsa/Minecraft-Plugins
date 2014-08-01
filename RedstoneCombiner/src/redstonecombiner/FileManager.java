package redstonecombiner;

import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class FileManager {
    private static RedstoneCombiner plugin;
    
    public static void setPlugin(RedstoneCombiner plugin) {
        FileManager.plugin = plugin;
    }
    
    public static void saveCombiner(Combiner combiner) {
        plugin.getConfig().set("combiners." + combiner.getName() + ".playerName", combiner.getPlayerName());
        plugin.getConfig().set("combiners." + combiner.getName() + ".toggleblock.x" , combiner.getToggleBlock().getX());
        plugin.getConfig().set("combiners." + combiner.getName() + ".toggleblock.y" , combiner.getToggleBlock().getY());
        plugin.getConfig().set("combiners." + combiner.getName() + ".toggleblock.z" , combiner.getToggleBlock().getZ());  
        plugin.saveConfig();        
    }

    public static Map<String, Combiner> load() {
        Map<String, Combiner> combiners = new LinkedHashMap();
        
        if(!plugin.getConfig().contains("combiners"))
            return combiners;
        
        for(String name : plugin.getConfig().getConfigurationSection("combiners").getKeys(false)) {
            String playerName = plugin.getConfig().getString("combiners." + name + ".playerName");
            
            int x = plugin.getConfig().getInt("combiners." + name + ".toggleblock.x");
            int y = plugin.getConfig().getInt("combiners." + name + ".toggleblock.y");
            int z = plugin.getConfig().getInt("combiners." + name + ".toggleblock.z");
            
            Combiner combiner = new Combiner(playerName, name, Bukkit.getWorld("world").getBlockAt(x, y, z));
            
            if(plugin.getConfig().contains("combiners." + name + ".links")) {
                for(String link : plugin.getConfig().getConfigurationSection("combiners." + name + ".links").getKeys(false)) {
                    int lx = plugin.getConfig().getInt("combiners." + name + ".links." + link + ".x");
                    int ly = plugin.getConfig().getInt("combiners." + name + ".links." + link + ".y");
                    int lz = plugin.getConfig().getInt("combiners." + name + ".links." + link + ".z");
                    
                    combiner.addLink(Bukkit.getWorld("world").getBlockAt(lx, ly, lz));
                }
            }
             
            combiners.put(name, combiner);
        }
        
        return combiners;
    }

    public static void saveLink(String name, Block link) {
        int x = link.getX();
        int y = link.getY();
        int z = link.getZ();

        plugin.getConfig().set("combiners." + name + ".links." + x + "," + y + "," + z + ".x", x);
        plugin.getConfig().set("combiners." + name + ".links." + x + "," + y + "," + z + ".y", y);
        plugin.getConfig().set("combiners." + name + ".links." + x + "," + y + "," + z + ".z", z);
        plugin.saveConfig();
    }

    public static void removeCombiner(Combiner combiner) {
         plugin.getConfig().set("combiners." + combiner.getName(), null);
         plugin.saveConfig();
    }

    public static void removeLink(String name, Block link) {
        int x = link.getX();
        int y = link.getY();
        int z = link.getZ();
        
        plugin.getConfig().set("combiners." + name + ".links." + x + "," + y + "," + z + "", null);
        plugin.saveConfig();
    }
}
