package puzzlepack;

import puzzlepack.executors.SecretRoomSelectionExecutor;
import puzzlepack.executors.SecretRoomExecutor;
import puzzlepack.executors.SecretRoomDeleteExecutor;
import puzzlepack.executors.TeleportRoomSelectionExecutor;
import puzzlepack.executors.TeleportRoomExecutor;
import puzzlepack.executors.TeleportRoomListExecutor;
import puzzlepack.executors.SecretRoomListExecutor;
import puzzlepack.executors.TeleportRoomDeleteExecutor;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
===== Block Usages =====

Gold:           Levers
Iron:           Ladders
Lapiz:
Emerald:
Diamond:
    
===== Wools =====
       
White:          Jumping 
Orange:         Teleport to
Magenta:        Elevators
LightBlue:
Yellow:         
Lime:
Pink:           Clear inventory
Grey:
LightGrey:
Cyan:
Purple:         Push block up/down with feather/bone
Blue:           Teleport from
Brown:          Teleport over block with blazerod 
Green:          Teleport under block with blazerod
Red:            Redstone wire 
Black:          Push block sideways with feather/bone

*/


public class PuzzlePack extends JavaPlugin {
    private WorldEditPlugin we;
    private AreaManager areaManager;
    
    @Override
    public void onEnable() {
        we = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        areaManager = new AreaManager(this);
        
        getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new OnBlazeRodInteractEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnFeatherInteractEvent(this), this);
        
        getCommand("secretroom").setExecutor(new SecretRoomExecutor(this));
        getCommand("secretroomlist").setExecutor(new SecretRoomListExecutor(this));
        getCommand("secretroomdelete").setExecutor(new SecretRoomDeleteExecutor(this));
        getCommand("secretroomselection").setExecutor(new SecretRoomSelectionExecutor(this));
        
        getCommand("teleportroom").setExecutor(new TeleportRoomExecutor(this));
        getCommand("teleportroomlist").setExecutor(new TeleportRoomListExecutor(this));
        getCommand("teleportroomdelete").setExecutor(new TeleportRoomDeleteExecutor(this));
        getCommand("teleportroomselection").setExecutor(new TeleportRoomSelectionExecutor(this));
    }

    @Override
    public void onDisable() {
    }
    
    public WorldEditPlugin getWorldEdit() {
        return we;
    }

    public AreaManager getAreaManager() {
        return areaManager;
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

        return distance < minDistance;
    }
}
