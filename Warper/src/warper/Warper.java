package warper;

import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class Warper extends JavaPlugin {
	
    @Override
    public void onEnable() {       
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("warpersave"),   new WarperSaveExecutor(this));
        commandRegister.register(getCommand("warperload"),   new WarperLoadExecutor(this));
        commandRegister.register(getCommand("warperdelete"), new WarperDeleteExecutor(this));
        commandRegister.register(getCommand("warperlist"),   new WarperListExecutor(this));
        commandRegister.register(getCommand("warperhelp"),   new HelpExecutor(commandRegister.getCommands()));
    }
}
