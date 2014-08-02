package toggleblocks;

import executors.EditExecutor;
import executors.DelinkExecutor;
import executors.HelpExecutor;
import executors.ToggleOffExecutor;
import executors.RedstoneLinkExecutor;
import executors.ToggleOnExecutor;
import executors.LinkExecutor;
import executors.ListExecutor;
import executors.ToggleBlocksExecutor;
import executors.ToggleBlocksDeleteExecutor;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.PluginCommand;
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
        
        PluginCommand toggleblocks          = getCommand("toggleblocks");
        PluginCommand toggleblockson        = getCommand("toggleblockson");
        PluginCommand toggleblocksoff       = getCommand("toggleblocksoff");
        PluginCommand toggleblocksedit      = getCommand("toggleblocksedit");
        PluginCommand toggleblockslink      = getCommand("toggleblockslink");
        PluginCommand toggleblocksrlink     = getCommand("toggleblocksrlink");
        PluginCommand toggleblocksdelink    = getCommand("toggleblocksdelink");
        PluginCommand toggleblockslist      = getCommand("toggleblockslist");
        PluginCommand toggleblocksdelete    = getCommand("toggleblocksdelete");
        PluginCommand toggleblockshelp      = getCommand("toggleblockshelp");
        
        List<PluginCommand> commands = new ArrayList();
        commands.add(toggleblocks);
        commands.add(toggleblockson);
        commands.add(toggleblocksoff);
        commands.add(toggleblocksedit);
        commands.add(toggleblockslink);
        commands.add(toggleblocksrlink);
        commands.add(toggleblocksdelink);
        commands.add(toggleblockslist);
        commands.add(toggleblocksdelete);
        commands.add(toggleblockshelp);
        
        toggleblocks.setExecutor(new ToggleBlocksExecutor(this));
        toggleblockson.setExecutor(new ToggleOnExecutor(this));
        toggleblocksoff.setExecutor(new ToggleOffExecutor(this));
        toggleblocksedit.setExecutor(new EditExecutor(this));
        toggleblockslink.setExecutor(new LinkExecutor(this));
        toggleblocksrlink.setExecutor(new RedstoneLinkExecutor(this));
        toggleblocksdelink.setExecutor(new DelinkExecutor(this));
        toggleblockslist.setExecutor(new ListExecutor(this));
        toggleblocksdelete.setExecutor(new ToggleBlocksDeleteExecutor(this));
        toggleblockshelp.setExecutor(new HelpExecutor(commands));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    public RegionManager getRegionManager() {
        return regionManager;
    }
}
