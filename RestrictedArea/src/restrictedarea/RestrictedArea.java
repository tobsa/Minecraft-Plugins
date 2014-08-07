
package restrictedarea;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;

public class RestrictedArea extends JavaPlugin {
    
    public static SubArea area;
    
    @Override
    public void onEnable() {
        WorldEditPlugin plugin = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(), this);
        
        CommandRegister commandRegister = new CommandRegister();
        
        commandRegister.register(getCommand("ra"),     new RestrictedAreaExecutor(plugin));
        commandRegister.register(getCommand("rahelp"), new HelpExecutor(commandRegister.getCommands()));
    }
    
}
