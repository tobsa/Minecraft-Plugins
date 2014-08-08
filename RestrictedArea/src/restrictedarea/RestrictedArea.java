
package restrictedarea;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;

public class RestrictedArea extends JavaPlugin {
        
    @Override
    public void onEnable() {
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        AreaManager areaManager = FileManager.load();
        GroupManager groupManager = new GroupManager();
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(areaManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        
        commandRegister.register(getCommand("ra"),          new RestrictedAreaExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("rainclude"),   new IncludeExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("raexclude"),   new ExcludeExecutor(areaManager));
        commandRegister.register(getCommand("radelete"),    new DeleteExecutor(areaManager));
        commandRegister.register(getCommand("rarename"),    new RenameExecutor(areaManager));
        commandRegister.register(getCommand("ralocation"),  new LocationExecutor(areaManager));
        commandRegister.register(getCommand("ralist"),      new ListExecutor(areaManager));
        commandRegister.register(getCommand("raselect"),    new SelectExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("raindex"),     new IndexExecutor(areaManager));
        
        commandRegister.register(getCommand("ragroup"),     new GroupExecutor(groupManager));
        commandRegister.register(getCommand("ragrouplist"), new GroupListExecutor(groupManager));        
        commandRegister.register(getCommand("ragroupadd"),  new GroupAddExecutor(areaManager, groupManager));   
        
        commandRegister.register(getCommand("rahelp"),      new HelpExecutor(commandRegister.getCommands()));
    }
    
}
