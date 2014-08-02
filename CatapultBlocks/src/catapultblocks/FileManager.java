package catapultblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

public class FileManager {
    private static CatapultBlocks plugin;
    
    public static void setPlugin(CatapultBlocks plugin) {
        FileManager.plugin = plugin;
    }
    
    public static void saveCatapultBlock(CatapultBlock catapultBlock) {
        int x = catapultBlock.getBlock().getX();
        int y = catapultBlock.getBlock().getY();
        int z = catapultBlock.getBlock().getZ();
        
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".x" , x);
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".y" , y);
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".z" , z);
        
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".direction" , catapultBlock.getDirection().toString());
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".velocityX" , catapultBlock.getVelocity().getX());
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".velocityY" , catapultBlock.getVelocity().getY());
        plugin.getConfig().set("catapultblocks." + x + "," + y + "," + z + ".velocityZ" , catapultBlock.getVelocity().getZ());
        plugin.saveConfig();
    }

    public static void removeCatapultBlock(CatapultBlock catapultBlock) {
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
            double velocityX = plugin.getConfig().getDouble("catapultblocks." + name + ".velocityX");
            double velocityY = plugin.getConfig().getDouble("catapultblocks." + name + ".velocityY");
            double velocityZ = plugin.getConfig().getDouble("catapultblocks." + name + ".velocityZ");
            
            Vector velocity = new Vector(velocityX, velocityY, velocityZ);
            
            catapultBlocks.add(new CatapultBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), direction, velocity));
        }
        
        return catapultBlocks;
    }
    
}
