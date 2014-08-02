package telepads;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Telepads extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        
        TelepadManager telepadManager = new TelepadManager();
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveTelepad(telepadManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakTelepad(telepadManager), this);
        
        PluginCommand telepad       = getCommand("telepad");
        PluginCommand telepadfrom   = getCommand("telepadfrom");
        PluginCommand telepadto     = getCommand("telepadto");
        PluginCommand telepadlist   = getCommand("telepadlist");
        PluginCommand telepaddelete = getCommand("telepaddelete");
        PluginCommand telepadhelp   = getCommand("telepadhelp");
        
        List<PluginCommand> commands = new ArrayList();
        commands.add(telepad);
        commands.add(telepadfrom);
        commands.add(telepadto);
        commands.add(telepadlist);
        commands.add(telepaddelete);
        commands.add(telepadhelp);
        
        telepad.setExecutor(new TelepadExecutor(telepadManager));
        telepadfrom.setExecutor(new TelepadFromExecutor(telepadManager));
        telepadto.setExecutor(new TelepadToExecutor(telepadManager));
        telepadlist.setExecutor(new TelepadListExecutor(telepadManager));
        telepaddelete.setExecutor(new TelepadDeleteExecutor(telepadManager));
        telepadhelp.setExecutor(new HelpExecutor(commands));
    }
}
