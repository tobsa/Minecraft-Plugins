package blazeblocks;

import org.bukkit.plugin.java.JavaPlugin;

public class BlazeBlocks extends JavaPlugin {
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
    }
}
