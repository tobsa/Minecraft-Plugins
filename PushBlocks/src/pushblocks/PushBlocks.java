package pushblocks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;

public class PushBlocks extends JavaPlugin {
    private PathManager pathManager;    
    
    @Override
    public void onEnable() {
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        pathManager = FileManager.load();
                
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(pathManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakEvent(pathManager), this);
                
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("pushblockspath"),   new PathExecutor(pathManager, worldEdit));
        commandRegister.register(getCommand("pushblocksdelete"), new DeletePathExecutor(pathManager));
        commandRegister.register(getCommand("pushblocksselect"), new PathSelectExecutor(pathManager, worldEdit));
        commandRegister.register(getCommand("pushblockshelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
    
    @Override
    public void onDisable() {
        FileManager.save(pathManager);
    }
    
}
