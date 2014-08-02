package toggleblocks;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class ToggleBlocks extends JavaPlugin {    
        
    @Override
    public void onEnable() {
        RegionManager regionManager = FileManager.load();
        
        getServer().getPluginManager().registerEvents(new OnBlockPlacedEvent(regionManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(regionManager), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(regionManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockRedstoneEvent(regionManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("toggleblocks"),       new ToggleBlocksExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockson"),     new ToggleOnExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksoff"),    new ToggleOffExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockslink"),   new LinkExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksrlink"),  new RedstoneLinkExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksdelink"), new DelinkExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockslist"),   new ListExecutor(regionManager));
        commandRegister.register(getCommand("toggleblocksdelete"), new DeleteExecutor(regionManager));
        commandRegister.register(getCommand("toggleblockshelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
}
