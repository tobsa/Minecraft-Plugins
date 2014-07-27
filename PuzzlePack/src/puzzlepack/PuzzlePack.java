package puzzlepack;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzlePack extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlazeRodInteractEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnFeatherInteractEvent(this), this);
    }

    @Override
    public void onDisable() {
    }

    public boolean isToClose(Player player, Block block, double minDistance) {
        Location location1 = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
        Location location2 = block.getLocation();

        double x1 = location1.getX();
        double y1 = location1.getY();
        double z1 = location1.getZ();

        double x2 = location2.getX();
        double y2 = location2.getY();
        double z2 = location2.getZ();

        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));

        if (distance < minDistance) {
            return true;
        }

        return false;
    }
}
