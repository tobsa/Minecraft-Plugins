package forestertool;

import basepack.CommandRegister;
import basepack.HelpExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class ForesterTool extends JavaPlugin {
    
    
    @Override
    public void onEnable() {
        FileManager.set(this);
        
        Tool tool = FileManager.load();
        
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(tool), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlaceEvent(tool), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("ftsize"),    new RadiusExecutor(tool));
        commandRegister.register(getCommand("ftair"),     new AirExecutor(tool));
        commandRegister.register(getCommand("ftleaves"),  new LeavesExecutor(tool));
        commandRegister.register(getCommand("ftreplon"),  new ReplaceOnExecutor(tool));
        commandRegister.register(getCommand("ftreploff"), new ReplaceOffExecutor(tool));
        commandRegister.register(getCommand("ftdiston"),  new DistanceOnExecutor(tool));
        commandRegister.register(getCommand("ftdistoff"), new DistanceOffExecutor(tool));
        commandRegister.register(getCommand("ftinfo"),    new InfoExecutor(tool));
        commandRegister.register(getCommand("fthelp"),    new HelpExecutor(commandRegister.getCommands()));
        
    }
}
