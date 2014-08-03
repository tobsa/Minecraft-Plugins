package soundblocks;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;

public class SoundBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        SoundBlockManager soundBlockManager = FileManager.load();
        
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(soundBlockManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("soundblock"),       new SoundBlockExecutor(soundBlockManager));
        commandRegister.register(getCommand("soundblockdelete"), new DeleteExecutor(soundBlockManager));
        commandRegister.register(getCommand("soundblocklist"),   new ListExecutor());
        commandRegister.register(getCommand("soundblockhelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
}
