package interactblocks;

import interactblocks.giveitem.GiveItemExecutor;
import interactblocks.giveitem.ItemListExecutor;
import interactblocks.message.MessageBlockExecutor;
import interactblocks.soundblock.SoundBlockExecutor;
import interactblocks.soundblock.SoundListExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;

public class InteractBlocks extends JavaPlugin {
    
    
    @Override
    public void onEnable() {
        InteractBlockManager blockManager = FileManager.load();
        
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(blockManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("ibsound"),     new SoundBlockExecutor(blockManager));        
        commandRegister.register(getCommand("ibsoundlist"), new SoundListExecutor());  
        commandRegister.register(getCommand("ibmessage"),   new MessageBlockExecutor(blockManager)); 
        commandRegister.register(getCommand("ibgiveitem"),  new GiveItemExecutor(blockManager)); 
        commandRegister.register(getCommand("ibitemlist"),  new ItemListExecutor()); 
        commandRegister.register(getCommand("ibdelete"),    new DeleteExecutor(blockManager)); 
        commandRegister.register(getCommand("ibhelp"),      new HelpExecutor(commandRegister.getCommands())); 
    }
}
