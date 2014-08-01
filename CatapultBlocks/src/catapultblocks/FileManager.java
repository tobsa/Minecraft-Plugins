package catapultblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;

public class FileManager {
    private static CatapultBlocks plugin;
    
    public static void setPlugin(CatapultBlocks plugin) {
        FileManager.plugin = plugin;
    }
    
    public static void save(CatapultBlock catapultBlock) {
        int x = catapultBlock.getBlock().getX();
        int y = catapultBlock.getBlock().getY();
        int z = catapultBlock.getBlock().getZ();
        
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".x" , x);
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".y" , y);
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".z" , z);
        
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".direction" , catapultBlock.getDirection().toString());
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".forwardVelocity" , catapultBlock.getForwardVelocity());
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".upwardVelocity" , catapultBlock.getUpwardVelocity());
        plugin.saveConfig();
    }

    public static void remove(CatapultBlock catapultBlock) {
        int x = catapultBlock.getBlock().getX();
        int y = catapultBlock.getBlock().getY();
        int z = catapultBlock.getBlock().getZ();
        
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z, null);
        plugin.saveConfig();
    }

    public static List<CatapultBlock> load() {
        List<CatapultBlock> catapultBlocks = new ArrayList();
        
        if(!plugin.getConfig().contains("catapultblocks"))
            return catapultBlocks;
            
        for(String name : plugin.getConfig().getConfigurationSection("catapultblocks").getKeys(false)) {
            int x = plugin.getConfig().getInt("catapultblocks." + name + ".x");
            int y = plugin.getConfig().getInt("catapultblocks." + name + ".y");
            int z = plugin.getConfig().getInt("catapultblocks." + name + ".z");

            Direction direction = Direction.valueOf(plugin.getConfig().getString("catapultblocks." + name + ".direction"));
            double forwardVelocity = plugin.getConfig().getDouble("catapultblocks." + name + ".forwardVelocity");
            double upwardVelocity = plugin.getConfig().getInt("catapultblocks." + name + ".upwardVelocity");

            catapultBlocks.add(new CatapultBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), direction, forwardVelocity, upwardVelocity));
        }
        
        return catapultBlocks;
    }
    
}
