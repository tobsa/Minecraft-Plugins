package portals;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Portals extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        
        PortalManager portalManager = new PortalManager(FileManager.load());
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerPortalEvent(portalManager), this);
        
        PluginCommand portal        = getCommand("portal");
        PluginCommand portallist    = getCommand("portallist");
        PluginCommand portalto      = getCommand("portalto");
        PluginCommand portaldelete  = getCommand("portaldelete");
        PluginCommand portalrename  = getCommand("portalrename");
        PluginCommand portalselect  = getCommand("portalselect");
        PluginCommand portalhelp    = getCommand("portalhelp");
        
        List<PluginCommand> commands = new ArrayList();
        commands.add(portal);
        commands.add(portallist);
        commands.add(portalto);
        commands.add(portaldelete);
        commands.add(portalrename);
        commands.add(portalselect);
        commands.add(portalhelp);
        
        portal.setExecutor(new PortalExecutor(portalManager, worldEdit));
        portallist.setExecutor(new PortalListExecutor(portalManager));
        portalto.setExecutor(new PortalToExecutor(portalManager));
        portaldelete.setExecutor(new PortalDeleteExecutor(portalManager));
        portalrename.setExecutor(new PortalRenameExecutor(portalManager));
        portalselect.setExecutor(new PortalSelectExecutor(portalManager, worldEdit));
        portalhelp.setExecutor(new HelpExecutor(commands));
    }

}
