package boomstick;

import org.bukkit.plugin.java.JavaPlugin;

public class Boomstick extends JavaPlugin {
    
    @Override
    public void onEnable() {
        
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(), this);
        
    }
    
}
