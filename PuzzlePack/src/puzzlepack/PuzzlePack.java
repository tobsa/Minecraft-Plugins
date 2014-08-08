package puzzlepack;

import itemrestrict.OnBlockPlaceItemRestrict;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzlePack extends JavaPlugin {
    
    public static int percent = 75;
    public static int size = 0;
    public static int air = 30;
    
    @Override
    public void onEnable() {             
        getServer().getPluginManager().registerEvents(new OnBlockPlaceItemRestrict(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new OnFoodLevelChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(), this);
        
        getCommand("tdist").setExecutor(new TDist());
        getCommand("tsize").setExecutor(new TSize());
    }

    public static boolean isToClose(Player player, Block block, double minDistance) {
        Location location1 = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
        Location location2 = block.getLocation();

        double x1 = location1.getX();
        double y1 = location1.getY();
        double z1 = location1.getZ();

        double x2 = location2.getX();
        double y2 = location2.getY();
        double z2 = location2.getZ();

        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));

        return distance < minDistance;
    }
    
    public static Integer getInteger(String number) {
        try {
            return Integer.valueOf(number);
        } catch(NumberFormatException ex) {
            return null;
        }
    }

}
