package restrictedarea;

import restrictedarea.executors.RenameExecutor;
import restrictedarea.executors.SelectExecutor;
import restrictedarea.executors.IndexExecutor;
import restrictedarea.executors.IncludeExecutor;
import restrictedarea.executors.MessageExecutor;
import restrictedarea.executors.RestrictedAreaExecutor;
import restrictedarea.executors.DeleteExecutor;
import restrictedarea.executors.ExcludeExecutor;
import restrictedarea.executors.LocationExecutor;
import restrictedarea.executors.ListExecutor;
import restrictedarea.group.GroupManager;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;
import puzzlepack.HelpExecutor;
import restrictedarea.group.executors.GroupDeleteExecutor;
import restrictedarea.group.executors.GroupExcludeExecutor;
import restrictedarea.group.executors.GroupExecutor;
import restrictedarea.group.executors.GroupIncludeExecutor;
import restrictedarea.group.executors.GroupListExecutor;
import restrictedarea.group.executors.GroupRenameExecutor;

public class RestrictedArea extends JavaPlugin {
        
    @Override
    public void onEnable() {
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        AreaManager areaManager   = FileManager.loadAreaManager();
        GroupManager groupManager = FileManager.loadGroupManager();    
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(areaManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        
        commandRegister.register(getCommand("ra"),          new RestrictedAreaExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("rainclude"),   new IncludeExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("raexclude"),   new ExcludeExecutor(areaManager));
        commandRegister.register(getCommand("radelete"),    new DeleteExecutor(areaManager, groupManager));
        commandRegister.register(getCommand("rarename"),    new RenameExecutor(areaManager, groupManager));
        commandRegister.register(getCommand("ralocation"),  new LocationExecutor(areaManager));
        commandRegister.register(getCommand("ramessage"),   new MessageExecutor(areaManager));
        commandRegister.register(getCommand("ralist"),      new ListExecutor(areaManager));
        commandRegister.register(getCommand("raselect"),    new SelectExecutor(areaManager, worldEdit));
        commandRegister.register(getCommand("raindex"),     new IndexExecutor(areaManager));
        
        commandRegister.register(getCommand("ragroup"),        new GroupExecutor(groupManager));
        commandRegister.register(getCommand("ragrouplist"),    new GroupListExecutor(groupManager));        
        commandRegister.register(getCommand("ragroupdelete"),  new GroupDeleteExecutor(groupManager));
        commandRegister.register(getCommand("ragrouprename"),  new GroupRenameExecutor(groupManager));
        commandRegister.register(getCommand("ragroupinclude"), new GroupIncludeExecutor(areaManager, groupManager));  
        commandRegister.register(getCommand("ragroupexclude"), new GroupExcludeExecutor(areaManager, groupManager));  
        
        commandRegister.register(getCommand("rahelp"),      new HelpExecutor(commandRegister.getCommands()));
    }
    
}
