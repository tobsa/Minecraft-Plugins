package telepads;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class Telepads extends JavaPlugin {
    
    @Override
    public void onEnable() {        
        TelepadManager telepadManager = FileManager.load();
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveTelepad(telepadManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakTelepad(telepadManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("telepad"),       new TelepadExecutor(telepadManager));
        commandRegister.register(getCommand("telepadfrom"),   new TelepadFromExecutor(telepadManager));
        commandRegister.register(getCommand("telepadto"),     new TelepadToExecutor(telepadManager));
        commandRegister.register(getCommand("telepadlist"),   new TelepadListExecutor(telepadManager));
        commandRegister.register(getCommand("telepaddelete"), new TelepadDeleteExecutor(telepadManager));
        commandRegister.register(getCommand("telepadhelp"),   new HelpExecutor(commandRegister.getCommands()));   
    }
}
