package toggleblocks;

import executors.DelinkExecutor;
import executors.HelpExecutor;
import executors.ToggleOffExecutor;
import executors.RedstoneLinkExecutor;
import executors.ToggleOnExecutor;
import executors.LinkExecutor;
import executors.ListExecutor;
import executors.ToggleBlocksExecutor;
import executors.ToggleBlocksDeleteExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class ToggleBlocks extends JavaPlugin {    
    private RegionManager regionManager;
        
    @Override
    public void onEnable() {
        regionManager = new RegionManager(this);
        
        getServer().getPluginManager().registerEvents(new OnBlockPlace(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(this), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("toggleblocks"),       new ToggleBlocksExecutor(this));
        commandRegister.register(getCommand("toggleblockson"),     new ToggleOnExecutor(this));
        commandRegister.register(getCommand("toggleblocksoff"),    new ToggleOffExecutor(this));
        commandRegister.register(getCommand("toggleblockslink"),   new LinkExecutor(this));
        commandRegister.register(getCommand("toggleblocksrlink"),  new RedstoneLinkExecutor(this));
        commandRegister.register(getCommand("toggleblocksdelink"), new DelinkExecutor(this));
        commandRegister.register(getCommand("toggleblockslist"),   new ListExecutor(this));
        commandRegister.register(getCommand("toggleblocksdelete"), new ToggleBlocksDeleteExecutor(this));
        commandRegister.register(getCommand("toggleblockshelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
        
    public RegionManager getRegionManager() {
        return regionManager;
    }
}
