package telepads;

import org.bukkit.plugin.java.JavaPlugin;

public class Telepads extends JavaPlugin {
    
    @Override
    public void onEnable() {
        FileManager.setPlugin(this);
        
        TelepadManager telepadManager = new TelepadManager();
        
        getServer().getPluginManager().registerEvents(new OnPlayerMoveTelepad(telepadManager), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreakTelepad(telepadManager), this);
        
        getCommand("telepad").setExecutor(new TelepadExecutor(telepadManager));
        getCommand("telepadfrom").setExecutor(new TelepadFromExecutor(telepadManager));
        getCommand("telepadto").setExecutor(new TelepadToExecutor(telepadManager));
        getCommand("telepadlist").setExecutor(new TelepadListExecutor(telepadManager));
        getCommand("telepaddelete").setExecutor(new TelepadDeleteExecutor(telepadManager));
    }
}
