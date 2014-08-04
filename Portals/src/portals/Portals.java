package portals;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;
import puzzlepack.CommandRegister;

public class Portals extends JavaPlugin {
    
    @Override
    public void onEnable() {
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        PortalManager portalManager = FileManager.load();
        
        getServer().getPluginManager().registerEvents(new OnPlayerPortalEvent(portalManager), this);
        
        CommandRegister commandRegister = new CommandRegister();
        commandRegister.register(getCommand("portal"),       new PortalExecutor(portalManager, worldEdit));
        commandRegister.register(getCommand("portallist"),   new PortalListExecutor(portalManager));
        commandRegister.register(getCommand("portalto"),     new PortalToExecutor(portalManager));
        commandRegister.register(getCommand("portaldelete"), new PortalDeleteExecutor(portalManager));
        commandRegister.register(getCommand("portalrename"), new PortalRenameExecutor(portalManager));
        commandRegister.register(getCommand("portalselect"), new PortalSelectExecutor(portalManager, worldEdit));
        commandRegister.register(getCommand("portalhelp"),   new HelpExecutor(commandRegister.getCommands()));
    }

}
