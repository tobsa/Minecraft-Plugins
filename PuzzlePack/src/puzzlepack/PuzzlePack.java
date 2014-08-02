package puzzlepack;

import itemrestrict.OnBlockPlaceItemRestrict;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzlePack extends JavaPlugin {
    
    @Override
    public void onEnable() {                
        getServer().getPluginManager().registerEvents(new OnBlockPlaceItemRestrict(), this);
        getServer().getPluginManager().registerEvents(new OnFallDamageEvent(), this);
    }

    @Override
    public void onDisable() {
    }
}
