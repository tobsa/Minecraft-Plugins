package portals;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Portals extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        
        PortalManager portalManager = new PortalManager(FileManager.load());
        WorldEditPlugin worldEdit = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");
        
        getServer().getPluginManager().registerEvents(new OnPlayerPortalEvent(portalManager), this);
        
        getCommand("portal").setExecutor(new PortalExecutor(portalManager, worldEdit));
        getCommand("portallist").setExecutor(new PortalListExecutor(portalManager));
        getCommand("portalto").setExecutor(new PortalToExecutor(portalManager));
        getCommand("portaldelete").setExecutor(new PortalDeleteExecutor(portalManager));
        getCommand("portalrename").setExecutor(new PortalRenameExecutor(portalManager));
        getCommand("portalselect").setExecutor(new PortalSelectExecutor(portalManager, worldEdit));
    }

}
