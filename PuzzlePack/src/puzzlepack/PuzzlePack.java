package puzzlepack;

import itemrestrict.OnBlockPlaceItemRestrict;
import blazeblock.OnPlayerInteractBlockBlaze;
import bounceblock.OnPlayerBounceBlockBreak;
import area.AreaManager;
import bounceblock.OnPlayerMoveBounceBlock;
import area.clearinventoryarea.OnPlayerMoveClearInventoryArea;
import area.secretarea.OnPlayerMoveSecretArea;
import area.teleportarea.OnPlayerMoveTeleportArea;
import bounceblock.BounceBlockManager;
import area.secretarea.SecretAreaSelectionExecutor;
import area.secretarea.SecretAreaExecutor;
import area.secretarea.SecretAreaDeleteExecutor;
import area.teleportarea.TeleportAreaSelectionExecutor;
import area.teleportarea.TeleportAreaExecutor;
import area.teleportarea.TeleportAreaListExecutor;
import area.secretarea.SecretAreaListExecutor;
import area.teleportarea.TeleportAreaDeleteExecutor;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import bounceblock.BounceBlockExecutor;
import area.clearinventoryarea.ClearInventoryAreaDeleteExecutor;
import area.clearinventoryarea.ClearInventoryAreaExecutor;
import area.clearinventoryarea.ClearInventoryAreaListExecutor;
import area.clearinventoryarea.ClearInventoryAreaSelectionExecutor;
import pushblocks.OnBlockPushBackward;
import pushblocks.OnBlockPushDown;
import pushblocks.OnBlockPushForward;
import pushblocks.OnBlockPushUp;

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
Pink:           
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
    private BounceBlockManager bounceBlockManager;
    
    @Override
    public void onEnable() {
        we = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        areaManager = new AreaManager(this);
        bounceBlockManager = new BounceBlockManager(this);
        
        getServer().getPluginManager().registerEvents(new OnBlockPlaceItemRestrict(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractBlockBlaze(this), this);
        
        getServer().getPluginManager().registerEvents(new OnBlockPushBackward(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockPushDown(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockPushForward(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockPushUp(this), this);
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveBounceBlock(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerBounceBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveClearInventoryArea(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveSecretArea(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveTeleportArea(this), this);
        
        getCommand("bounceblock").setExecutor(new BounceBlockExecutor(this));
        
        getCommand("secretarea").setExecutor(new SecretAreaExecutor(this));
        getCommand("secretarealist").setExecutor(new SecretAreaListExecutor(this));
        getCommand("secretareadelete").setExecutor(new SecretAreaDeleteExecutor(this));
        getCommand("secretareaselection").setExecutor(new SecretAreaSelectionExecutor(this));
        
        getCommand("teleportarea").setExecutor(new TeleportAreaExecutor(this));
        getCommand("teleportarealist").setExecutor(new TeleportAreaListExecutor(this));
        getCommand("teleportareadelete").setExecutor(new TeleportAreaDeleteExecutor(this));
        getCommand("teleportareaselection").setExecutor(new TeleportAreaSelectionExecutor(this));
        
        getCommand("clearinvarea").setExecutor(new ClearInventoryAreaExecutor(this));
        getCommand("clearinvarealist").setExecutor(new ClearInventoryAreaListExecutor(this));
        getCommand("clearinvareadelete").setExecutor(new ClearInventoryAreaDeleteExecutor(this));
        getCommand("clearinvareaselection").setExecutor(new ClearInventoryAreaSelectionExecutor(this));
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
    
    public BounceBlockManager getBounceBlockManager() {
        return bounceBlockManager;
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
