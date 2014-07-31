package toggleblocks;

import executors.EditExecutor;
import executors.DelinkExecutor;
import executors.ToggleOffExecutor;
import executors.RedstoneLinkExecutor;
import executors.ToggleOnExecutor;
import executors.LinkExecutor;
import executors.ListExecutor;
import executors.ToggleBlocksExecutor;
import executors.ToggleBlocksDeleteExecutor;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class ToggleBlocks extends JavaPlugin {    
    private RegionManager regionManager;
        
    @Override
    public void onEnable() {
        regionManager = new RegionManager(this);
        
        getServer().getPluginManager().registerEvents(new OnBlockPlace(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(this), this);
        
        getCommand("toggleblocks").setExecutor(new ToggleBlocksExecutor(this));
        getCommand("toggleblockson").setExecutor(new ToggleOnExecutor(this));
        getCommand("toggleblocksoff").setExecutor(new ToggleOffExecutor(this));
        getCommand("toggleblocksedit").setExecutor(new EditExecutor(this));
        getCommand("toggleblockslink").setExecutor(new LinkExecutor(this));
        getCommand("toggleblocksrlink").setExecutor(new RedstoneLinkExecutor(this));
        getCommand("toggleblocksdelink").setExecutor(new DelinkExecutor(this));
        getCommand("toggleblockslist").setExecutor(new ListExecutor(this));
        getCommand("toggleblocksdelete").setExecutor(new ToggleBlocksDeleteExecutor(this));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    public RegionManager getRegionManager() {
        return regionManager;
    }
}
